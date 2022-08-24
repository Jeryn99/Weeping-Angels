package mc.craig.software.angels.common.entity;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.misc.BodyRotationAngel;
import mc.craig.software.angels.util.ViewUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class AbstractWeepingAngel extends Monster implements Enemy {

    private static final EntityDataAccessor<Boolean> IS_SEEN = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TIME_VIEWED = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> PREVBLOCKPOS = SynchedEntityData.defineId(AbstractWeepingAngel.class, EntityDataSerializers.BLOCK_POS);

    public AbstractWeepingAngel(Level worldIn, EntityType<? extends Monster> entityType) {
        super(entityType, worldIn);
    }


    @Override
    public @NotNull AttributeMap getAttributes() {
        return new AttributeMap(createAttributes().build());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMonsterAttributes().
                add(Attributes.ATTACK_DAMAGE, 8D).
                add(Attributes.MAX_HEALTH, 50D).
                add(Attributes.KNOCKBACK_RESISTANCE, 1.0D).
                add(Attributes.MOVEMENT_SPEED, 0.8D).
                add(Attributes.ARMOR, 2.0D);
    }


    @Override
    public void tick() {
        super.tick();
        if (getSeenTime() == 0 || level.isEmptyBlock(blockPosition().below())) {
            setNoAi(false);
        }
    }

    @Override
    public void aiStep() {
        if (!getMainHandItem().isEmpty()) {
            setPersistenceRequired();
        }

        super.aiStep();
        if (!level.isClientSide) {
            List<Player> players = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(WAConfiguration.CONFIG.stalkRange.get()));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level != level);

            if (players.isEmpty()) {
                setSeenTime(0);
                setSpeed(0.5F);
            } else {
                Player targetPlayer = null;
                for (Player player : players) {
                    if (ViewUtil.isInSight(player, this) && isOnGround()) {
                        setSeenTime(getSeenTime() + 1);
                        invokeSeen(player);
                        return;
                    }
                    if (targetPlayer == null) {
                        targetPlayer = player;
                        setSeenTime(0);
                        setSpeed(0.5F);
                    }
                }

                if (isSeen() || targetPlayer.isCreative()) return;
                snapLookToPlayer(targetPlayer);
                moveTowards(targetPlayer);
            }
        }
    }

    private void snapLookToPlayer(Player targetPlayer) {
        Vec3 vecPos = position();
        Vec3 vecPlayerPos = targetPlayer.position();
        float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
        yHeadRot = yBodyRot = angle > 180 ? angle : angle + 90;
    }

    public void moveTowards(LivingEntity targetPlayer) {
        getNavigation().moveTo(targetPlayer, getSpeed());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(IS_SEEN, false);
        getEntityData().define(TIME_VIEWED, 0);
        getEntityData().define(PREVBLOCKPOS, BlockPos.ZERO);
    }

    @Override
    public void deserializeNBT(CompoundTag compound) {
        super.deserializeNBT(compound);
        if (compound.contains(WAConstants.TIME_SEEN)) setSeenTime(compound.getInt(WAConstants.TIME_SEEN));
        if (compound.contains(WAConstants.PREVPOS)) setPrevPos(getPrevPos());
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean(WAConstants.IS_SEEN, isSeen());
        compound.putInt(WAConstants.TIME_SEEN, getSeenTime());
        compound.putLong(WAConstants.PREVPOS, getPrevPos().asLong());
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

    public boolean isSeen() {
        return getSeenTime() > 0;
    }

    public int getSeenTime() {
        return getEntityData().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getEntityData().set(TIME_VIEWED, time);
    }

    public BlockPos getPrevPos() {
        return getEntityData().get(PREVBLOCKPOS);
    }

    public void setPrevPos(BlockPos pos) {
        getEntityData().set(PREVBLOCKPOS, pos);
    }

    @Override
    protected @NotNull BodyRotationControl createBodyControl() {
        return new BodyRotationAngel(this);
    }

    public void invokeSeen(Player player) {
        getNavigation().moveTo((Path) null, 0);
        setNoAi(true);
    }

    @Override
    protected boolean isImmobile() {
        return getSeenTime() > 0;
    }
}
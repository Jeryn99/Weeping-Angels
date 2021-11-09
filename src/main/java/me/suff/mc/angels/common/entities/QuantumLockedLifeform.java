package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.misc.WAConstants;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.conversion.AngelInfection;
import me.suff.mc.angels.conversion.AngelVirus;
import me.suff.mc.angels.utils.ViewUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class QuantumLockedLifeform extends Monster implements Enemy {

    private static final EntityDataAccessor<Boolean> IS_SEEN = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> TIME_VIEWED = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> PREVBLOCKPOS = SynchedEntityData.defineId(QuantumLockedLifeform.class, EntityDataSerializers.BLOCK_POS);

    public QuantumLockedLifeform(Level worldIn, EntityType<? extends Monster> entityType) {
        super(entityType, worldIn);
    }

    @Override
    public AttributeMap getAttributes() {
        return new AttributeMap(WeepingAngel.createAttributes().build());
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
            List<Player> players = level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(WAConfig.CONFIG.stalkRange.get()));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level != level);

            if (WAConfig.CONFIG.freezeOnAngel.get()) {
                List<WeepingAngel> angels = level.getEntitiesOfClass(WeepingAngel.class, getBoundingBox().inflate(WAConfig.CONFIG.stalkRange.get()));
                for (WeepingAngel angel : angels) {
                    if (angel.getUUID() != getUUID() && ViewUtil.isInSight(angel, this) && isOnGround()) {
                        setSeenTime(getSeenTime() + 1);
                        setNoAi(true);
                        return;
                    }
                }
            }

            if (players.isEmpty()) {
                setSeenTime(0);
                setSpeed(0.5F);
            } else {
                Player targetPlayer = null;
                for (Player player : players) {
                    if (ViewUtil.isInSight(player, this) && isOnGround()) {
                        setSeenTime(getSeenTime() + 1);
                        invokeSeen(player);
                        AngelInfection.get(player).ifPresent(AngelVirus::tickCounter);
                        return;
                    }
                    AngelInfection.get(player).ifPresent((angelVirus -> {
                        angelVirus.infect(false);
                    }));

                    if (targetPlayer == null) {
                        targetPlayer = player;
                        setSeenTime(0);
                        setSpeed(0.5F);
                    }
                }

                if (isSeen() || !WAConfig.CONFIG.aggroCreative.get() && targetPlayer.isCreative()) return;
                snapLookToPlayer(targetPlayer);
                if (distanceTo(targetPlayer) < 2) {
                    if (random.nextInt(50) < 30) {
                        doHurtTarget(targetPlayer);
                    }
                } else
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
    public void addAdditionalSaveData(CompoundTag compound) {
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

    public void invokeSeen(Player player) {
        getNavigation().moveTo((Path) null, 0);
        setNoAi(true);
    }

    @Override
    protected boolean isImmobile() {
        return getSeenTime() > 0;
    }
}

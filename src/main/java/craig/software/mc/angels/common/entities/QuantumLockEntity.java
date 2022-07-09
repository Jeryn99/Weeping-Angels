package craig.software.mc.angels.common.entities;

import java.util.List;

import craig.software.mc.angels.common.misc.WAConstants;
import craig.software.mc.angels.config.WAConfig;
import craig.software.mc.angels.utils.ViewUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class QuantumLockEntity extends MonsterEntity implements IMob {

    private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.defineId(QuantumLockEntity.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.defineId(QuantumLockEntity.class, DataSerializers.INT);
    private static final DataParameter<BlockPos> PREVBLOCKPOS = EntityDataManager.defineId(QuantumLockEntity.class, DataSerializers.BLOCK_POS);

    public QuantumLockEntity(World worldIn, EntityType<? extends MonsterEntity> entityType) {
        super(entityType, worldIn);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!level.isClientSide) {
            List<PlayerEntity> players = level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(WAConfig.CONFIG.stalkRange.get()));
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.level != level);

            if (WAConfig.CONFIG.freezeOnAngel.get()) {
                List<WeepingAngelEntity> angels = level.getEntitiesOfClass(WeepingAngelEntity.class, getBoundingBox().inflate(WAConfig.CONFIG.stalkRange.get()));
                for (WeepingAngelEntity angel : angels) {
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
                PlayerEntity targetPlayer = null;
                for (PlayerEntity player : players) {
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

                if (isSeen() || !WAConfig.CONFIG.aggroCreative.get() && targetPlayer.isCreative()) return;
                snapLookToPlayer(targetPlayer);
                if (distanceTo(targetPlayer) < 2)
                    doHurtTarget(targetPlayer);
                else
                    moveTowards(targetPlayer);
            }
        }
    }

    private void snapLookToPlayer(PlayerEntity targetPlayer) {
        Vector3d vecPos = position();
        Vector3d vecPlayerPos = targetPlayer.position();
        float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
        yHeadRot = yRot = angle > 180 ? angle : angle + 90;
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
    public void deserializeNBT(CompoundNBT compound) {
        super.deserializeNBT(compound);
        if (compound.contains(WAConstants.TIME_SEEN)) setSeenTime(compound.getInt(WAConstants.TIME_SEEN));
        if (compound.contains(WAConstants.PREVPOS)) setPrevPos(getPrevPos());
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
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

    public void invokeSeen(PlayerEntity player) {
        getNavigation().moveTo((Path) null, 0);
        setNoAi(true);
    }

    @Override
    protected boolean isImmobile() {
        return getSeenTime() > 0;
    }
}

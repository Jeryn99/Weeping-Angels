package me.suff.mc.angels.common.entity;

import me.suff.mc.angels.util.Constants;
import me.suff.mc.angels.util.ViewUtil;
import me.suff.mc.angels.util.WAConfig;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class QuantumLockBaseEntity extends PathAwareEntity {

    private static final TrackedData< Boolean > IS_SEEN = DataTracker.registerData(QuantumLockBaseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData< Integer > TIME_VIEWED = DataTracker.registerData(QuantumLockBaseEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData< BlockPos > PREVBLOCKPOS = DataTracker.registerData(QuantumLockBaseEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);

    public QuantumLockBaseEntity(World worldIn, EntityType< ? extends PathAwareEntity > entityType) {
        super(entityType, worldIn);
    }

    @Override
    public void tick() {
        super.tick();

        headYaw = yaw;
        if (!world.isClient && age % 5 == 0) {
            List< PlayerEntity > players = world.getEntitiesByClass(PlayerEntity.class, getBoundingBox().contract(WAConfig.AngelBehaviour.stalkRange.getValue()), LivingEntity::isAlive);
            players.removeIf(player -> player.isSpectator() || player.isInvisible() || player.isSleeping() || player.world != world);

            if (players.isEmpty()) {
                setSeenTime(0);
            } else {
                PlayerEntity targetPlayer = null;
                for (PlayerEntity player : players) {
                    if (ViewUtil.isInSight(player, this)/* && !AngelUtils.isDarkForPlayer(this, player)*/ && isOnGround()) {
                        setSeenTime(getSeenTime() + 1);
                        if (getSeenTime() == 1) {
                            invokeSeen(player);
                        }
                        return;
                    } else if (targetPlayer == null) {
                        targetPlayer = player;
                        setSeenTime(0);
                    }
                }

                Vec3d vecPos = getPos();
                Vec3d vecPlayerPos = targetPlayer.getPos();
                float angle = (float) Math.toDegrees((float) Math.atan2(vecPos.z - vecPlayerPos.z, vecPos.x - vecPlayerPos.x));
                headYaw = yaw = angle > 180 ? angle : angle + 90;

                if (isSeen()) return;
                if (distanceTo(targetPlayer) < 2)
                    attackLivingEntity(targetPlayer);
                else
                    moveTowards(targetPlayer);
            }
        }

    }

    public void moveTowards(LivingEntity targetPlayer) {
        this.setMovementSpeed(1.2F);
        getNavigation().startMovingTo(targetPlayer, getMovementSpeed());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        getDataTracker().startTracking(IS_SEEN, false);
        getDataTracker().startTracking(TIME_VIEWED, 0);
        getDataTracker().startTracking(PREVBLOCKPOS, new BlockPos(0, 0, 0));
    }

    @Override
    public CompoundTag writeNbt(CompoundTag tag) {
        tag.putBoolean(Constants.IS_SEEN, isSeen());
        tag.putInt(Constants.TIME_SEEN, getSeenTime());
        tag.putLong(Constants.PREVPOS, getPrevPos().asLong());
        return super.writeNbt(tag);
    }

    @Override
    public void readNbt(CompoundTag tag) {
        super.readNbt(tag);
        if (tag.contains(Constants.TIME_SEEN)) setSeenTime(tag.getInt(Constants.TIME_SEEN));
        if (tag.contains(Constants.PREVPOS)) setPrevPos(getPrevPos());
    }

    @Override
    public boolean doesRenderOnFire() {
        return false;
    }

    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    public boolean isSeen() {
        return getSeenTime() > 0;
    }

    public int getSeenTime() {
        return getDataTracker().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getDataTracker().set(TIME_VIEWED, time);
    }

    public BlockPos getPrevPos() {
        return getDataTracker().get(PREVBLOCKPOS);
    }

    public void setPrevPos(BlockPos pos) {
        getDataTracker().set(PREVBLOCKPOS, pos);
    }

    public void invokeSeen(PlayerEntity player) {
        getNavigation().stop();
        setAiDisabled(true);
        this.lookAtEntity(player, 90.0F, 90.0F);
    }

}
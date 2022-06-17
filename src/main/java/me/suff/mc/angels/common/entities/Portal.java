package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class Portal extends Mob {

    private static final EntityDataAccessor<Integer> TIME_ALIVE = SynchedEntityData.defineId(Portal.class, EntityDataSerializers.INT);

    public Portal(Level worldIn) {
        super(WAObjects.EntityEntries.ANOMALY.get(), worldIn);
    }

    public Portal(EntityType<?> type, Level world) {
        this(world);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSourceIn) {
        return SoundEvents.PLAYER_ATTACK_WEAK;
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }

    public int lifeSpan() {
        return getEntityData().get(TIME_ALIVE);
    }

    public void setLifeSpan(int timeAlive) {
        getEntityData().set(TIME_ALIVE, timeAlive);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level.isClientSide) {

            setNoAi(true);
            if (tickCount == 1) {
                playSound(WAObjects.Sounds.TELEPORT.get(), 1.0F, 1.0F);
            }

            if (tickCount > lifeSpan()) {
                remove(RemovalReason.DISCARDED);
            }

            for (WeepingAngel weepingAngel : level.getEntitiesOfClass(WeepingAngel.class, getBoundingBox().inflate(10))) {
                BlockPos pos = blockPosition().subtract(weepingAngel.blockPosition());
                Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ()).normalize();
                weepingAngel.setNoAi(false);
                weepingAngel.setDeltaMovement(vec.scale(0.15D));
            }
        }
    }


    @Override
    protected void doPush(@NotNull Entity entityIn) {
        super.doPush(entityIn);

        if (entityIn instanceof WeepingAngel weepingAngel) {
            weepingAngel.setSilent(true);
            weepingAngel.hurt(WAObjects.GENERATOR, Integer.MAX_VALUE);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(TIME_ALIVE, 100);
    }
}

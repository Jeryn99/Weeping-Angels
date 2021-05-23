package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class AnomalyEntity extends MobEntity {

    private static final DataParameter<Integer> TIME_ALIVE = EntityDataManager.defineId(AnomalyEntity.class, DataSerializers.INT);

    public AnomalyEntity(World worldIn) {
        super(WAObjects.EntityEntries.ANOMALY.get(), worldIn);
    }

    public AnomalyEntity(EntityType<?> type, World world) {
        this(world);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
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
                remove();
            }

            for (WeepingAngelEntity weepingAngelEntity : level.getEntitiesOfClass(WeepingAngelEntity.class, getBoundingBox().inflate(10))) {
                BlockPos pos = blockPosition().subtract(weepingAngelEntity.blockPosition());
                Vector3d vec = new Vector3d(pos.getX(), pos.getY(), pos.getZ()).normalize();
                weepingAngelEntity.setNoAi(false);
                weepingAngelEntity.setDeltaMovement(vec.scale(0.15D));
            }
        }
    }


    @Override
    protected void doPush(Entity entityIn) {
        super.doPush(entityIn);

        if (entityIn instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) entityIn;
            weepingAngelEntity.setSilent(true);
            weepingAngelEntity.hurt(WAObjects.GENERATOR, Integer.MAX_VALUE);
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(TIME_ALIVE, 100);
    }
}

package mc.craig.software.angels.common.entity.anomaly;

import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.util.WADamageSources;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class AnomalyEntity extends Mob {

    public AnimationState SPIN_STATE = new AnimationState();
    private static final EntityDataAccessor<Integer> TIME_ALIVE = SynchedEntityData.defineId(AnomalyEntity.class, EntityDataSerializers.INT);

    public AnomalyEntity(EntityType<? extends Mob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(TIME_ALIVE, 100);
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

        if (tickCount == 1) {
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(blockPosition()));
            lightningbolt.setVisualOnly(true);
            level.addFreshEntity(lightningbolt);
        }
        if (!level.isClientSide) {
            setNoAi(true);
            if (tickCount > lifeSpan()) {
                remove(RemovalReason.DISCARDED);
            }

            for (WeepingAngel weepingAngel : level.getEntitiesOfClass(WeepingAngel.class, getBoundingBox().inflate(15))) {
                BlockPos pos = blockPosition().subtract(weepingAngel.blockPosition());
                Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ()).normalize();
                weepingAngel.setNoAi(false);
                weepingAngel.setDeltaMovement(vec.scale(0.15D));

                if(distanceTo(weepingAngel) < 2) {
                    weepingAngel.setSilent(true);
                    weepingAngel.hurt(WADamageSources.GENERATOR, Integer.MAX_VALUE);
                }

            }
        } else {
            if (!SPIN_STATE.isStarted()) {
                SPIN_STATE.start(tickCount);
            }
        }
    }


    @Override
    protected void doPush(@NotNull Entity entityIn) {

    }
}

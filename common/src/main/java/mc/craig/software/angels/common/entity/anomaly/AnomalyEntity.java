package mc.craig.software.angels.common.entity.anomaly;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.util.HurtHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
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

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
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
        setNoGravity(true);
        if (tickCount == 1) {
            LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(level);
            lightningbolt.moveTo(Vec3.atBottomCenterOf(blockPosition()));
            lightningbolt.setVisualOnly(true);
            level.addFreshEntity(lightningbolt);
            playSound(SoundEvents.END_PORTAL_SPAWN);
        }
            if (tickCount > lifeSpan()) {
                remove(RemovalReason.DISCARDED);
            }

            for (WeepingAngel weepingAngel : level.getEntitiesOfClass(WeepingAngel.class, getBoundingBox().inflate(64))) {
                BlockPos pos = blockPosition().subtract(weepingAngel.blockPosition());
                Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ()).normalize();
                weepingAngel.setNoAi(false);
                weepingAngel.setHooked(true);
                weepingAngel.setDeltaMovement(vec.scale(0.25D));

                for (int i = 0; i < 2; ++i) {
                    this.level.addParticle(ParticleTypes.ELECTRIC_SPARK, weepingAngel.getRandomX(0.5D), weepingAngel.getRandomY(), weepingAngel.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
                }

            }

        if (!SPIN_STATE.isStarted()) {
            SPIN_STATE.start(tickCount);
        }
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected boolean isImmobile() {
        return true;
    }


    @Override
    protected void doPush(@NotNull Entity entityIn) {
        if (WAConfiguration.CONFIG.hurtType.get() == HurtHelper.HurtType.GENERATOR || WAConfiguration.CONFIG.hurtType.get() == HurtHelper.HurtType.PICKAXE_AND_GENERATOR) {
            if (entityIn instanceof WeepingAngel weepingAngel) {
                weepingAngel.setSilent(true);
                weepingAngel.remove(RemovalReason.KILLED);
            }
        }
    }
}

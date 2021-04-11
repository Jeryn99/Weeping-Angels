package me.suff.mc.angels.common.entity;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.objects.WASounds;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortalEntity extends MobEntity {

    private static final TrackedData< Integer > TIME_ALIVE = DataTracker.registerData(PortalEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public PortalEntity(World worldIn) {
        super(WeepingAngels.PORTAL, worldIn);
    }

    public PortalEntity(EntityType< ? > type, World world) {
        this(world);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    public int getTimeAlive() {
        return getDataTracker().get(TIME_ALIVE);
    }

    public void setTimeAlive(int timeAlive) {
        getDataTracker().set(TIME_ALIVE, timeAlive);
    }

    @Override
    public void tick() {
        super.tick();

        if (!world.isClient) {

            setAiDisabled(true);
            if (age == 1) {
                playSound(WASounds.TELEPORT, 1.0F, 1.0F);
            }

            if (age > getTimeAlive()) {
                remove();
            }
        }

        if (!world.isClient && age % 5 == 0) {
            List< WeepingAngelEntity > list = getAngels();
            for (WeepingAngelEntity quantumLockBaseEntity : list) {
                quantumLockBaseEntity.setSilent(true);
                BlockPos pos = getBlockPos().subtract(quantumLockBaseEntity.getBlockPos());
                Vec3d vec = new Vec3d(pos.getX(), pos.getY(), pos.getZ()).normalize();
                quantumLockBaseEntity.setAiDisabled(false);
                quantumLockBaseEntity.setVelocity(vec.multiply(0.15D));
            }
        }
    }

    public List< WeepingAngelEntity > getAngels() {
        return world.getEntitiesByClass(WeepingAngelEntity.class, getBoundingBox().contract(16.0D), null);
    }

    @Override
    public boolean collidesWith(Entity other) {
        if (other instanceof WeepingAngelEntity) {
            WeepingAngelEntity weepingAngelEntity = (WeepingAngelEntity) other;
            weepingAngelEntity.setSilent(true);
            weepingAngelEntity.damage(DamageSource.OUT_OF_WORLD, Integer.MAX_VALUE);
        }
        return super.collidesWith(other);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        getDataTracker().startTracking(TIME_ALIVE, 100);
    }

}
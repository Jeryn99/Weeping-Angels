package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

/**
 * Created by Craig on 06/10/2019 @ 12:17
 */
@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class ChronodyneGeneratorProjectile extends ThrowableItemProjectile implements ItemSupplier {

    ItemStack stack = new ItemStack(WAObjects.Items.CHRONODYNE_GENERATOR.get());

    public ChronodyneGeneratorProjectile(EntityType<? extends ThrowableItemProjectile> type, Level worldIn) {
        super(type, worldIn);
    }

    public ChronodyneGeneratorProjectile(EntityType<? extends ThrowableItemProjectile> type, double x, double y, double z, Level worldIn) {
        super(type, x, y, z, worldIn);
    }

    public ChronodyneGeneratorProjectile(EntityType<? extends ThrowableItemProjectile> type, LivingEntity livingEntityIn, Level worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public ChronodyneGeneratorProjectile(Level world) {
        this(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), world);
    }

    @Override
    protected void onHit(HitResult result) {

        if (result == null || !isAlive()) return;

        // Entity Hit
        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = ((EntityHitResult) result);
            if (entityHitResult == null) return;
            Entity hitEntity = entityHitResult.getEntity();
            if (hitEntity instanceof WeepingAngel) {
                if (!level.isClientSide) {
                    Portal a = new Portal(level);
                    a.copyPosition(hitEntity);
                    level.addFreshEntity(a);
                    remove(RemovalReason.KILLED);
                }
            }
        }

        if (result.getType() == HitResult.Type.BLOCK) {
            if (!level.isClientSide) {
                BlockHitResult blockRayTraceResult = (BlockHitResult) result;
                BlockPos pos = new BlockPos(blockRayTraceResult.getBlockPos().getX(), blockRayTraceResult.getBlockPos().getY() + 1, blockRayTraceResult.getBlockPos().getZ());
                if (level.isEmptyBlock(pos) && !level.isEmptyBlock(pos.below()) || level.getBlockState(pos).getMaterial().equals(Material.PLANT)) {
                    level.setBlockAndUpdate(pos, WAObjects.Blocks.CHRONODYNE_GENERATOR.get().defaultBlockState());
                } else {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(WAObjects.Blocks.CHRONODYNE_GENERATOR.get()));
                }
                level.broadcastEntityEvent(this, (byte) 3);
                remove(RemovalReason.KILLED);
            }
        }

    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected Item getDefaultItem() {
        return stack.getItem();
    }

    @Override
    public ItemStack getItem() {
        return stack;
    }
}

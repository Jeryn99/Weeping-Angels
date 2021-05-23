package me.suff.mc.angels.common.entities;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Created by Craig on 06/10/2019 @ 12:17
 */
@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class ChronodyneGeneratorEntity extends ProjectileItemEntity implements IRendersAsItem {

    ItemStack stack = new ItemStack(WAObjects.Items.CHRONODYNE_GENERATOR.get());

    public ChronodyneGeneratorEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ChronodyneGeneratorEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public ChronodyneGeneratorEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn) {
        super(type, livingEntityIn, worldIn);
    }

    public ChronodyneGeneratorEntity(World world) {
        this(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), world);
    }

    @Override
    protected void onHit(RayTraceResult result) {

        if (result == null || !isAlive()) return;

        // Entity Hit
        if (result.getType() == RayTraceResult.Type.ENTITY) {
            EntityRayTraceResult entityHitResult = ((EntityRayTraceResult) result);
            if (entityHitResult == null) return;
            Entity hitEntity = entityHitResult.getEntity();
            if (hitEntity instanceof WeepingAngelEntity) {
                if (!level.isClientSide) {
                    AnomalyEntity a = new AnomalyEntity(level);
                    a.copyPosition(hitEntity);
                    level.addFreshEntity(a);
                    remove();
                }
            }
        }

        if (result.getType() == RayTraceResult.Type.BLOCK) {
            if (!level.isClientSide) {
                BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) result;
                BlockPos pos = new BlockPos(blockRayTraceResult.getBlockPos().getX(), blockRayTraceResult.getBlockPos().getY() + 1, blockRayTraceResult.getBlockPos().getZ());
                if (level.isEmptyBlock(pos) && !level.isEmptyBlock(pos.below()) || level.getBlockState(pos).getMaterial().equals(Material.PLANT)) {
                    level.setBlockAndUpdate(pos, WAObjects.Blocks.CHRONODYNE_GENERATOR.get().defaultBlockState());
                } else {
                    InventoryHelper.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(WAObjects.Blocks.CHRONODYNE_GENERATOR.get()));
                }
                level.broadcastEntityEvent(this, (byte) 3);
                remove();
            }
        }

    }

    @Override
    public boolean isInWater() {
        return false;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
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

package me.swirtzly.minecraft.angels.common.entities;

import me.swirtzly.minecraft.angels.common.WAObjects;
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
 * Created by Swirtzly on 06/10/2019 @ 12:17
 */
@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class ChronodyneGeneratorEntity extends ProjectileItemEntity implements IRendersAsItem {


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
	protected void onImpact(RayTraceResult result) {

		if (result == null || !isAlive()) return;
		
		// Entity Hit
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			EntityRayTraceResult entityHitResult = ((EntityRayTraceResult) result);
			if (entityHitResult == null) return;
			Entity hitEntity = entityHitResult.getEntity();
			if (hitEntity instanceof WeepingAngelEntity) {
				if (!world.isRemote) {
					AnomalyEntity a = new AnomalyEntity(world);
					a.setEntityEyeHeight(hitEntity.getEyeHeight());
					a.copyLocationAndAnglesFrom(hitEntity);
					world.addEntity(a);
					remove();
				}
			}
			
		}

		if (result.getType() == RayTraceResult.Type.BLOCK) {
			if (!world.isRemote) {
				BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) result;
				BlockPos pos = new BlockPos(blockRayTraceResult.getPos().getX(), blockRayTraceResult.getPos().getY() + 1, blockRayTraceResult.getPos().getZ());
				if (world.isAirBlock(pos) && !world.isAirBlock(pos.down()) || world.getBlockState(pos).getMaterial().equals(Material.PLANTS)) {
					world.setBlockState(pos, WAObjects.Blocks.CG.get().getDefaultState());
				} else {
					InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(WAObjects.Blocks.CG.get()));
				}
				world.setEntityState(this, (byte) 3);
				remove();
			}
		}
		
	}

	@Override
	public boolean isInWater() {
		return false;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	ItemStack stack = new ItemStack(WAObjects.Items.CHRONODYNE_GENERATOR.get());

	@Override
	protected Item getDefaultItem() {
		return stack.getItem();
	}

	@Override
	public ItemStack getItem() {
		return stack;
	}
}

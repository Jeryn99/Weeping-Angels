package me.swirtzly.minecraft.angels.common.entities;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.misc.WAConstants;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Created by Swirtzly on 06/10/2019 @ 12:17
 */
@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem.class)
public class ChronodyneGeneratorEntity extends ThrowableEntity implements IRendersAsItem {
	
	public ChronodyneGeneratorEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public ChronodyneGeneratorEntity(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}
	
	public ChronodyneGeneratorEntity(EntityType<? extends ThrowableEntity> type, LivingEntity livingEntityIn, World worldIn) {
		super(type, livingEntityIn, worldIn);
	}
	
	public ChronodyneGeneratorEntity(World world) {
		this(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), world);
	}
	
	@Override
	public void tick() {
		double speed = new Vector3d(getPosX(), getPosY(), getPosZ()).distanceTo(new Vector3d(prevPosX, prevPosY, prevPosZ));
		if (!this.world.isRemote && (ticksExisted > 30 * 20 || speed < 0.01)) {
			this.remove();
		}
		
		if (this.isAlive()) super.tick();
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
					WeepingAngelEntity angel = (WeepingAngelEntity) hitEntity;
					AnomalyEntity a = new AnomalyEntity(world);
					a.setEntityEyeHeight(hitEntity.getEyeHeight());
					a.copyLocationAndAnglesFrom(hitEntity);
					world.addEntity(a);
					angel.dropAngelStuff();
					angel.remove();
					remove();
				}
			}
			
		}
		
		if (result.getType() == RayTraceResult.Type.BLOCK) {
			BlockRayTraceResult blockRayTraceResult = (BlockRayTraceResult) result;
			BlockPos pos = new BlockPos(blockRayTraceResult.getPos().getX(), blockRayTraceResult.getPos().getY() + 1, blockRayTraceResult.getPos().getZ());
			if (world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().equals(Material.PLANTS)) {
				world.setBlockState(pos, WAObjects.Blocks.CG.get().getDefaultState());
				if (world.getTileEntity(pos) != null) {
					CompoundNBT tileData = world.getTileEntity(pos).getTileData();
					tileData.putDouble(WAConstants.ABS_X, getPosX());
					tileData.putDouble(WAConstants.ABS_Y, getPosY());
					tileData.putDouble(WAConstants.ABS_Z, getPosZ());
					remove();
				}
			}
		}
		
		if (!world.isRemote) {
			world.setEntityState(this, (byte) 3);
			remove();
		}
		
	}
	
	@Override
	protected void registerData() {
		
	}
	
	@Override
	public boolean isInWater() {
		return false;
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public ItemStack getItem() {
		return ItemStack.EMPTY;
	}
}

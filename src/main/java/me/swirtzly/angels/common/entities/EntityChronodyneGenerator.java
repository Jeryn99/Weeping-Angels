package me.swirtzly.angels.common.entities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.misc.WAConstants;
import net.minecraft.block.material.Material;
import net.minecraft.command.impl.TagCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Created by Swirtzly
 * on 06/10/2019 @ 12:17
 */
public class EntityChronodyneGenerator extends ThrowableEntity implements IRendersAsItem {

	public EntityChronodyneGenerator(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public EntityChronodyneGenerator(EntityType<? extends ThrowableEntity> type, double x, double y, double z, World worldIn) {
		super(type, x, y, z, worldIn);
	}


	public EntityChronodyneGenerator(EntityType<? extends ThrowableEntity> type, LivingEntity livingEntityIn, World worldIn) {
		super(type, livingEntityIn, worldIn);
	}


	public EntityChronodyneGenerator(World world) {
		this(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), world);
	}


	@Override
	public void tick() {
		double speed = new Vec3d(posX, posY, posZ).distanceTo(new Vec3d(prevPosX, prevPosY, prevPosZ));
		if (!this.world.isRemote && (ticksExisted > 30 * 20 || speed < 0.01)) {
			this.remove();
		}



		if (this.isAlive())
			super.tick();
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result == null || !isAlive())
			return;

		//Entity Hit
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			EntityRayTraceResult entityHitResult = ((EntityRayTraceResult) result);
			if (entityHitResult.getEntity() == this.getThrower() || entityHitResult == null) return;
			Entity hitEntity = entityHitResult.getEntity();
			if (hitEntity instanceof EntityWeepingAngel) {
				if (!world.isRemote) {
					EntityAnomaly a = new EntityAnomaly(world);
					a.setEntityEyeHeight(hitEntity.getEyeHeight());
					a.copyLocationAndAnglesFrom(hitEntity);
					world.addEntity(a);
					hitEntity.remove();
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
					tileData.putDouble(WAConstants.ABS_X, posX);
					tileData.putDouble(WAConstants.ABS_Y, posY);
					tileData.putDouble(WAConstants.ABS_Z, posZ);
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

package me.suff.angels.common.entities;

import me.suff.angels.common.WAObjects;
import me.suff.angels.common.misc.WAConstants;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Particles;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class EntityChronodyneGenerator extends EntityThrowable {
	
	public EntityChronodyneGenerator(World worldIn) {
		super(worldIn);
	}
	
	public EntityChronodyneGenerator(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		shoot(throwerIn, throwerIn.rotationPitch, throwerIn.rotationYawHead, 0, 1.5F, 1.0F);
	}
	
	public EntityChronodyneGenerator(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	/**
	 * Handler for {@link World#setEntityState}
	 */
	@Override
	public void handleStatusUpdate(byte id) {
		
	}
	
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {
		
		if (result.type == Type.BLOCK) {
			BlockPos pos = new BlockPos(result.getBlockPos().getX(), result.getBlockPos().getY() + 1, result.getBlockPos().getZ());
			if (world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().equals(Material.PLANTS)) {
				world.setBlockState(pos, WAObjects.Blocks.CG.getDefaultState());
				if (world.getTileEntity(pos) != null) {
					NBTTagCompound tileData = world.getTileEntity(pos).getTileData();
					tileData.setDouble(WAConstants.ABS_X, posX);
					tileData.setDouble(WAConstants.ABS_Y, posY);
					tileData.setDouble(WAConstants.ABS_Z, posZ);
					remove();
				}
			}
		}
		
		if (result.entity instanceof EntityWeepingAngel) {
			if (world.isRemote) {
				world.spawnParticle(Particles.EXPLOSION, getPosition().getX(), getPosition().getY(), getPosition().getZ(), 1.0D, 0.0D, 0.0D);
			}
			
			if (!world.isRemote) {
				
				EntityAnomaly a = new EntityAnomaly(world);
				a.setEntityEyeHeight(result.entity.getEyeHeight());
				a.copyLocationAndAnglesFrom(result.entity);
				world.spawnEntity(a);
				
				result.entity.remove();
				remove();
			}
		}
		
		if (!world.isRemote) {
			world.setEntityState(this, (byte) 3);
			remove();
		}
	}
}

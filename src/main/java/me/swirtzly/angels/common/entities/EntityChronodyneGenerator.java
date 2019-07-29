package me.swirtzly.angels.common.entities;

import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.misc.WAConstants;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		
	}
	
	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result) {
		
		if (result.typeOfHit == Type.BLOCK) {
			BlockPos pos = new BlockPos(result.getBlockPos().getX(), result.getBlockPos().getY() + 1, result.getBlockPos().getZ());
			if (world.isAirBlock(pos) || world.getBlockState(pos).getMaterial().equals(Material.PLANTS)) {
				world.setBlockState(pos, WAObjects.Blocks.CG.getDefaultState());
				if (world.getTileEntity(pos) != null) {
					NBTTagCompound tileData = world.getTileEntity(pos).getTileData();
					tileData.setDouble(WAConstants.ABS_X, posX);
					tileData.setDouble(WAConstants.ABS_Y, posY);
					tileData.setDouble(WAConstants.ABS_Z, posZ);
					setDead();
				}
			}
		}
		
		if (result.entityHit instanceof EntityWeepingAngel) {
			if (world.isRemote) {
				world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, getPosition().getX(), getPosition().getY(), getPosition().getZ(), 1.0D, 0.0D, 0.0D);
			}
			
			if (!world.isRemote) {
				
				EntityAnomaly a = new EntityAnomaly(world);
				a.setEntityEyeHeight(result.entityHit.getEyeHeight());
				a.copyLocationAndAnglesFrom(result.entityHit);
				world.spawnEntity(a);
				
				result.entityHit.setDead();
				setDead();
			}
		}
		
		if (!world.isRemote) {
			world.setEntityState(this, (byte) 3);
			setDead();
		}
	}
}

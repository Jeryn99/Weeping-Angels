package me.sub.angels.common.entities;

import me.sub.angels.common.WAObjects;
import me.sub.angels.main.WAConstants;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
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
		this.shoot(throwerIn, throwerIn.rotationPitch, throwerIn.rotationYawHead, 0, 1.5F, 1.0F);
	}
	
	public EntityChronodyneGenerator(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	public static void registerFixesGen(DataFixer fixer) {
		EntityThrowable.registerFixesThrowable(fixer, "gen");
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
			if (world.isAirBlock(pos)) {
				world.setBlockState(pos, WAObjects.WABlocks.CG.getDefaultState());
				if (world.getTileEntity(pos) != null) {
					world.getTileEntity(pos).getTileData().setDouble(WAConstants.ABS_X, posX);
					world.getTileEntity(pos).getTileData().setDouble(WAConstants.ABS_Y, posY);
					world.getTileEntity(pos).getTileData().setDouble(WAConstants.ABS_Z, posZ);
					this.setDead();
				}
			}
		}
		
		if (result.entityHit instanceof EntityAngel) {
			if (world.isRemote) {
				this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ(), 1.0D, 0.0D, 0.0D);
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
		
		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte) 3);
			this.setDead();
		}
	}
}

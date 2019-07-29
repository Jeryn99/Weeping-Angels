package me.swirtzly.angels.utils;

import me.swirtzly.angels.common.entities.EntityQuantumLockBase;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ViewUtil {
	
	public static boolean isInFrontOfEntity(Entity entity, Entity target, boolean vr) {
		Vec3d vecTargetsPos = target.getPositionVector();
		Vec3d vecLook;
		
		if (vr) {
			if (entity instanceof EntityPlayer) {
				vecLook = CommonProxy.reflector.getHMDRot((EntityPlayer) entity);
			} else {
				throw new RuntimeException("Attempted to use a non-player entity with VRSupport: " + entity.getEntityData());
			}
		} else {
			vecLook = entity.getLookVec();
		}
		
		Vec3d vecFinal = vecTargetsPos.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
		vecFinal = new Vec3d(vecFinal.x, 0.0D, vecFinal.z);
		return vecFinal.dotProduct(vecLook) < 0.0;
	}
	
	
	public static boolean viewBlocked(EntityLivingBase viewer, EntityLivingBase angel) {
		AxisAlignedBB viewerBoundBox = viewer.getEntityBoundingBox();
		AxisAlignedBB angelBoundingBox = angel.getEntityBoundingBox();
		Vec3d[] viewerPoints = {new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};
		Vec3d[] angelPoints = {new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.minZ),};
		
		for (int i = 0; i < viewerPoints.length; i++) {
			if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], false, true, false) == null) return false;
			if (rayTraceBlocks(viewer.world, viewerPoints[i], angelPoints[i], pos -> {
				IBlockState state = viewer.world.getBlockState(pos);
				for (String transparent_block : WAConfig.angels.transparent_blocks)
					if (state.getBlock().getRegistryName().toString().equals(transparent_block)) return false;
				return state.getMaterial() != Material.GLASS && state.getMaterial() != Material.PORTAL && state.getMaterial() != Material.ICE &&
						!(state.getBlock() instanceof BlockPane) && !(state.getBlock() instanceof BlockVine) && !(state.getBlock() instanceof BlockLeaves) &&
						state.getCollisionBoundingBox(viewer.world, pos) != Block.NULL_AABB && state.getBlock().canCollideCheck(state, false);
			}) == null) return false;
		}
		
		if (angel.ticksExisted % 1200 == 0) {
			if (angel.getDistance(viewer) < 15) {
				viewer.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 15));
			}
		}
		
		return true;
	}
	
	/**
	 * Slightly modified from {@link World#rayTraceBlocks(Vec3d, Vec3d, boolean, boolean, boolean)} to take a predicate as an argument
	 */
	@Nullable
	private static RayTraceResult rayTraceBlocks(World world, Vec3d vec31, Vec3d vec32, Predicate<BlockPos> stopOn) {
		if (!Double.isNaN(vec31.x) && !Double.isNaN(vec31.y) && !Double.isNaN(vec31.z)) {
			if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
				int i = MathHelper.floor(vec32.x);
				int j = MathHelper.floor(vec32.y);
				int k = MathHelper.floor(vec32.z);
				int l = MathHelper.floor(vec31.x);
				int i1 = MathHelper.floor(vec31.y);
				int j1 = MathHelper.floor(vec31.z);
				BlockPos blockpos = new BlockPos(l, i1, j1);
				IBlockState iblockstate = world.getBlockState(blockpos);
				
				if (stopOn.test(blockpos)) {
					RayTraceResult raytraceresult = iblockstate.collisionRayTrace(world, blockpos, vec31, vec32);
					
					if (raytraceresult != null) {
						return raytraceresult;
					}
				}
				
				int k1 = 200;
				
				while (k1-- >= 0) {
					if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
						return null;
					}
					
					if (l == i && i1 == j && j1 == k) {
						return null;
					}
					
					boolean flag2 = true;
					boolean flag = true;
					boolean flag1 = true;
					double d0 = 999.0D;
					double d1 = 999.0D;
					double d2 = 999.0D;
					
					if (i > l) {
						d0 = (double) l + 1.0D;
					} else if (i < l) {
						d0 = (double) l + 0.0D;
					} else {
						flag2 = false;
					}
					
					if (j > i1) {
						d1 = (double) i1 + 1.0D;
					} else if (j < i1) {
						d1 = (double) i1 + 0.0D;
					} else {
						flag = false;
					}
					
					if (k > j1) {
						d2 = (double) j1 + 1.0D;
					} else if (k < j1) {
						d2 = (double) j1 + 0.0D;
					} else {
						flag1 = false;
					}
					
					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					double d6 = vec32.x - vec31.x;
					double d7 = vec32.y - vec31.y;
					double d8 = vec32.z - vec31.z;
					
					if (flag2) {
						d3 = (d0 - vec31.x) / d6;
					}
					
					if (flag) {
						d4 = (d1 - vec31.y) / d7;
					}
					
					if (flag1) {
						d5 = (d2 - vec31.z) / d8;
					}
					
					if (d3 == -0.0D) {
						d3 = -1.0E-4D;
					}
					
					if (d4 == -0.0D) {
						d4 = -1.0E-4D;
					}
					
					if (d5 == -0.0D) {
						d5 = -1.0E-4D;
					}
					
					EnumFacing enumfacing;
					
					if (d3 < d4 && d3 < d5) {
						enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
						vec31 = new Vec3d(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
					} else if (d4 < d5) {
						enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
						vec31 = new Vec3d(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
					} else {
						enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
						vec31 = new Vec3d(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
					}
					
					l = MathHelper.floor(vec31.x) - (enumfacing == EnumFacing.EAST ? 1 : 0);
					i1 = MathHelper.floor(vec31.y) - (enumfacing == EnumFacing.UP ? 1 : 0);
					j1 = MathHelper.floor(vec31.z) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
					blockpos = new BlockPos(l, i1, j1);
					if (stopOn.test(blockpos)) {
						RayTraceResult raytraceresult1 = world.getBlockState(blockpos).collisionRayTrace(world, blockpos, vec31, vec32);
						
						if (raytraceresult1 != null) {
							return raytraceresult1;
						}
					}
				}
			}
		}
		
		return null;
	}
	
	
	/**
	 * Method that detects whether a entity is the the view sight of another entity
	 *
	 * @param viewer      The viewer entity
	 * @param beingViewed The entity being watched by viewer
	 */
	public static boolean canEntitySee(EntityLivingBase viewer, EntityLivingBase beingViewed) {
		double dx = beingViewed.posX - viewer.posX;
		double dz;
		for (dz = beingViewed.posX - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		
		return yaw < 60 && yaw > -60 && viewer.canEntityBeSeen(beingViewed);
	}
	
	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param viewer The viewer entity
	 * @param tile   The tile being watched by viewer
	 */
	public static boolean isInSightTile(EntityLivingBase viewer, TileEntity tile) {
		double dx = tile.getPos().getX() - viewer.posX;
		double dz;
		for (dz = tile.getPos().getX() - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		
		return yaw < 60 && yaw > -60;
	}
	
	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param livingBase The viewer entity
	 * @param angel      The entity being watched by viewer
	 */
	public static boolean isInSight(EntityLivingBase livingBase, EntityQuantumLockBase angel) {
		if (viewBlocked(livingBase, angel)) return false;
		
		if (livingBase instanceof EntityPlayer) {
			return isInFrontOfEntity(livingBase, angel, CommonProxy.reflector.isVRPlayer((EntityPlayer) livingBase));
		}
		return isInFrontOfEntity(livingBase, angel, false);
	}
	
}

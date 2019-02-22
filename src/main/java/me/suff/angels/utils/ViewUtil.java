package me.suff.angels.utils;

import me.suff.angels.common.entities.EntityQuantumLockBase;
import me.suff.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ViewUtil {
	
	public static boolean isInFrontOfEntity(Entity entity, Entity target, boolean vr) {
		Vec3d vecTargetsPos = target.getPositionVector();
		Vec3d vecLook;
		vecLook = entity.getLookVec();
		Vec3d vecFinal = vecTargetsPos.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
		vecFinal = new Vec3d(vecFinal.x, 0.0D, vecFinal.z);
		return vecFinal.dotProduct(vecLook) < 0.0;
	}
	
	
	public static boolean viewBlocked(EntityLivingBase viewer, EntityLivingBase angel) {
		AxisAlignedBB viewerBoundBox = viewer.getBoundingBox();
		AxisAlignedBB angelBoundingBox = angel.getBoundingBox();
		Vec3d[] viewerPoints = {new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};
		Vec3d[] angelPoints = {new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.minZ),};
		
		for (int i = 0; i < viewerPoints.length; i++) {
			if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], RayTraceFluidMode.NEVER, true, false) == null)
				return false;
			if (rayTraceBlocks(viewer.world, viewerPoints[i], angelPoints[i], RayTraceFluidMode.NEVER, false, false, pos -> {
				IBlockState state = viewer.world.getBlockState(pos);
				for (String transparent_block : WAConfig.CONFIG.transparent_blocks.get())
					if (state.getBlock().getRegistryName().toString().equals(transparent_block)) return false;
				return state.getMaterial() != Material.GLASS && state.getMaterial() != Material.PORTAL && state.getMaterial() != Material.ICE &&
						!(state.getBlock() instanceof BlockPane) && !(state.getBlock() instanceof BlockVine) && !(state.getBlock() instanceof BlockLeaves) &&
						state.getCollisionShape(viewer.world, pos) != VoxelShapes.empty() && state.getBlock().isCollidable(state);
			}) == null) return false;
		}
		return true;
	}
	
	@Nullable
	public static RayTraceResult rayTraceBlocks(World world, Vec3d start, Vec3d end, RayTraceFluidMode fluidMode, boolean p_200259_4_, boolean p_200259_5_, Predicate<BlockPos> stopOn) {
		double d0 = start.x;
		double d1 = start.y;
		double d2 = start.z;
		if (!Double.isNaN(d0) && !Double.isNaN(d1) && !Double.isNaN(d2)) {
			if (!Double.isNaN(end.x) && !Double.isNaN(end.y) && !Double.isNaN(end.z)) {
				int i = MathHelper.floor(end.x);
				int j = MathHelper.floor(end.y);
				int k = MathHelper.floor(end.z);
				int l = MathHelper.floor(d0);
				int i1 = MathHelper.floor(d1);
				int j1 = MathHelper.floor(d2);
				BlockPos blockpos = new BlockPos(l, i1, j1);
				
				IBlockState iblockstate = world.getBlockState(blockpos);
				
				if (stopOn.test(blockpos)) {
					RayTraceResult raytraceresult = Block.collisionRayTrace(iblockstate, world, blockpos, start, end);
					if (raytraceresult != null) {
						return raytraceresult;
					}
				}
				
				IFluidState ifluidstate = world.getFluidState(blockpos);
				if (!p_200259_4_ || !iblockstate.getCollisionShape(world, blockpos).isEmpty()) {
					boolean flag = iblockstate.getBlock().isCollidable(iblockstate);
					boolean flag1 = fluidMode.predicate.test(ifluidstate);
					if (flag || flag1) {
						RayTraceResult raytraceresult = null;
						
						
						if (flag) {
							raytraceresult = Block.collisionRayTrace(iblockstate, world, blockpos, start, end);
						}
						
						if (raytraceresult == null && flag1) {
							raytraceresult = VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, (double)ifluidstate.getHeight(), 1.0D).func_212433_a(start, end, blockpos);
						}
						
						if (raytraceresult != null) {
							return raytraceresult;
						}
					}
				}
				
				RayTraceResult raytraceresult2 = null;
				int k1 = 200;
				
				while(k1-- >= 0) {
					if (Double.isNaN(d0) || Double.isNaN(d1) || Double.isNaN(d2)) {
						return null;
					}
					
					if (l == i && i1 == j && j1 == k) {
						return p_200259_5_ ? raytraceresult2 : null;
					}
					
					boolean flag4 = true;
					boolean flag5 = true;
					boolean flag6 = true;
					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					if (i > l) {
						d3 = (double)l + 1.0D;
					} else if (i < l) {
						d3 = (double)l + 0.0D;
					} else {
						flag4 = false;
					}
					
					if (j > i1) {
						d4 = (double)i1 + 1.0D;
					} else if (j < i1) {
						d4 = (double)i1 + 0.0D;
					} else {
						flag5 = false;
					}
					
					if (k > j1) {
						d5 = (double)j1 + 1.0D;
					} else if (k < j1) {
						d5 = (double)j1 + 0.0D;
					} else {
						flag6 = false;
					}
					
					double d6 = 999.0D;
					double d7 = 999.0D;
					double d8 = 999.0D;
					double d9 = end.x - d0;
					double d10 = end.y - d1;
					double d11 = end.z - d2;
					if (flag4) {
						d6 = (d3 - d0) / d9;
					}
					
					if (flag5) {
						d7 = (d4 - d1) / d10;
					}
					
					if (flag6) {
						d8 = (d5 - d2) / d11;
					}
					
					if (d6 == -0.0D) {
						d6 = -1.0E-4D;
					}
					
					if (d7 == -0.0D) {
						d7 = -1.0E-4D;
					}
					
					if (d8 == -0.0D) {
						d8 = -1.0E-4D;
					}
					
					EnumFacing enumfacing;
					if (d6 < d7 && d6 < d8) {
						enumfacing = i > l ? EnumFacing.WEST : EnumFacing.EAST;
						d0 = d3;
						d1 += d10 * d6;
						d2 += d11 * d6;
					} else if (d7 < d8) {
						enumfacing = j > i1 ? EnumFacing.DOWN : EnumFacing.UP;
						d0 += d9 * d7;
						d1 = d4;
						d2 += d11 * d7;
					} else {
						enumfacing = k > j1 ? EnumFacing.NORTH : EnumFacing.SOUTH;
						d0 += d9 * d8;
						d1 += d10 * d8;
						d2 = d5;
					}
					
					l = MathHelper.floor(d0) - (enumfacing == EnumFacing.EAST ? 1 : 0);
					i1 = MathHelper.floor(d1) - (enumfacing == EnumFacing.UP ? 1 : 0);
					j1 = MathHelper.floor(d2) - (enumfacing == EnumFacing.SOUTH ? 1 : 0);
					blockpos = new BlockPos(l, i1, j1);
					IBlockState iblockstate1 = world.getBlockState(blockpos);
					IFluidState ifluidstate1 = world.getFluidState(blockpos);
					if (!p_200259_4_ || iblockstate1.getMaterial() == Material.PORTAL || !iblockstate1.getCollisionShape(world, blockpos).isEmpty()) {
						boolean flag2 = iblockstate1.getBlock().isCollidable(iblockstate1);
						boolean flag3 = fluidMode.predicate.test(ifluidstate1);
						if (!flag2 && !flag3) {
							raytraceresult2 = new RayTraceResult(RayTraceResult.Type.MISS, new Vec3d(d0, d1, d2), enumfacing, blockpos);
						} else {
							RayTraceResult raytraceresult1 = null;
							if (flag2) {
								raytraceresult1 = Block.collisionRayTrace(iblockstate1, world, blockpos, start, end);
							}
							
							if (raytraceresult1 == null && flag3) {
								raytraceresult1 = VoxelShapes.create(0.0D, 0.0D, 0.0D, 1.0D, (double)ifluidstate1.getHeight(), 1.0D).func_212433_a(start, end, blockpos);
							}
							
							if (raytraceresult1 != null) {
								return raytraceresult1;
							}
						}
					}
				}
				
				return p_200259_5_ ? raytraceresult2 : null;
			} else {
				return null;
			}
		} else {
			return null;
		}
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
			return isInFrontOfEntity(livingBase, angel, false);
		}
		return isInFrontOfEntity(livingBase, angel, false);
	}
	
}

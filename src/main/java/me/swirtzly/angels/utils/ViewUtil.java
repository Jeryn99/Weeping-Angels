package me.swirtzly.angels.utils;

import me.swirtzly.angels.common.entities.EntityQuantumLockBase;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShapes;

import java.util.function.BiFunction;
import java.util.function.Function;
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
	
	
	public static boolean viewBlocked(LivingEntity viewer, LivingEntity angel) {
		AxisAlignedBB viewerBoundBox = viewer.getBoundingBox();
		AxisAlignedBB angelBoundingBox = angel.getBoundingBox();
		Vec3d[] viewerPoints = {new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};
		Vec3d[] angelPoints = {new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.minZ),};

		for (int i = 0; i < viewerPoints.length; i++) {
			if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], RayTraceFluidMode.NEVER, true, false) == null)
				return false;
			//if (rayTraceBlocks(viewer.world, viewerPoints[i], angelPoints[i], RayTraceFluidMode.NEVER, false, false, pos -> {
			if (job(viewer.world, viewerPoints[i], angelPoints[i], RayTraceContext.FluidMode.NONE, false, false, pos -> {
				BlockState state = viewer.world.getBlockState(pos);
				for (String transparent_block : WAConfig.CONFIG.transparent_blocks.get())
					if (state.getBlock().getRegistryName().toString().equals(transparent_block)) return false;
				return state.getMaterial() != Material.GLASS && state.getMaterial() != Material.PORTAL && state.getMaterial() != Material.ICE &&
						!(state.getBlock() instanceof PaneBlock) && !(state.getBlock() instanceof VineBlock) && !(state.getBlock() instanceof LeavesBlock) &&
						state.getCollisionShape(viewer.world, pos) != VoxelShapes.empty() && state.getBlock().isCollidable(state);
			}) == null) return false;
		}
		return true;
	}


	public static <T> T job(RayTraceContext contexr, BiFunction<RayTraceContext, BlockPos, T> p_217300_1_, Function<RayTraceContext, T> p_217300_2_, Predicate<BlockPos> stopOn) {
		Vec3d vec3d = contexr.func_222253_b();
		Vec3d vec3d1 = contexr.func_222250_a();
		if (vec3d.equals(vec3d1)) {
			return p_217300_2_.apply(contexr);
		} else {
			double d0 = MathHelper.lerp(-1.0E-7D, vec3d1.x, vec3d.x);
			double d1 = MathHelper.lerp(-1.0E-7D, vec3d1.y, vec3d.y);
			double d2 = MathHelper.lerp(-1.0E-7D, vec3d1.z, vec3d.z);
			double d3 = MathHelper.lerp(-1.0E-7D, vec3d.x, vec3d1.x);
			double d4 = MathHelper.lerp(-1.0E-7D, vec3d.y, vec3d1.y);
			double d5 = MathHelper.lerp(-1.0E-7D, vec3d.z, vec3d1.z);
			int i = MathHelper.floor(d3);
			int j = MathHelper.floor(d4);
			int k = MathHelper.floor(d5);

			BlockPos blockpos = new BlockPos(d3, d4, d5);
			BlockState iblockstate = world.getBlockState(blockpos);

			if (stopOn.test(blockpos)) {
				RayTraceResult raytraceresult = iblockstate.collisionRayTrace(world, blockpos, vec31, vec32);

				if (raytraceresult != null) {
					return raytraceresult;
				}
			}

			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(i, j, k);
			T t = p_217300_1_.apply(contexr, blockpos$mutableblockpos);
			if (t != null) {
				return t;
			} else {
				double d6 = d0 - d3;
				double d7 = d1 - d4;
				double d8 = d2 - d5;
				int l = MathHelper.func_219802_k(d6);
				int i1 = MathHelper.func_219802_k(d7);
				int j1 = MathHelper.func_219802_k(d8);
				double d9 = l == 0 ? Double.MAX_VALUE : (double)l / d6;
				double d10 = i1 == 0 ? Double.MAX_VALUE : (double)i1 / d7;
				double d11 = j1 == 0 ? Double.MAX_VALUE : (double)j1 / d8;
				double d12 = d9 * (l > 0 ? 1.0D - MathHelper.frac(d3) : MathHelper.frac(d3));
				double d13 = d10 * (i1 > 0 ? 1.0D - MathHelper.frac(d4) : MathHelper.frac(d4));
				double d14 = d11 * (j1 > 0 ? 1.0D - MathHelper.frac(d5) : MathHelper.frac(d5));

				while(d12 <= 1.0D || d13 <= 1.0D || d14 <= 1.0D) {
					if (d12 < d13) {
						if (d12 < d14) {
							i += l;
							d12 += d9;
						} else {
							k += j1;
							d14 += d11;
						}
					} else if (d13 < d14) {
						j += i1;
						d13 += d10;
					} else {
						k += j1;
						d14 += d11;
					}

					T t1 = p_217300_1_.apply(contexr, blockpos$mutableblockpos.setPos(i, j, k));
					if (t1 != null) {
						return t1;
					}

				}

				return p_217300_2_.apply(contexr);
			}
		}
	}
	
	/**
	 * Method that detects whether a entity is the the view sight of another entity
	 *
	 * @param viewer      The viewer entity
	 * @param beingViewed The entity being watched by viewer
	 */
	public static boolean canEntitySee(LivingEntity viewer, LivingEntity beingViewed) {
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
	public static boolean isInSightTile(LivingEntity viewer, TileEntity tile) {
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
	public static boolean isInSight(LivingEntity livingBase, EntityQuantumLockBase angel) {
		if (viewBlocked(livingBase, angel)) return false;
		
		if (livingBase instanceof PlayerEntity) {
			return isInFrontOfEntity(livingBase, angel, false);
		}
		return isInFrontOfEntity(livingBase, angel, false);
	}
	
}

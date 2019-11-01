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
		return false; //TODO Actually write this method
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

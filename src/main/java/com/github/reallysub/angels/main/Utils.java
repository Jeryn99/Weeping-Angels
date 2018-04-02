package com.github.reallysub.angels.main;

import java.util.List;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Utils {
	
	public static void teleportEntity(World world, Entity e, double X, double Y, double Z) {
		BlockPos p = new BlockPos(X, Y, Z);
		
		if (world.isAirBlock(p)) {
			if (world.getBlockState(p.add(0, -1, 0)).getMaterial().isSolid()) {
				e.setPositionAndUpdate(p.getX(), p.getY(), p.getZ());
			} else {
				for (int i = 1; i < 255; i++) {
					if (world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
						e.setPositionAndUpdate(p.getX(), i, p.getZ());
					}
				}
			}
		} else {
			for (int i = 1; i < 255; i++) {
				if (world.isAirBlock(p.add(0, -p.getY() + i, 0)) && world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
					e.setPositionAndUpdate(p.getX(), i, p.getZ());
				}
			}
		}
	}
	
	public static void getAllAngels(EntityLivingBase seeker, int distance, double radius) {
		if (distance < 0 || distance > 256) {
			distance = 256;
		}
		Vec3d vec3 = seeker.getLookVec();
		double targetX = seeker.posX;
		double targetY = seeker.posY + seeker.getEyeHeight() - 0.10000000149011612D;
		double targetZ = seeker.posZ;
		double distanceTraveled = 0;
		
		while ((int) distanceTraveled < distance) {
			targetX += vec3.x;
			targetY += vec3.y;
			targetZ += vec3.z;
			distanceTraveled += vec3.lengthVector();
			AxisAlignedBB bb = new AxisAlignedBB(targetX - radius, targetY - radius, targetZ - radius, targetX + radius, targetY + radius, targetZ + radius);
			List<EntityAngel> list = seeker.world.getEntitiesWithinAABB(EntityAngel.class, bb);
			for (EntityAngel target : list) {
				if (target != seeker && target.canBeCollidedWith() && isTargetInSight(seeker, target) && !seeker.isPotionActive(MobEffects.BLINDNESS)) {
					target.setSeen(true);
					if (target.getAttackTarget() == seeker && target.getSeenTime() == 1 && target.world.rand.nextInt(3) == 1) {
						target.playSound(WAObjects.angelSeen, 1.0F, 1.0F);
					}
				}
			}
		}
	}
	
	private static boolean isTargetInSight(EntityLivingBase seeker, Entity target) {
		return seeker.canEntityBeSeen(target) && isInEyeLine(seeker, target, 60);
	}
	
	private static boolean isInEyeLine(Entity seeker, Entity target, float fov) {
		double dx = target.posX - seeker.posX;
		double dz;
		for (dz = target.posZ - seeker.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (seeker.rotationYaw > 360) {
			seeker.rotationYaw -= 360;
		}
		while (seeker.rotationYaw < -360) {
			seeker.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - seeker.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		return yaw < fov && yaw > -fov;
	}
	
}

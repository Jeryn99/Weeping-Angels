package com.github.reallysub.angels.main.config;

import java.util.List;

import com.github.reallysub.angels.common.InitEvents;
import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class Utils {
	
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
				if (target != seeker && target.canBeCollidedWith() && isTargetInSight(seeker, target)) {
					target.setSeen(true);
					if (target.getAttackTarget() == seeker && target.getSeenTime() == 1) {
						target.playSound(InitEvents.angelSeen, 1.0F, 1.0F);
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

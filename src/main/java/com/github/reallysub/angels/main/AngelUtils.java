package com.github.reallysub.angels.main;

import java.util.List;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.WAObjects.WAItems;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.events.EventAngelTeleport;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class AngelUtils {
	
	public static void getForTorch(EntityPlayer p, EnumHand hand) {
		ItemStack torch = p.getHeldItem(hand);
		if (torch.getItem() == Item.getItemFromBlock(Blocks.TORCH) || torch.getItem() == Item.getItemFromBlock(Blocks.REDSTONE_TORCH)) {
			String itemName = torch.getDisplayName();
			ItemStack newStack = new ItemStack(WAItems.unLitTorch);
			newStack.setStackDisplayName(itemName);
			torch.setCount(0);
			if (hand == EnumHand.MAIN_HAND) {
				p.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, newStack);
			} else {
				p.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, newStack);
			}
		}
	}
	
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
		
		if (e instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e;
			if (player.world.rand.nextInt(2) == 2) {
				player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 600, 3));
				player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 600, 3));
				player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 3));
			}
			
			MinecraftForge.EVENT_BUS.post(new EventAngelTeleport(player));
		}
	}
	
	public static void getAllAngels(EntityPlayer seeker, int distance, double radius) {
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
			
			if (!list.isEmpty()) {
				for (EntityAngel target : list) {
					if (target.canBeCollidedWith() && isTargetInSight(seeker, target) && !seeker.isPotionActive(MobEffects.BLINDNESS) && !seeker.isSpectator()) {
						target.setSeen(true);
						if (target.getAttackTarget() == seeker && target.getSeenTime() == 1 && seeker.world.rand.nextBoolean() && target.getDistance(seeker) < 5) {
							target.playSound(WAObjects.Sounds.angelSeen, 0.5F, 1.0F);
						}
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

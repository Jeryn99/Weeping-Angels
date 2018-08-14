package me.sub.angels.utils;

import com.google.common.collect.Lists;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityQuantumLockBase;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;

public class AngelUtils {
	
	public static ArrayList<Item> lightItems = new ArrayList<Item>();
	
	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param viewer The viewer entity
	 * @param tile The tile being watched by viewer
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

	public static boolean isInSight(EntityLivingBase livingBase, EntityQuantumLockBase angel) {
		return isInFrontOfEntity(livingBase, angel) && !viewBlocked(livingBase, angel) && !AngelUtils.isDarkForPlayer(angel, livingBase);
	}
	
	public static boolean isDarkForPlayer(EntityQuantumLockBase angel, EntityLivingBase living) {
		return !living.isPotionActive(MobEffects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) == 0 && !AngelUtils.handLightCheck(living);
	}
	
	public static void setupLightItems() {
		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			
			if (block.getLightValue(block.getDefaultState()) > 7) {
				lightItems.add(Item.getItemFromBlock(block));
			}
			
			lightItems.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
		}
	}
	
	public static boolean handLightCheck(EntityLivingBase player) {
		for (Item item : lightItems) {
			if (PlayerUtils.isInEitherHand(player, item)) {
				return true;
			}
		}
		return false;
	}
	
	// Spawn Set up
	public static void setUpSpawns() {
		Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
		ArrayList<Biome> spawn = Lists.newArrayList();
		spawn.addAll(biomes);
		
		for (String rs : WAConfig.spawn.notAllowedBiomes) {
			if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
				Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
				spawn.remove(removedBiome);
			}
		}
		
		for (Biome biome : spawn) {
			if (biome != null) {
				EntityRegistry.addSpawn(EntityWeepingAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, biome);
			}
		}
	}

	public static int secondsToTicks(int seconds) {
		return 20 * seconds;
	}
	
	public static void removeLightFromHand(EntityPlayerMP playerMP, EntityWeepingAngel angel) {
		if (playerMP.getDistanceSq(angel) < 1) {
			
			ItemStack stack = playerMP.getHeldItem(EnumHand.MAIN_HAND);
			if (lightCheck(playerMP, stack, angel)) {
				return;
			}
			
			stack = playerMP.getHeldItem(EnumHand.OFF_HAND);
			lightCheck(playerMP, stack, angel);
		}
	}
	
	private static boolean lightCheck(EntityPlayerMP player, ItemStack stack, EntityWeepingAngel angel) {
		if (lightItems.contains(stack.getItem()) && stack.getItem() != Item.getItemFromBlock(Blocks.TORCH)) {
			stack.shrink(1);
			angel.playSound(WAObjects.Sounds.BLOW, 1.0F, 1.0F);
			return true;
		}
		
		if (stack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
			stack.shrink(1);
			player.addItemStackToInventory(new ItemStack(WAObjects.Items.UNLIT_TORCH));
			angel.playSound(WAObjects.Sounds.BLOW, 1.0F, 1.0F);
			return true;
		}
		return false;
	}

	public static boolean isInFrontOfEntity(Entity entity, Entity target) {
		Vec3d vec3d = target.getPositionVector();
		Vec3d vec3d1 = entity.getLook(1.0F);
		Vec3d vec3d2 = vec3d.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
		vec3d2 = new Vec3d(vec3d2.x, 0.0D, vec3d2.z);
		return vec3d2.dotProduct(vec3d1) < 0.0;
	}

	public static boolean viewBlocked(EntityLivingBase viewer, EntityLivingBase angel) {
		AxisAlignedBB vB = viewer.getEntityBoundingBox();
		AxisAlignedBB aB = angel.getEntityBoundingBox();
		Vec3d[] viewerPoints = {new Vec3d(vB.minX, vB.minY, vB.minZ), new Vec3d(vB.minX, vB.minY, vB.maxZ), new Vec3d(vB.minX, vB.maxY, vB.minZ), new Vec3d(vB.minX, vB.maxY, vB.maxZ), new Vec3d(vB.maxX, vB.maxY, vB.minZ), new Vec3d(vB.maxX, vB.maxY, vB.maxZ), new Vec3d(vB.maxX, vB.minY, vB.maxZ), new Vec3d(vB.maxX, vB.minY, vB.minZ),};
		Vec3d[] angelPoints = {new Vec3d(aB.minX, aB.minY, aB.minZ), new Vec3d(aB.minX, aB.minY, aB.maxZ), new Vec3d(aB.minX, aB.maxY, aB.minZ), new Vec3d(aB.minX, aB.maxY, aB.maxZ), new Vec3d(aB.maxX, aB.maxY, aB.minZ), new Vec3d(aB.maxX, aB.maxY, aB.maxZ), new Vec3d(aB.maxX, aB.minY, aB.maxZ), new Vec3d(aB.maxX, aB.minY, aB.minZ),};

		for (int i = 0; i < viewerPoints.length; i++) {
			if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], false, true, false) == null) return false;
		}
		return true;
	}
}

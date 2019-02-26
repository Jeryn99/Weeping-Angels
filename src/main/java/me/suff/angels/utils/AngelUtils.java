package me.suff.angels.utils;

import com.google.common.collect.Lists;
import me.suff.angels.common.WAObjects;
import me.suff.angels.common.entities.AngelEnums;
import me.suff.angels.common.entities.EntityQuantumLockBase;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;
import java.util.Random;

public class AngelUtils {
	
	private static final Random RANDOM = new Random(23435325);
	public static String[] END_STRUCTURES = new String[]{"EndCity",};
	public static String[] OVERWORLD_STRUCTURES = new String[]{"Stronghold", "Monument", "Village", "Mansion", "Temple", "Mineshaft"};
	public static String[] NETHER_STRUCTURES = new String[]{"Fortress"};
	public static ArrayList<Item> LIGHT_ITEMS = new ArrayList<Item>();
	
	public static void playBreakEvent(Entity entity, BlockPos pos, Block block) {
		if (!entity.world.isRemote) {
			entity.playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
			InventoryHelper.spawnItemStack(entity.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.world.getBlockState(pos).getBlock()));
			entity.world.setBlockState(pos, block.getDefaultState());
			for (EntityPlayer player : entity.world.playerEntities) {
				if (player instanceof EntityPlayerMP) {
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					if (playerMP.getDistanceSq(pos) < 45) {
						playerMP.connection.sendPacket(new SPacketParticles(EnumParticleTypes.CRIT_MAGIC, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 1.0F, 11));
					}
				}
			}
		}
	}
	
	
	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param angel Angel involved (Used for checking if there is light around the angel)
	 * @param angel The entity being watched by viewer
	 */
	public static boolean isDarkForPlayer(EntityQuantumLockBase angel, EntityLivingBase living) {
		return !living.isPotionActive(MobEffects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) <= 0 && !AngelUtils.handLightCheck(living);
	}
	
	
	/**
	 * Method that puts all ItemBlocks of blocks that emite light
	 * WARNING: ONLY CALLED ONCE AND CACHED INTO AngelUtils::LIGHT_ITEMS
	 */
	public static void setupLightItems() {
		ForgeRegistries.BLOCKS.getValuesCollection().forEach(block -> {
			if (AngelUtils.getLightValue(block) > 7) {
				LIGHT_ITEMS.add(Item.getItemFromBlock(block));
			}
		});
		LIGHT_ITEMS.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
		LIGHT_ITEMS.add(Item.getItemFromBlock(Blocks.TORCH));
		LIGHT_ITEMS.removeIf(item -> item == Items.AIR);
	}
	
	/**
	 * Checks if the entity has a item that emites light in their hand
	 */
	public static boolean handLightCheck(EntityLivingBase player) {
		for (Item item : LIGHT_ITEMS) {
			if (PlayerUtils.isInEitherHand(player, item)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets up weeping angel spawns
	 */
	public static void setUpSpawns() {
		ArrayList<Biome> SPAWNS = Lists.newArrayList();
		SPAWNS.addAll(ForgeRegistries.BIOMES.getValuesCollection());
		
		for (String rs : WAConfig.spawn.notAllowedBiomes) {
			if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
				Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
				SPAWNS.remove(removedBiome);
			}
		}
		
		for (Biome spawn : SPAWNS) {
			if (spawn != null) {
				EntityRegistry.addSpawn(EntityWeepingAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, spawn);
			}
		}
		
	}
	
	/**
	 * Converts seconds into ticks
	 */
	public static int secondsToTicks(int seconds) {
		return 20 * seconds;
	}
	
	
	public static void removeLightFromHand(EntityPlayerMP playerMP, EntityWeepingAngel angel) {
		if (playerMP.getDistanceSq(angel) < 1) {
			for (EnumHand enumHand : EnumHand.values()) {
				ItemStack stack = playerMP.getHeldItem(enumHand);
				if (lightCheck(stack, angel)) {
					stack.shrink(1);
					angel.playSound(WAObjects.Sounds.BLOW, 1.0F, 1.0F);
					return;
				}
			}
		}
	}
	
	public static int getLightValue(Block block) {
		return ReflectionHelper.getPrivateValue(Block.class, block, 9);
	}
	
	private static boolean lightCheck(ItemStack stack, EntityWeepingAngel angel) {
		if (LIGHT_ITEMS.contains(stack.getItem())) {
			stack.shrink(1);
			angel.dropItem(stack.getItem(), 1);
			return true;
		}
		
		return false;
	}
	
	public static AngelEnums.AngelType randomType() {
		int pick = new Random().nextInt(AngelEnums.AngelType.values().length);
		return AngelEnums.AngelType.values()[pick];
	}
	
	/**
	 * Returns a random between the specified values;
	 *
	 * @param min the minimum value of the random number
	 * @param max the maximum value of the random number
	 * @return the random number
	 */
	public static double randomBetween(final int min, final int max) {
		return RANDOM.nextInt((max - min) + 1) + min;
	}
	
	public enum EnumTeleportType {
		STRUCTURES, RANDOM_PLACE, DONT
	}
}

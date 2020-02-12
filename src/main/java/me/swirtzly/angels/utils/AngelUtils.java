package me.swirtzly.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.AngelEnums;
import me.swirtzly.angels.common.entities.EntityQuantumLockBase;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class AngelUtils {
	
	private static final Random RANDOM = new Random(23435325);
	public static String[] END_STRUCTURES = new String[]{"EndCity",};
	public static String[] OVERWORLD_STRUCTURES = new String[]{"Stronghold", "Monument", "Village", "Mansion", "Temple", "Mineshaft"};
	public static String[] NETHER_STRUCTURES = new String[]{"Fortress"};
	public static ArrayList<Item> LIGHT_ITEMS = new ArrayList<Item>();
	
	
	public static void playBreakEvent(Entity entity, BlockPos pos, Block block) {
		if (!entity.world.isRemote) {
			entity.playSound(WAObjects.Sounds.LIGHT_BREAK.get(), 1.0F, 1.0F);
			InventoryHelper.spawnItemStack(entity.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.world.getBlockState(pos).getBlock()));
			entity.world.setBlockState(pos, block.getDefaultState());
			
			entity.world.getPlayers().forEach(player -> {
				if (player instanceof ServerPlayerEntity) {
					ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
					if (playerMP.getDistanceSq(pos.getX(), pos.getY(), pos.getZ()) < 45) {
						playerMP.connection.sendPacket(new SSpawnParticlePacket(ParticleTypes.CRIT, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 1.0F, 11));
					}
				}
			});
		}
	}
	
	
	/**
	 * Method that detects whether a tile is the the view sight of viewer
	 *
	 * @param angel Angel involved (Used for checking if there is light around the angel)
	 * @param angel The entity being watched by viewer
	 */
	public static boolean isDarkForPlayer(EntityQuantumLockBase angel, LivingEntity living) {
		return !living.isPotionActive(Effects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) <= 0 && !AngelUtils.handLightCheck(living);
	}
	
	
	/**
	 * Method that puts all ItemBlocks of blocks that emite light
	 * WARNING: ONLY CALLED ONCE AND CACHED INTO AngelUtils::LIGHT_ITEMS
	 */
	public static void setupLightItems() {
		ForgeRegistries.BLOCKS.getValues().forEach(block -> {
			if (AngelUtils.getLightValue(block) > 7) {
				LIGHT_ITEMS.add(block.asItem());
			}
		});
		LIGHT_ITEMS.add(Blocks.REDSTONE_TORCH.asItem());
		LIGHT_ITEMS.add(Blocks.TORCH.asItem());
		LIGHT_ITEMS.removeIf(item -> item == Items.AIR);
	}
	
	/**
	 * Checks if the entity has a item that emites light in their hand
	 */
	public static boolean handLightCheck(LivingEntity player) {
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
		Collection<Biome> biomes = ForgeRegistries.BIOMES.getValues();
		ArrayList<Biome> SPAWNS = Lists.newArrayList();
		SPAWNS.addAll(biomes);
		
		for (String rs : WAConfig.CONFIG.notAllowedBiomes.get()) {
			if (ForgeRegistries.BIOMES.containsKey(new ResourceLocation(rs))) {
				Biome removedBiome = ForgeRegistries.BIOMES.getValue(new ResourceLocation(rs));
				SPAWNS.remove(removedBiome);
			}
		}
		for (Biome biome : SPAWNS) {
            biome.getSpawns(EntityClassification.valueOf(WAConfig.CONFIG.spawnType.get())).add((new Biome.SpawnListEntry(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnProbability.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get())));
		}
	}
	
	/**
	 * Converts seconds into ticks
	 */
	public static int secondsToTicks(int seconds) {
		return 20 * seconds;
	}
	
	
	public static void removeLightFromHand(ServerPlayerEntity playerMP, EntityWeepingAngel angel) {
		if (playerMP.getDistanceSq(angel) < 1) {
			for (Hand enumHand : Hand.values()) {
				ItemStack stack = playerMP.getHeldItem(enumHand);
				if (lightCheck(stack, angel)) {
					stack.shrink(1);
					angel.playSound(WAObjects.Sounds.BLOW.get(), 1.0F, 1.0F);
					return;
				}
			}
		}
	}
	
	public static int getLightValue(Block block) {
		return ObfuscationReflectionHelper.getPrivateValue(Block.class, block, 6);
	}
	
	private static boolean lightCheck(ItemStack stack, EntityWeepingAngel angel) {
		if (LIGHT_ITEMS.contains(stack.getItem())) {
			stack.shrink(1);
			angel.entityDropItem(stack);
			return true;
		}
		
		return false;
	}
	
	public static AngelEnums.AngelType randomType() {
		int pick = new Random().nextInt(AngelEnums.AngelType.values().length);
		return AngelEnums.AngelType.values()[pick];
	}


	public enum EnumTeleportType {
		STRUCTURES, RANDOM_PLACE, DONT
	}
}

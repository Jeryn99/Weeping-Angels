package me.sub.angels.config;

import me.sub.angels.WeepingAngels;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = WeepingAngels.MODID)
public class WAConfig {
	
	@Config.LangKey("category.angels")
	public static final Angels angels = new Angels();
	
	@Config.LangKey("category.spawn")
	public static final Spawn spawn = new Spawn();
	
	public static class Spawn {
		
		@Config.LangKey("config.max_spawn")
		@Config.Comment("The maximum amount of angels per biome")
		@Config.RangeInt(max = 25)
		public int maximumSpawn = 4;
		
		@Config.LangKey("config.spawn_probability")
		@Config.Comment("The angel spawn probability rate")
		public int spawnProbability = 50;
		
		@Config.LangKey("config.min_spawn")
		@Config.Comment("The minimum amount of angels per biome")
		@Config.RangeInt(max = 24)
		public int minimumSpawn = 2;
		
		@Config.LangKey("config.spawntype")
		@Config.Comment("This will only accept: WATER_CREATURE, AMBIENT, CREATURE, MONSTER || Anything else WILL crash your game.")
		@Config.RequiresMcRestart
		public EnumCreatureType spawnType = EnumCreatureType.MONSTER;
		
		@Config.LangKey("config.disallowed_spawn_biomes")
		@Config.Comment("Note: A list of biomes where angels should NOT spawn.")
		public String[] notAllowedBiomes = { "minecraft:void", "minecraft:sky", "minecraft:hell", "minecraft:deep_ocean", "minecraft:ocean" };
		
		@Config.LangKey("config.allowed_spawn_dimensions")
		@Config.Comment("Note: A list of dimension ids where angels should spawn.")
		public int[] dimensionWhitelist = { -1, 0, 1 };
		
	}
	
	public static class Angels {
		
		@Config.LangKey("config.angel_move_sound")
		@Config.Comment("Non-child angels play scraping sounds when moving, this toggles that")
		public boolean playScrapSounds = true;
		
		@Config.LangKey("config.angel_damage")
		@Config.Comment("The damage dealt by an angel")
		public double damage = 8.0D;
		
		@Config.LangKey("config.angel_xp_value")
		@Config.Comment("XP gained from angels")
		public int xpGained = 25;
		
		@Config.LangKey("config.teleport_instant")
		@Config.Comment("just teleport. no damage.")
		public boolean justTeleport = false;
		
		@Config.LangKey("config.teleportRange")
		@Config.Comment("The maximum range a user can be teleported by the Angels")
		public int teleportRange = 450;
		
		@Config.LangKey("config.angel_locking")
		@Config.Comment("If this is enabled, angels will freeze when they see one another {WIP, not the best}")
		public boolean angelLocking = false;
		
		@Config.LangKey("config.angeldimteleport")
		@Config.Comment("If this is enabled, angel teleporting can also tp the player to other dimensions")
		public boolean angelDimTeleport = true;
		
		@Config.LangKey("config.angel.block_break")
		@Config.Comment("If this is enabled, angels will break blocks (If gamerules allow)")
		public boolean blockBreaking = true;
		
		@Config.LangKey("config.block_break_range")
		@Config.Comment("The maximum range a angel can break blocks within")
		public int blockBreakRange = 25;
		
		@Config.LangKey("config.chicken_go_boom")
		@Config.Comment("If this is enabled, the timey wimey detector can blow up chickens when in use randomly")
		public boolean chickenGoboom = true;
		
		@Config.LangKey("config.blowout_torch")
		@Config.Comment("If this is enabled, baby angels will blow out torches")
		public boolean torchBlowOut = true;
		
		@Config.LangKey("config.genCatacombs")
		@Config.Comment("Generate catacombs?")
		public boolean genCatacombs = false;
		
		@Config.LangKey("config.disallowed_blocks")
		public String[] disAllowedBlocks = { Blocks.AIR.getRegistryName().toString(), "thedalekmod:tardis", "tardis:tardis", "tardis:tardisblocktop" };
		
		@Config.LangKey("config.disallowed_dimensions")
		@Config.Comment("Note: This a list of dimensions that angels should NOT teleport you to.")
		public int[] notAllowedDimensions = { 1 };
	}
	
	@Mod.EventBusSubscriber
	public static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(WeepingAngels.MODID)) {
				ConfigManager.sync(WeepingAngels.MODID, Config.Type.INSTANCE);
			}
		}
	}
	
}

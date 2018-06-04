package me.sub.angels.main.config;

import me.sub.angels.main.WeepingAngels;
import net.minecraft.block.Block;
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
		@Config.Comment("The angel spawn probabilty rate")
		public int spawnProbability = 50;
		
		@Config.LangKey("config.min_spawn")
		@Config.Comment("The minimum amount of angels per biome")
		@Config.RangeInt(max = 24)
		public int minimumSpawn = 2;
		
		@Config.LangKey("config.spawntype")
		@Config.Comment("This will only accept: WATER_CREATURE, AMBIENT, CREATURE, MONSTER || Anything else WILL crash your game.")
		@Config.RequiresMcRestart
		public String spawnType = "MONSTER";
	}
	
	public static class Angels {
		
		@Config.LangKey("config.angel_damage")
		@Config.Comment("The damage dealt by an angel")
		public double damage = 8.0D;

		@Config.LangKey("config.angel_speed")
		@Config.Comment("The speed angels move at")
		public double speed = 0.23000000417232513D;
		
		@Config.LangKey("config.teleport_instant")
		@Config.Comment("just teleport. no damage.")
		public boolean justTeleport = false;
		
		@Config.LangKey("config.teleportRange")
		@Config.Comment("The maximum range a user can be teleported by the Angels")
		public int teleportRange = 450;
		
		@Config.LangKey("config.teleport_entities")
		@Config.Comment("If this is enabled there is a chance of you being teleported by a weeping angel")
		public boolean teleportEntities = true;
		
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
		
		@Config.LangKey("config.blocks")
		public Block[] blocks = {};
		
	}
	
	@Mod.EventBusSubscriber(modid = WeepingAngels.MODID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(WeepingAngels.MODID)) {
				ConfigManager.sync(WeepingAngels.MODID, Config.Type.INSTANCE);
			}
		}
	}
	
}

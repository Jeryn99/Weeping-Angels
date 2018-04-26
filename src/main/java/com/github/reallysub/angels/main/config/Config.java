package com.github.reallysub.angels.main.config;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static boolean teleportEntities, angelLocking, angelDimTeleport, chickenGoboom;
	public static int teleportRange, spawnProbability, maximumSpawn, minimumSpawn, blockBreakRange;
	
	public static void init(Configuration cfg) {
		cfg.load();
		
		// Teleport
		teleportRange = cfg.getInt("teleportRange", "Angels", 450, 0, Integer.MAX_VALUE, "The maximum range a user can be teleported by the Angels");
		teleportEntities = cfg.getBoolean("teleportEntities", "Angels", true, "If this is enabled there is a chance of you being teleported by a weeping angel");
		
		// Angel
		angelLocking = cfg.getBoolean("angelLocking", "Angels", false, "If this is enabled, angels will freeze when they see one another {WIP, not the best}");
		angelDimTeleport = cfg.getBoolean("angelDimTeleport", "Angels", true, "If this is enabled, angel teleporting can also tp the player to other dimensions");
		blockBreakRange = cfg.getInt("blockBreakRange", "Angels", 25, 0, Integer.MAX_VALUE, "The maximum range a angel can break blocks within");
		chickenGoboom = cfg.getBoolean("chickenGoboom ", "Angels", false, "If this is enabled, the timey wimey detector can blow up chickens when in use randomly");
		
		// Spawn
		maximumSpawn = cfg.getInt("maximumSpawn", "Spawning", 4, 0, Integer.MAX_VALUE, "The maximum amount of angels per biome");
		spawnProbability = cfg.getInt("spawnProbability", "Spawning", 50, 0, Integer.MAX_VALUE, "The angel spawn probabilty rate");
		minimumSpawn = cfg.getInt("minimumSpawn", "Spawning", 2, 0, Integer.MAX_VALUE, "The minimum amount of angels per biome");
		
		cfg.save();
	}
}
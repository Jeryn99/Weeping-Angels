package com.github.reallysub.angels.main.config;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static boolean teleportEntities, infection, angelPersistence;
	public static int teleportRange, spawnProbability, maximumSpawn, minimumSpawn;
	
	public static void init(Configuration cfg) {
		cfg.load();
		
		// Teleport
		teleportRange = cfg.getInt("teleportRange", "Angels", 450, 0, Integer.MAX_VALUE, "The maximum range a user can be teleported by the Angels");
		teleportEntities = cfg.getBoolean("teleportEntities", "Angels", true, "If this is enabled there is a chance of you being teleported by a weeping angel");
		infection = cfg.getBoolean("infection", "Angels", true, "If this is enabled there is a chance of you being infected by a weeping angel");
		angelPersistence = cfg.getBoolean("angelPersistence", "Angels", false, "If enabled, angels will not despawn on chunk unload");
		
		// Spawn
		maximumSpawn = cfg.getInt("maximumSpawn", "Spawning", 6, 0, Integer.MAX_VALUE, "The maximum amount of angels per biome");
		spawnProbability = cfg.getInt("spawnProbability", "Spawning", 100, 0, Integer.MAX_VALUE, "The angel spawn probabilty rate");
		minimumSpawn = cfg.getInt("minimumSpawn", "Spawning", 2, 0, Integer.MAX_VALUE, "The minimum amount of angels per biome");
		
		cfg.save();
	}
}

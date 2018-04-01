package com.github.reallysub.angels.main.config;

import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static boolean teleportEntities;
	public static int teleportRange;
	
	public static void init(Configuration cfg) {
		cfg.load();
		
		teleportRange = cfg.getInt("teleportRange", "Angels", 450, 0, Integer.MAX_VALUE, "The maximum range a user can be teleported by the Angels");
		teleportEntities = cfg.getBoolean("teleportEntities", "Angels", true, "If this is enabled there is a chance of you being teleported by a weeping angel");
		
		cfg.save();
	}
}

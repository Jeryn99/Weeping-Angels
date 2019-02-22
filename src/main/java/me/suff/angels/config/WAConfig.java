package me.suff.angels.config;

import me.suff.angels.utils.EnumTeleportType;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;

public class WAConfig {
	
	public static final Angels angels = new Angels();
	
	public static final Spawn spawn = new Spawn();
	
	public static final WorldGen worldGen = new WorldGen();
	
	public static Integrations integrations = new Integrations();
	
	public static Teleport teleport = new Teleport();
	
	public static class WorldGen {
		
		public boolean arms = true;
		
		
		public boolean genCatacombs = false;
		
		public int chanceCatacombs = 25;
		
		public boolean genOres = true;
	}
	
	public static class Spawn {
		
		public int maximumSpawn = 4;
		
		public int spawnProbability = 50;
		
		
		public int minimumSpawn = 2;
		
		public EnumCreatureType spawnType = EnumCreatureType.MONSTER;
		
		public String[] notAllowedBiomes = {"minecraft:void", "minecraft:sky", "minecraft:hell", "minecraft:deep_ocean", "minecraft:ocean"};
		
		
		public int[] dimensionWhitelist = {-1, 0, 1};
		
	}
	
	public static class Angels {
		
		public boolean hardcoreMode = false;
		
		public boolean playScrapSounds = true;
		
		
		public boolean playSeenSounds = true;
		
		
		public double damage = 8.0D;
		
		
		public int xpGained = 25;
		
		public boolean blockBreaking = true;
		
		
		public int blockBreakRange = 25;
		
		public boolean chickenGoboom = true;
		
		public boolean torchBlowOut = true;
		
		public String[] disAllowedBlocks = {Blocks.AIR.getRegistryName().toString(), "thedalekmod:tardis", "tardis:tardis", "tardis:tardisblocktop", "minecraft:air"};
		
		public boolean freezeOnAngel = false;
		
		public boolean pickaxeOnly = true;
		
		public int stalkRange = 65;
		
		public double moveSpeed = 1.5;
		
		public String[] transparent_blocks = {"modid:block_name"};
	}
	
	public static class Integrations {
		public String[] keyStrings = new String[]{"thedalekmod:tardisKey", "tardis:key"};
	}
	
	public static class Teleport {
		
		public EnumTeleportType teleportType = EnumTeleportType.RANDOM_PLACE;
		
		public int[] notAllowedDimensions = {1};
		
		public boolean justTeleport = false;
		
		public int teleportRange = 450;
		
		public boolean angelDimTeleport = true;
	}
	
	
}
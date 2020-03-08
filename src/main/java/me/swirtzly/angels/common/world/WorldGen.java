package me.swirtzly.angels.common.world;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldGen {
	
	public static void applyFeatures() {
		// Only in Overworld atm, feel free to change this with a config value
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			// Kontron Ore
			if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, WAObjects.Blocks.KONTRON_ORE.get().getDefaultState(), 5), // Veins of 5
						Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 24))); // Up to 8 veins per chunk, between y level 0 and 24
			}
			if (biome.getPrecipitation() == Biome.RainType.SNOW) {
				biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(WAObjects.WorldGenEntries.ARM_GEN.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHORUS_PLANT, IPlacementConfig.NO_PLACEMENT_CONFIG));
			} else {
				// biome.addFeature(Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(WAObjects.WorldGenEntries.GRAVES.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
				// biome.addStructure(WAObjects.WorldGenEntries.GRAVES.get(), IFeatureConfig.NO_FEATURE_CONFIG);
			}
		}
	}
	
}

package me.swirtzly.minecraft.angels.common.world;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.config.WAConfig;
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
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			// Kontron Ore
			if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && WAConfig.CONFIG.genOres.get()) {
				biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, WAObjects.Blocks.KONTRON_ORE.get().getDefaultState(), 5), Placement.COUNT_RANGE, new CountRangeConfig(8, 0, 0, 24))); // Up to 8 veins per chunk, between y level 0 and 24
			}
			//Arms
			if (biome.getPrecipitation() == Biome.RainType.SNOW && WAConfig.CONFIG.arms.get()) {
				biome.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(WAObjects.WorldGenEntries.ARM_GEN.get(), IFeatureConfig.NO_FEATURE_CONFIG, Placement.CHORUS_PLANT, IPlacementConfig.NO_PLACEMENT_CONFIG));
			}
		}
	}
	
}

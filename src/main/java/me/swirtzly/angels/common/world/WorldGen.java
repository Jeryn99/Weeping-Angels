package me.swirtzly.angels.common.world;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import me.swirtzly.angels.WeepingAngels;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Bus.MOD)
public class WorldGen {	

	/**
 * Used to actually apply features to the world
 * @implNote This is called in the FMLCommonSetup method in the main Weeping Angels class
 * 
 */
 public static void applyFeatures() {
	 //Only in Overworld atm, feel free to change this with a config value
	 for(Biome biome :  ForgeRegistries.BIOMES.getValues()) { 
		 if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { 
			 biome.addFeature(Decoration.UNDERGROUND_ORES,Biome.createDecoratedFeature(Feature.ORE,
				 new OreFeatureConfig(
				 	OreFeatureConfig.FillerBlockType.NATURAL_STONE, 
					WAObjects.Blocks.KONTRON_ORE.get().getDefaultState(), 
					5), //Veins of 5 	
					Placement.COUNT_RANGE, 
					new CountRangeConfig(8, 0, 0, 24))); //Up to 8 veins per chunk, between y level 0 and 24
		 }
	}
 }

}

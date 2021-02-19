package me.suff.mc.angels.common.world.features;

import me.suff.mc.angels.common.objects.WABlocks;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

/* Created by Craig on 18/02/2021 */
public class WAFeatures {

    private static final ConfiguredFeature< ?, ? > KONTRON_ORE = Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, WABlocks.KONTRON_ORE.getDefaultState(), 9)).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, 0, 64))).spreadHorizontally().repeat(20);

    public static void init() {
        RegistryKey< ConfiguredFeature< ?, ? > > kontron = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier(Constants.MODID, "kontron"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, kontron.getValue(), KONTRON_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, kontron);
    }

}

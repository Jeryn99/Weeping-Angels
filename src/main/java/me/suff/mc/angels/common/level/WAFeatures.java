package me.suff.mc.angels.common.level;

import me.suff.mc.angels.common.WAObjects;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {


    private static final HashSet<PlacedFeature> FEATURES = new HashSet<>();

    public static void ores() {
        BlockState blockState = WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState();
        BlockState blockStateDeep = WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState();
        List<OreConfiguration.TargetBlockState> targetBlockStateList = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, blockState), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, blockStateDeep));
        ConfiguredFeature<?, ?> feature = FeatureUtils.register("kontron_ore", Feature.ORE.configured(new OreConfiguration(targetBlockStateList, 9)));
        PlacedFeature placedFeatureUpper = PlacementUtils.register("kontron_ore_upper", feature.placed(commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)))));
        PlacedFeature placedFeatureMiddle = PlacementUtils.register("kontron_ore_middle", feature.placed(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
        FEATURES.add(placedFeatureUpper);
        FEATURES.add(placedFeatureMiddle);
    }


    @SubscribeEvent
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
            for (PlacedFeature feature : FEATURES) {
                gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
            }
        }
    }


    public static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, name, configuredFeature);
    }

    // Taken from Oreplacements, should really be made public by Forge
    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

}

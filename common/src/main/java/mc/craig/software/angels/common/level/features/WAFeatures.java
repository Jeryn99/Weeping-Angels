package mc.craig.software.angels.common.level.features;

import com.google.common.collect.ImmutableList;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class WAFeatures {


    static RuleTest oreReplaceablesTest = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
    static RuleTest deepslateOreReplaceablesTest = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
    public static final DeferredRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegistry.create(WeepingAngels.MODID, Registries.CONFIGURED_FEATURE);
    public static final DeferredRegistry<PlacedFeature> PLACED_FEATURES = DeferredRegistry.create(WeepingAngels.MODID, Registries.PLACED_FEATURE);
    public static RegistrySupplier<ConfiguredFeature<?, ?>> ORE_KONTRON_CONFIGURED = CONFIGURED_FEATURES.register("ore_kontron", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(oreReplaceablesTest, WABlocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateOreReplaceablesTest, WABlocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 9)));
    public static RegistrySupplier<PlacedFeature> ORE_KONTRON = PLACED_FEATURES.register("ore_kontron", () -> new PlacedFeature(Holder.direct(WAFeatures.ORE_KONTRON_CONFIGURED.get()), List.copyOf(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))))));
    public static RegistrySupplier<ConfiguredFeature<?, ?>> ORE_KONTRON_SMALL_CONFIGURED = CONFIGURED_FEATURES.register("ore_kontron_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(oreReplaceablesTest, WABlocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateOreReplaceablesTest, WABlocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 4)));
    public static RegistrySupplier<PlacedFeature> ORE_KONTRON_SMALL = PLACED_FEATURES.register("ore_kontron_small", () -> new PlacedFeature(Holder.direct(WAFeatures.ORE_KONTRON_SMALL_CONFIGURED.get()), List.copyOf(commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))))));

    public static RegistrySupplier<ConfiguredFeature<?, ?>> SNOW_ANGEL_CONFIGURED = CONFIGURED_FEATURES.register("snow_angel", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WABlocks.SNOW_ANGEL.get().defaultBlockState())))));
    public static RegistrySupplier<PlacedFeature> SNOW_ANGEL = PLACED_FEATURES.register("snow_angel", () -> new PlacedFeature(Holder.direct(WAFeatures.SNOW_ANGEL_CONFIGURED.get()), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    private static List<PlacementModifier> orePlacement(PlacementModifier plMod, PlacementModifier plMod2) {
        return List.of(plMod, InSquarePlacement.spread(), plMod2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int amt, PlacementModifier plMod) {
        return orePlacement(CountPlacement.of(amt), plMod);
    }


}

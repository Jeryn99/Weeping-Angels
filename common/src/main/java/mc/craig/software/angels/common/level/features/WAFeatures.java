package mc.craig.software.angels.common.level.features;

import com.google.common.collect.ImmutableList;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class WAFeatures {

    public static final DeferredRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegistry.create(WeepingAngels.MODID, Registry.CONFIGURED_FEATURE_REGISTRY);
    public static final DeferredRegistry<PlacedFeature> PLACED_FEATURES = DeferredRegistry.create(WeepingAngels.MODID, Registry.PLACED_FEATURE_REGISTRY);

    public static RegistrySupplier<ConfiguredFeature<?, ?>> SNOW_ANGEL_CONFIGURED = CONFIGURED_FEATURES.register("snow_angel", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WABlocks.SNOW_ANGEL.get().defaultBlockState())))));
    public static RegistrySupplier<PlacedFeature> SNOW_ANGEL = PLACED_FEATURES.register("snow_angel", () -> new PlacedFeature(Holder.direct(WAFeatures.SNOW_ANGEL_CONFIGURED.get()), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    private static List<PlacementModifier> orePlacement(PlacementModifier plMod, PlacementModifier plMod2) {
        return List.of(plMod, InSquarePlacement.spread(), plMod2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int amt, PlacementModifier plMod) {
        return orePlacement(CountPlacement.of(amt), plMod);
    }


}

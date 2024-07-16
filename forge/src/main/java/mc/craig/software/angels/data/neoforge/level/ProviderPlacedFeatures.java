package mc.craig.software.angels.data.neoforge.level;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;


public class ProviderPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SNOW_ANGEL = createKey("snow_angel");
    public static final ResourceKey<PlacedFeature> ORE_KONTRON = createKey("ore_kontron");
    public static final ResourceKey<PlacedFeature> ORE_KONTRON_SMALL = createKey("ore_kontron_small");

    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.tryBuild(WeepingAngels.MODID, name));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SNOW_ANGEL, configuredFeatures.getOrThrow(ProviderConfiguredFeatures.SNOW_ANGEL_CONFIGURED), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        register(context, ORE_KONTRON, configuredFeatures.getOrThrow(ProviderConfiguredFeatures.ORE_KONTRON), List.copyOf(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
        register(context, ORE_KONTRON_SMALL, configuredFeatures.getOrThrow(ProviderConfiguredFeatures.ORE_KONTRON_SMALL), List.copyOf(commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72)))));

    }

    private static List<PlacementModifier> orePlacement(PlacementModifier plMod, PlacementModifier plMod2) {
        return List.of(plMod, InSquarePlacement.spread(), plMod2, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int amt, PlacementModifier plMod) {
        return orePlacement(CountPlacement.of(amt), plMod);
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

}
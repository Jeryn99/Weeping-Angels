package mc.craig.software.angels.data.forge.level;

import com.google.common.collect.ImmutableList;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ProviderConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_ANGEL_CONFIGURED = createKey("snow_angel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KONTRON = createKey("ore_kontron");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_KONTRON_SMALL = createKey("ore_kontron_small");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(WeepingAngels.MODID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, SNOW_ANGEL_CONFIGURED, Feature.RANDOM_PATCH,  FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WABlocks.SNOW_ANGEL.get().defaultBlockState()))));
        register(context, ORE_KONTRON, Feature.ORE,  new OreConfiguration(ImmutableList.of(OreConfiguration.target(stoneReplaceable, WABlocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceable, WABlocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 9));
        register(context, ORE_KONTRON_SMALL, Feature.ORE,  new OreConfiguration(ImmutableList.of(OreConfiguration.target(stoneReplaceable, WABlocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(deepslateReplaceable, WABlocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 4));

    }

    public static void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, Feature<NoneFeatureConfiguration> feature) {
        register(context, key, feature, FeatureConfiguration.NONE);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
package me.suff.mc.angels.common.level;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.structures.CatacombStructure;
import me.suff.mc.angels.common.level.structures.GraveyardStructure;
import me.suff.mc.angels.config.WAConfig;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {


    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, WeepingAngels.MODID);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CATACOMB = DEFERRED_REGISTRY_STRUCTURE.register("catacomb", () -> (new CatacombStructure(JigsawConfiguration.CODEC)));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("graveyard", () -> (new GraveyardStructure(NoneFeatureConfiguration.CODEC)));
    private static final HashSet<PlacedFeature> FEATURES = new HashSet<>();
    private static final HashSet<PlacedFeature> ORES = new HashSet<>();

    public static void setupStructures() {
        setupMapSpacingAndLand(CATACOMB.get(), new StructureFeatureConfiguration(10000, 5000, 1234567890), false);
        setupMapSpacingAndLand(GRAVEYARD.get(), new StructureFeatureConfiguration(10000, 8000, 1234353780), true);
    }

    public static <F extends StructureFeature<?>> void setupMapSpacingAndLand(F structure, StructureFeatureConfiguration structureFeatureConfiguration, boolean transformSurroundingLand) {
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        if (transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES =
                    ImmutableList.<StructureFeature<?>>builder()
                            .addAll(StructureFeature.NOISE_AFFECTING_FEATURES)
                            .add(structure)
                            .build();
        }

        StructureSettings.DEFAULTS =
                ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder()
                        .putAll(StructureSettings.DEFAULTS)
                        .put(structure, structureFeatureConfiguration)
                        .build();


        BuiltinRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(settings -> {
            Map<StructureFeature<?>, StructureFeatureConfiguration> structureMap = settings.getValue().structureSettings().structureConfig();

            if (structureMap instanceof ImmutableMap) {
                Map<StructureFeature<?>, StructureFeatureConfiguration> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, structureFeatureConfiguration);
                settings.getValue().structureSettings().structureConfig = tempMap;
            } else {
                structureMap.put(structure, structureFeatureConfiguration);
            }
        });
    }

    public static void ores() {
        BlockState blockState = WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState();
        BlockState blockStateDeep = WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState();
        List<OreConfiguration.TargetBlockState> targetBlockStateList = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, blockState), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, blockStateDeep));
        ConfiguredFeature<?, ?> feature = FeatureUtils.register("kontron_ore", Feature.ORE.configured(new OreConfiguration(targetBlockStateList, 9)));
        PlacedFeature placedFeatureUpper = PlacementUtils.register("kontron_ore_upper", feature.placed(commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384)))));
        PlacedFeature placedFeatureMiddle = PlacementUtils.register("kontron_ore_middle", feature.placed(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56)))));
        ORES.add(placedFeatureUpper);
        ORES.add(placedFeatureMiddle);

        ConfiguredFeature<SimpleBlockConfiguration, ?> snowAngelFeature = FeatureUtils.register("snow_angel", Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(WAObjects.Blocks.SNOW_ANGEL.get().defaultBlockState()))));
        PlacedFeature snowAngelPlaced = PlacementUtils.register("snow_angel", snowAngelFeature.placed(RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
        FEATURES.add(snowAngelPlaced);

    }


    @SubscribeEvent
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND && WAConfig.CONFIG.genOres.get()) {
            for (PlacedFeature feature : ORES) {
                gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
            }
        }

        if (event.getCategory() == Biome.BiomeCategory.ICY || event.getName().toString().toLowerCase().contains("snow")) {
            WeepingAngels.LOGGER.info("Added snow angels to " + event.getName());
            for (PlacedFeature feature : FEATURES) {
                gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, feature);
            }
        }
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

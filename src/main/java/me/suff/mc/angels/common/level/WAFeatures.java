package me.suff.mc.angels.common.level;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.structures.CatacombStructure;
import me.suff.mc.angels.common.level.structures.GraveyardStructure;
import me.suff.mc.angels.config.WAConfig;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
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

import java.util.HashSet;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {


    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, WeepingAngels.MODID);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CATACOMB = DEFERRED_REGISTRY_STRUCTURE.register("catacombs", CatacombStructure::new);
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("graveyard", () -> (new GraveyardStructure(NoneFeatureConfiguration.CODEC)));
    private static final HashSet<Holder<PlacedFeature>> FEATURES = new HashSet<>();
    private static final HashSet<Holder<PlacedFeature>> ORES = new HashSet<>();


    public static void ores() {
        BlockState blockState = WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState();
        BlockState blockStateDeep = WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState();
        List<OreConfiguration.TargetBlockState> targetBlockStateList = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, blockState), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, blockStateDeep));
        Holder<ConfiguredFeature<OreConfiguration, ?>> feature = FeatureUtils.register("ore_kontron", Feature.ORE, new OreConfiguration(targetBlockStateList, 9));
        Holder<PlacedFeature> placedFeatureUpper = PlacementUtils.register("kontron_ore_upper", feature, commonOrePlacement(90, HeightRangePlacement.triangle(VerticalAnchor.absolute(80), VerticalAnchor.absolute(384))));
        Holder<PlacedFeature> placedFeatureMiddle = PlacementUtils.register("kontron_ore_middle", feature, commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))));
        ORES.add(placedFeatureUpper);
        ORES.add(placedFeatureMiddle);

        Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> snowAngelFeature = FeatureUtils.register("snow_angel", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WAObjects.Blocks.SNOW_ANGEL.get().defaultBlockState()))));
        Holder<PlacedFeature> snowAngelPlaced = PlacementUtils.register("snow_angel", snowAngelFeature, NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
        FEATURES.add(snowAngelPlaced);
    }


    @SubscribeEvent
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND && WAConfig.CONFIG.genOres.get()) {
            for (Holder<PlacedFeature> feature : ORES) {
                gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
            }
        }

        if (event.getCategory() == Biome.BiomeCategory.ICY || event.getName().toString().toLowerCase().contains("snow")) {
            WeepingAngels.LOGGER.info("Added snow angels to " + event.getName());
            for (Holder<PlacedFeature> feature : FEATURES) {
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

package me.suff.mc.angels.common.level;

import com.mojang.serialization.Codec;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.structures.CatacombStructureJigsaw;
import me.suff.mc.angels.common.level.structures.GraveyardStructure;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {

    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, WeepingAngels.MODID);
    public static final RegistryObject<StructureType<?>> CATACOMB = DEFERRED_REGISTRY_STRUCTURE.register("catacombs", () -> typeConvert(CatacombStructureJigsaw.CODEC));
    public static final RegistryObject<StructureType<?>> GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("graveyard", () -> typeConvert(GraveyardStructure.CODEC));
    private static final HashSet<Holder<PlacedFeature>> FEATURES = new HashSet<>();
    private static final HashSet<Holder<PlacedFeature>> ORES = new HashSet<>();


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

    private static Structure.StructureSettings structure(TagKey<Biome> p_236546_, Map<MobCategory, StructureSpawnOverride> p_236547_, GenerationStep.Decoration p_236548_, TerrainAdjustment p_236549_) {
        return new Structure.StructureSettings(biomes(p_236546_), p_236547_, p_236548_, p_236549_);
    }

    private static Structure.StructureSettings structure(TagKey<Biome> p_236539_, GenerationStep.Decoration p_236540_, TerrainAdjustment p_236541_) {
        return structure(p_236539_, Map.of(), p_236540_, p_236541_);
    }

    private static Structure.StructureSettings structure(TagKey<Biome> p_236543_, TerrainAdjustment p_236544_) {
        return structure(p_236543_, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, p_236544_);
    }

    private static Holder<Structure> register(ResourceKey<Structure> p_236534_, Structure p_236535_) {
        return BuiltinRegistries.register(BuiltinRegistries.STRUCTURES, p_236534_, p_236535_);
    }

    private static HolderSet<Biome> biomes(TagKey<Biome> p_236537_) {
        return BuiltinRegistries.BIOME.getOrCreateTag(p_236537_);
    }

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


    //TODO
/*    @SubscribeEvent
    public static void gen(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND && WAConfig.CONFIG.genOres.get()) {
            for (Holder<PlacedFeature> feature : ORES) {
                gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
            }
        }

        Holder<Biome> biome = Holder.direct(ForgeRegistries.BIOMES.getValue(event.getName()));

        if (biome.value().getPrecipitation() == Biome.Precipitation.SNOW) {
            WeepingAngels.LOGGER.info("Added snow angels to " + event.getName());
            for (Holder<PlacedFeature> feature : FEATURES) {
                gen.addFeature(GenerationStep.Decoration.RAW_GENERATION, feature);
            }
        }
    }*/


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

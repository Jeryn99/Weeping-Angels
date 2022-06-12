package me.suff.mc.angels.common.level;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
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
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static me.suff.mc.angels.WeepingAngels.MODID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, MODID);
    public static final RegistryObject<StructureType<?>> CATACOMB = STRUCTURES.register("catacomb_broken", () -> typeConvert(CatacombStructureJigsaw.CODEC));
  /*  public static final RegistryObject<StructureType<?>> CATACOMB_CLASSIC = STRUCTURES.register("catacomb_classic", () -> typeConvert(CatacombStructureJigsaw.CODEC));
    public static final RegistryObject<StructureType<?>> CATACOMB_CLEAN = STRUCTURES.register("catacomb_clean", () -> typeConvert(CatacombStructureJigsaw.CODEC));
    public static final RegistryObject<StructureType<?>> CATACOMB_FLAT = STRUCTURES.register("catacomb_flat", () -> typeConvert(CatacombStructureJigsaw.CODEC));
    public static final RegistryObject<StructureType<?>> CATACOMB_NORMAL = STRUCTURES.register("catacomb_normal", () -> typeConvert(CatacombStructureJigsaw.CODEC));*/
    public static final RegistryObject<StructureType<?>> GRAVEYARD = STRUCTURES.register("graveyard", () -> typeConvert(GraveyardStructure.CODEC));

    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MODID);

    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_KONTRON_CONFIGURED = CONFIGURED_FEATURES.register("ore_kontron", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 9)));
    public static RegistryObject<ConfiguredFeature<?, ?>> ORE_KONTRON_SMALL_CONFIGURED = CONFIGURED_FEATURES.register("ore_kontron_small", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ImmutableList.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE_DEEPSLATE.get().defaultBlockState())), 4)));
    public static RegistryObject<ConfiguredFeature<?, ?>> SNOW_ANGEL_CONFIGURED = CONFIGURED_FEATURES.register("snow_angel", () -> new ConfiguredFeature<>(Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WAObjects.Blocks.SNOW_ANGEL.get().defaultBlockState())))));


    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MODID);

    public static RegistryObject<PlacedFeature> ORE_KONTRON = PLACED_FEATURES.register("ore_kontron", () -> new PlacedFeature(Holder.direct(WAFeatures.ORE_KONTRON_CONFIGURED.get()), List.copyOf(commonOrePlacement(10, HeightRangePlacement.triangle(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(56))))));
    public static RegistryObject<PlacedFeature> ORE_KONTRON_SMALL = PLACED_FEATURES.register("ore_kontron_small", () -> new PlacedFeature(Holder.direct(WAFeatures.ORE_KONTRON_SMALL_CONFIGURED.get()), List.copyOf(commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(72))))));

    public static RegistryObject<PlacedFeature> SNOW_ANGEL = PLACED_FEATURES.register("snow_angel", () -> new PlacedFeature(Holder.direct(WAFeatures.SNOW_ANGEL_CONFIGURED.get()), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));


    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier plMod, PlacementModifier plMod2) {
        return List.of(plMod, InSquarePlacement.spread(), plMod2, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int amt, PlacementModifier plMod) {
        return orePlacement(CountPlacement.of(amt), plMod);
    }

}

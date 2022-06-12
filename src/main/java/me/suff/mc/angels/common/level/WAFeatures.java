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
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
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

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WAFeatures {

    public static final DeferredRegister<StructureType<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, WeepingAngels.MODID);
    public static final RegistryObject<StructureType<?>> CATACOMB = DEFERRED_REGISTRY_STRUCTURE.register("catacombs", () -> typeConvert(CatacombStructureJigsaw.CODEC));
    public static final RegistryObject<StructureType<?>> GRAVEYARD = DEFERRED_REGISTRY_STRUCTURE.register("graveyard", () -> typeConvert(GraveyardStructure.CODEC));

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, WeepingAngels.MODID);

    public static RegistryObject<PlacedFeature> SNOW_ANGEL = PLACED_FEATURES.register("snow_angel", () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) (Holder<? extends ConfiguredFeature<?, ?>>) FeatureUtils.register("snow_angel", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WAObjects.Blocks.SNOW_ANGEL.get())), List.of(WAObjects.Blocks.SNOW_ANGEL.get()))), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));
    public static RegistryObject<PlacedFeature> KONTRON_ORE = PLACED_FEATURES.register("kontron_ore", () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) (Holder<? extends ConfiguredFeature<?, ?>>) FeatureUtils.register("snow_angel", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(WAObjects.Blocks.SNOW_ANGEL.get())), List.of(WAObjects.Blocks.SNOW_ANGEL.get()))), List.of(NoiseThresholdCountPlacement.of(-0.8D, 0, 4), RarityFilter.onAverageOnceEvery(300), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

    private static HolderSet<Biome> biomes(TagKey<Biome> p_236537_) {
        return BuiltinRegistries.BIOME.getOrCreateTag(p_236537_);
    }

    // Taken from Oreplacements, should really be made public by Forge
    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier p_195348_) {
        return List.of(placementModifier, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    private static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

}

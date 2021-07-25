package me.suff.mc.angels.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.world.structures.CatacombStructure;
import me.suff.mc.angels.common.world.structures.GraveyardStructure;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.StructureSettings;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.blockplacers.SimpleBlockPlacer;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.ConfiguredDecorator;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class WAWorld {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WeepingAngels.MODID);
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, WeepingAngels.MODID);

    //Features
    public static final RegistryObject<Feature<OreConfiguration>> KONTRON_ORE = FEATURES.register("kontron_ore", () -> new OreFeature(OreConfiguration.CODEC));
    public static final RegistryObject<Feature<RandomPatchConfiguration>> ANGEL_SNOW = FEATURES.register("angel_snow", () -> new RandomPatchFeature(RandomPatchConfiguration.CODEC));

    //Structures
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> CATACOMBS = STRUCTURES.register("catacombs", () -> new CatacombStructure(JigsawConfiguration.CODEC, 0, true, true));
    public static final RegistryObject<StructureFeature<NoneFeatureConfiguration>> GRAVEYARD = STRUCTURES.register("graveyard", () -> new GraveyardStructure(NoneFeatureConfiguration.CODEC));

    public static final StructureTemplatePool START = Pools.register(new StructureTemplatePool(new ResourceLocation("village/snowy/town_centers"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy("village/snowy/town_centers/snowy_meeting_point_1"), 100), Pair.of(StructurePoolElement.legacy("village/snowy/town_centers/snowy_meeting_point_2"), 50), Pair.of(StructurePoolElement.legacy("village/snowy/town_centers/snowy_meeting_point_3"), 150), Pair.of(StructurePoolElement.legacy("village/snowy/zombie/town_centers/snowy_meeting_point_1"), 2), Pair.of(StructurePoolElement.legacy("village/snowy/zombie/town_centers/snowy_meeting_point_2"), 1), Pair.of(StructurePoolElement.legacy("village/snowy/zombie/town_centers/snowy_meeting_point_3"), 3)), StructureTemplatePool.Projection.RIGID));

    //Configured

    public static void setupStructures() {
        setupStructure(GRAVEYARD.get(), new StructureFeatureConfiguration(200, 100, 1234567890), false);
        setupStructure(CATACOMBS.get(), new StructureFeatureConfiguration(300, 100, 234567890), false);
    }

    public static void registerConfiguredStructures() {
        registerConfiguredStructure("configured_graveyard", GRAVEYARD, ConfiguredFeatures.CONFIGURED_GRAVEYARD); //We have to add this to flatGeneratorSettings to account for mods that add custom chunk generators or superflat world type
        registerConfiguredStructure("configured_catacombs", CATACOMBS, ConfiguredFeatures.CONFIGURED_CATACOMBS);
    }

    private static <T extends StructureFeature<?>> void registerConfiguredStructure(String registryName, Supplier<T> structure, ConfiguredStructureFeature<?, ?> configuredStructure) {
        BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(WeepingAngels.MODID, registryName), configuredStructure);
        FlatLevelGeneratorSettings.STRUCTURE_FEATURES.put(structure.get(), configuredStructure);
    }

    public static <F extends StructureFeature<?>> void setupStructure(StructureFeature<?> structure, StructureFeatureConfiguration structureFeatureConfiguration, boolean transformSurroundingLand) {
        StructureFeature.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);
        if (transformSurroundingLand) {
            StructureFeature.NOISE_AFFECTING_FEATURES = ImmutableList.<StructureFeature<?>>builder().addAll(StructureFeature.NOISE_AFFECTING_FEATURES).add(structure).build();
        }
        StructureSettings.DEFAULTS = ImmutableMap.<StructureFeature<?>, StructureFeatureConfiguration>builder().putAll(StructureSettings.DEFAULTS).put(structure, structureFeatureConfiguration).build();
    }

    public static StructurePieceType registerStructurePiece(StructurePieceType type, String key) {
        return Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(WeepingAngels.MODID, key), type);
    }

    private static <T extends Feature<?>> void registerConfiguredFeature(String registryName, ConfiguredFeature<?, ?> configuredFeature) {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry.register(registry, new ResourceLocation(WeepingAngels.MODID, registryName), configuredFeature);
    }

    public static class ConfiguredFeatures {

        public static final ImmutableList<OreConfiguration.TargetBlockState> KONTRON_TARGET = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES, WAObjects.Blocks.KONTRON_ORE.get().defaultBlockState()));
        public static final ConfiguredDecorator<HeightmapConfiguration> HEIGHTMAP_DOUBLE = FeatureDecorator.HEIGHTMAP_SPREAD_DOUBLE.configured(new HeightmapConfiguration(Heightmap.Types.MOTION_BLOCKING));
        public static final ConfiguredDecorator<?> HEIGHTMAP_DOUBLE_SQUARE = HEIGHTMAP_DOUBLE.squared();
        public static final ConfiguredFeature<?, ?> CONFIGURED_SNOW_ANGEL = ANGEL_SNOW.get().configured((new RandomPatchConfiguration.GrassConfigurationBuilder(new SimpleStateProvider(WAObjects.Blocks.SNOW_ANGEL.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(5).build()).decorated(HEIGHTMAP_DOUBLE_SQUARE).count(2);
        public static final ConfiguredFeature<?, ?> CONFIGURED_KONTRON_ORE = KONTRON_ORE.get().configured(new OreConfiguration(KONTRON_TARGET, 9)).count(2).rangeUniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(63)).squared().count(20);
        public static ConfiguredStructureFeature<?, ? extends StructureFeature<?>> CONFIGURED_GRAVEYARD = GRAVEYARD.get().configured(NoneFeatureConfiguration.INSTANCE);
        public static ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> CONFIGURED_CATACOMBS = CATACOMBS.get().configured(new JigsawConfiguration(new Supplier<StructureTemplatePool>() {
            @Override
            public StructureTemplatePool get() {
                return START;
            }
        }, 9));

        public static void registerConfiguredFeatures() {
            registerConfiguredFeature("snow_angel", CONFIGURED_SNOW_ANGEL);
            registerConfiguredFeature("kontron_ore", CONFIGURED_KONTRON_ORE);
        }

    }

}

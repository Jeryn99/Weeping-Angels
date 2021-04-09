package me.suff.mc.angels.common.world.structure;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import static me.suff.mc.angels.util.Constants.MODID;

/* Created by Craig on 18/02/2021 */
public class WAStructures {
    public static final StructurePieceType GRAVE_PIECE = GraveyardPieces.MyPiece::new;
    private static final StructureFeature< DefaultFeatureConfig > GRAVEYARD = new GraveyardStructure(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature< ?, ? > GRAVEYARD_CONFIGURED = GRAVEYARD.configure(DefaultFeatureConfig.DEFAULT);

    public static StructureFeature< DefaultFeatureConfig > CATACOMBS = new CatacombStructure(DefaultFeatureConfig.CODEC);
    public static ConfiguredStructureFeature< ?, ? > CONFIGURED_CATACOMBS = CATACOMBS.configure(DefaultFeatureConfig.DEFAULT);

    public static void init() {
        registerConfiguredStructures();
        setupAndRegisterStructureFeatures();
        addToBiomes();
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(MODID, "graveyard"), GRAVE_PIECE);
        FabricStructureBuilder.create(new Identifier(MODID, "graveyard"), GRAVEYARD)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();
    }

    public static void registerConfiguredStructures() {
        Registry< ConfiguredStructureFeature< ?, ? > > registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier(MODID, "configured_catacombs"), CONFIGURED_CATACOMBS);
    }

    public static void setupAndRegisterStructureFeatures() {
        FabricStructureBuilder.create(new Identifier(MODID, "catacombs"), CATACOMBS).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(10, 5, 399117345)).superflatFeature(CATACOMBS.configure(FeatureConfig.DEFAULT)).register();
    }

    public static void addToBiomes() {
        BiomeModifications.create(new Identifier(MODID, "catacombs")).add(ModificationPhase.ADDITIONS, BiomeSelectors.foundInOverworld(), context -> context.getGenerationSettings().addBuiltInStructure(WAStructures.CONFIGURED_CATACOMBS));

        RegistryKey< ConfiguredStructureFeature< ?, ? > > myConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier(MODID, "graveyard"));
        BiomeModifications.addStructure(BiomeSelectors.foundInOverworld(), myConfigured);
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, myConfigured.getValue(), GRAVEYARD_CONFIGURED);
    }
}

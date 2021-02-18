package me.suff.mc.angels.common.world;

import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import static me.suff.mc.angels.util.Constants.MODID;

/* Created by Craig on 18/02/2021 */
public class WAStructures {
    public static StructureFeature< DefaultFeatureConfig > CATACOMBS = new CatacombStructure(DefaultFeatureConfig.CODEC);
    public static ConfiguredStructureFeature< ?, ? > CONFIGURED_CATACOMBS = CATACOMBS.configure(DefaultFeatureConfig.DEFAULT);

    public static void init(){
        registerConfiguredStructures();
        setupAndRegisterStructureFeatures();
        addToBiomes();
    }

    public static void registerConfiguredStructures() {
        Registry< ConfiguredStructureFeature< ?, ? > > registry = BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry.register(registry, new Identifier(MODID, "configured_catacombs"), CONFIGURED_CATACOMBS);
    }

    public static void setupAndRegisterStructureFeatures() {
        FabricStructureBuilder.create(new Identifier(MODID, "catacombs"), CATACOMBS).step(GenerationStep.Feature.UNDERGROUND_STRUCTURES).defaultConfig(new StructureConfig(
                10, /* maximum distance apart in chunks between spawn attempts */
                5, /* minimum distance apart in chunks between spawn attempts */
                399117345 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */)).superflatFeature(CATACOMBS.configure(FeatureConfig.DEFAULT)).register();
    }

    public static void addToBiomes(){
        BiomeModifications.create(new Identifier(MODID, "catacombs"))
                .add(   // Describes what we are doing. SInce we are adding a structure, we choose ADDITIONS.
                        ModificationPhase.ADDITIONS,

                        // Add our structure to all biomes including other modded biomes.
                        // You can filter to certain biomes based on stuff like temperature, scale, precipitation, mod id.
                        BiomeSelectors.all(),

                        // context is basically the biome itself. This is where you do the changes to the biome.
                        // Here, we will add our ConfiguredStructureFeature to the biome.
                        context -> {
                            context.getGenerationSettings().addBuiltInStructure(WAStructures.CONFIGURED_CATACOMBS);
                        });

    }
}

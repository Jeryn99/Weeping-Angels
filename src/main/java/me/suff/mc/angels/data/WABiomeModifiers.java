package me.suff.mc.angels.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.WAFeatures;
import me.suff.mc.angels.common.level.biomemodifiers.FeatureModifier;
import me.suff.mc.angels.common.level.biomemodifiers.SpawnsModifier;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;

import static me.suff.mc.angels.WeepingAngels.MODID;

public record WABiomeModifiers(DataGenerator dataGenerator) implements DataProvider {

    public static void generate(RegistryOps<JsonElement> ops, BiomeModifier modifier, Path outputFolder, String saveName, CachedOutput cache) {
        final String directory = PackType.SERVER_DATA.getDirectory();
        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();

        final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), saveName + ".json");

        BiomeModifier.DIRECT_CODEC.encodeStart(ops, modifier).resultOrPartial(msg -> WeepingAngels.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)).ifPresent(json ->
        {
            try {
                final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
                DataProvider.saveStable(cache, json, biomeModifierPath);
            } catch (
                    IOException e) {
                WeepingAngels.LOGGER.error("Failed to save " + biomeModifierPathString, e);
            }
        });
    }

    @Override
    public void run(CachedOutput cachedOutput) throws IOException {

        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final Path outputFolder = this.dataGenerator.getOutputFolder();

        Registry<PlacedFeature> placedFeatures = ops.registry(Registry.PLACED_FEATURE_REGISTRY).get();


        // Biome Modifiers
        BiomeModifier spawnsModifier = new SpawnsModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), AngelUtil.ANGEL_SPAWNS), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), 25, 1, 3));
        FeatureModifier snowAngel = new FeatureModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.HAS_IGLOO), GenerationStep.Decoration.RAW_GENERATION, HolderSet.direct(placedFeatures.getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, WAFeatures.SNOW_ANGEL.getId()))));
        FeatureModifier oreModifer = new FeatureModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD), GenerationStep.Decoration.UNDERGROUND_ORES, HolderSet.direct(placedFeatures.getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, WAFeatures.KONTRON_ORE.getId()))));

        // Generate BiomeModiers
        generate(ops, spawnsModifier, outputFolder, SpawnsModifier.MODIFY_SPAWNS, cachedOutput);
        generate(ops, snowAngel, outputFolder, FeatureModifier.NAME, cachedOutput);
      //  generate(ops, oreModifer, outputFolder, FeatureModifier.ORE_NAME, cachedOutput);


        // TODO: need to seperate or combine kontron related generation + correctly set up and not use igloos and nether stuff for everything
    }

    @Override
    public String getName() {
        return MODID + " Biome Modifiers";
    }
}

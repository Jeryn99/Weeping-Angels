package me.suff.mc.angels.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.WAFeatures;
import me.suff.mc.angels.common.level.biomemodifiers.BiomeFeatureModifier;
import me.suff.mc.angels.common.level.biomemodifiers.BiomeSpawnsModifier;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
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
    public void run(CachedOutput cachedOutput) {

        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final Path outputFolder = this.dataGenerator.getOutputFolder();

        // Biome Modifiers
        BiomeModifier spawnsModifier = new BiomeSpawnsModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), AngelUtil.ANGEL_SPAWNS), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), 25, 1, 3));
        BiomeFeatureModifier snowAngel = new BiomeFeatureModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), Tags.Biomes.IS_SNOWY), GenerationStep.Decoration.RAW_GENERATION, HolderSet.direct(Holder.direct(WAFeatures.SNOW_ANGEL.get())));
        BiomeFeatureModifier oreModifer = new BiomeFeatureModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD), GenerationStep.Decoration.UNDERGROUND_ORES, HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON.get())));
        BiomeFeatureModifier oreModiferSmall = new BiomeFeatureModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), BiomeTags.IS_OVERWORLD), GenerationStep.Decoration.UNDERGROUND_ORES, HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON_SMALL.get())));


        // Generate BiomeModiers
        generate(ops, spawnsModifier, outputFolder, BiomeSpawnsModifier.MODIFY_SPAWNS, cachedOutput);
        generate(ops, snowAngel, outputFolder, "snow_angel", cachedOutput);
        generate(ops, oreModiferSmall, outputFolder, BiomeFeatureModifier.ORE_NAME, cachedOutput);
        generate(ops, oreModifer, outputFolder, BiomeFeatureModifier.ORE_NAME + "_small", cachedOutput);
    }

    @Override
    public String getName() {
        return MODID + " Biome Modifiers";
    }
}

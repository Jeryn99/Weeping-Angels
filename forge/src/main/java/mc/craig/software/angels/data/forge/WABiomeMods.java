package mc.craig.software.angels.data.forge;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.level.features.WAFeatures;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static mc.craig.software.angels.WeepingAngels.MODID;
//TODO: Fix this
//public record WABiomeMods(DataGenerator dataGenerator) implements DataProvider {
//
//    public static void generate(RegistryOps<JsonElement> ops, BiomeModifier modifier, Path outputFolder, String saveName, CachedOutput cache) {
//        final String directory = PackType.SERVER_DATA.getDirectory();
//        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();
//
//        final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), saveName + ".json");
//
//        BiomeModifier.DIRECT_CODEC.encodeStart(ops, modifier).resultOrPartial(msg -> WeepingAngels.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)).ifPresent(json ->
//        {
//            try {
//                final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
//                DataProvider.saveStable(cache, json, biomeModifierPath);
//            } catch (
//                    IOException e) {
//                WeepingAngels.LOGGER.error("Failed to save " + biomeModifierPathString, e);
//            }
//        });
//    }
//
//    @Override
//    public void run(@NotNull CachedOutput cachedOutput) {
//
//        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
//        final Path outputFolder = this.dataGenerator.getOutputFolder();
//
//        // Biome Modifiers
//        BiomeModifier spawnsModifier = new ForgeBiomeModifiers.AddSpawnsBiomeModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), WATags.ANGEL_SPAWNS), List.of(new MobSpawnSettings.SpawnerData(WAEntities.WEEPING_ANGEL.get(), 10, 1, 4)));
//        ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModifer = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), net.minecraft.tags.BiomeTags.IS_OVERWORLD), HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON.get())), GenerationStep.Decoration.UNDERGROUND_ORES);
//        ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModiferSmall = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), net.minecraft.tags.BiomeTags.IS_OVERWORLD), HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON_SMALL.get())), GenerationStep.Decoration.UNDERGROUND_ORES);
//        ForgeBiomeModifiers.AddFeaturesBiomeModifier snowAngel = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), Tags.Biomes.IS_SNOWY), HolderSet.direct(Holder.direct(WAFeatures.SNOW_ANGEL.get())), GenerationStep.Decoration.RAW_GENERATION);
//
//        // Generate BiomeModiers
//        generate(ops, spawnsModifier, outputFolder, "weeping_angel_spawns", cachedOutput);
//        generate(ops, oreModifer, outputFolder, "kontron", cachedOutput);
//        generate(ops, oreModiferSmall, outputFolder, "kontron_small", cachedOutput);
//        generate(ops, snowAngel, outputFolder, "snow_angel", cachedOutput);
//    }
//
//    @Override
//    public @NotNull String getName() {
//        return MODID + " Biome Modifiers";
//    }
//}
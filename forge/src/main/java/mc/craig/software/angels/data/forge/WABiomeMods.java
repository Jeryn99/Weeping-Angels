package mc.craig.software.angels.data.forge;

import com.google.gson.JsonElement;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

import static mc.craig.software.angels.WeepingAngels.MODID;

public record WABiomeMods(DataGenerator dataGenerator) implements DataProvider {

    public static void generate(RegistryOps<JsonElement> ops, BiomeModifier modifier, Path outputFolder, String saveName, CachedOutput cache) {
        final String directory = PackType.SERVER_DATA.getDirectory();
        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();

        final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), saveName + ".json");

        BiomeModifier.DIRECT_CODEC.encodeStart(ops, modifier).resultOrPartial(msg -> WeepingAngels.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)).ifPresent(json ->
        {
            final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
            DataProvider.saveStable(cache, json, biomeModifierPath);
        });
    }

    @Override
    public CompletableFuture<?> run(@NotNull CachedOutput cachedOutput) {


/*
        //TODO
        RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final Path outputFolder = this.dataGenerator.getPackOutput().getOutputFolder();

        // Biome Modifiers
        BiomeModifier spawnsModifier = new AddAngelSpawns(new HolderSet.Named<>(ops.registry(Registries.BIOME).get(), WATags.ANGEL_SPAWNS));
        ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModifer = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registries.BIOME).get(), net.minecraft.tags.BiomeTags.IS_OVERWORLD), HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON.get())), GenerationStep.Decoration.UNDERGROUND_ORES);
        ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModiferSmall = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registries.BIOME).get(), net.minecraft.tags.BiomeTags.IS_OVERWORLD), HolderSet.direct(Holder.direct(WAFeatures.ORE_KONTRON_SMALL.get())), GenerationStep.Decoration.UNDERGROUND_ORES);
        ForgeBiomeModifiers.AddFeaturesBiomeModifier snowAngel = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(new HolderSet.Named<>(ops.registry(Registries.BIOME).get(), Tags.Biomes.IS_SNOWY), HolderSet.direct(Holder.direct(WAFeatures.SNOW_ANGEL.get())), GenerationStep.Decoration.RAW_GENERATION);

        // Generate BiomeModiers
        generate(ops, spawnsModifier, outputFolder, "weeping_angel_spawns", cachedOutput);
        generate(ops, oreModifer, outputFolder, "kontron", cachedOutput);
        generate(ops, oreModiferSmall, outputFolder, "kontron_small", cachedOutput);
        generate(ops, snowAngel, outputFolder, "snow_angel", cachedOutput);*/
        return null;
    }

    @Override
    public @NotNull String getName() {
        return MODID + " Biome Modifiers";
    }
}
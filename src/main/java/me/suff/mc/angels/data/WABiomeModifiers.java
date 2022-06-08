package me.suff.mc.angels.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.level.biomemodifiers.SpawnsModifier;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.nio.file.Path;

import static me.suff.mc.angels.WeepingAngels.MODID;

//TODO Make this a proper class
public class WABiomeModifiers {

    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        final Path outputFolder = generator.getOutputFolder();
        final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.BUILTIN.get());
        final String directory = PackType.SERVER_DATA.getDirectory();

        final ResourceLocation biomeModifiersRegistryID = ForgeRegistries.Keys.BIOME_MODIFIERS.location();

        final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), SpawnsModifier.MODIFY_SPAWNS + ".json");
        final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);

        final BiomeModifier spawnsModifier = new SpawnsModifier(new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), AngelUtil.ANGEL_SPAWNS), new MobSpawnSettings.SpawnerData(WAObjects.EntityEntries.WEEPING_ANGEL.get(), 25, 1, 3));


        // Spawns
        generator.addProvider(event.includeServer(), new DataProvider() {
            @Override
            public void run(final CachedOutput cache) {
                BiomeModifier.DIRECT_CODEC.encodeStart(ops, spawnsModifier)
                        .resultOrPartial(msg -> WeepingAngels.LOGGER.error("Failed to encode {}: {}", biomeModifierPathString, msg)) // Log error on encode failure.
                        .ifPresent(json -> // Output to file on encode success.
                        {
                            try {
                                final String biomeModifierPathString = String.join("/", directory, MODID, biomeModifiersRegistryID.getNamespace(), biomeModifiersRegistryID.getPath(), SpawnsModifier.MODIFY_SPAWNS + ".json");
                                final Path biomeModifierPath = outputFolder.resolve(biomeModifierPathString);
                                DataProvider.saveStable(cache, json, biomeModifierPath);
                            } catch (
                                    IOException e) {
                                WeepingAngels.LOGGER.error("Failed to save " + biomeModifierPathString, e);
                            }
                        });
            }

            @Override
            public String getName() {
                return MODID + " Biome Modifiers";
            }
        });
    }

}

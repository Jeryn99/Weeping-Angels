package dev.jeryn.angels;

import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.util.Platform;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;

public class FabricSpawnHelper {

    public static void init() {
        Registry<Biome> biomeRegistry = BuiltinRegistries.BIOME;
        for (Biome biome : biomeRegistry) {
            BiomeModifications.addSpawn(biomeSelectionContext -> WAEntitySpawns.canSpawnInThisBiome(biome), WAEntitySpawns.getSpawnType(biome), WAEntities.WEEPING_ANGEL.get(), WAEntitySpawns.getSpawnWeight(biome), WAEntitySpawns.getSpawnMin(biome), WAEntitySpawns.getSpawnMax(biome));
        }
    }

}

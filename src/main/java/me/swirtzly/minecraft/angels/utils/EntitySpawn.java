package me.swirtzly.minecraft.angels.utils;

import com.google.common.collect.ImmutableMap;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.config.WAConfig;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EntitySpawn {

    //Iterate through all registered biomes in the WorldGenRegistries.
    public static void addSpawnEntries() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            for (String resourceLocation : WAConfig.CONFIG.allowedBiomes.get()) {
                if(resourceLocation.equalsIgnoreCase(biome.getRegistryName().toString())){
                    if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND && biome.getCategory() != Biome.Category.NONE && biome.getCategory() != Biome.Category.OCEAN) {
                        WeepingAngels.LOGGER.info("Weeping Angels spawns added to : ["+biome.getRegistryName()+"]");
                        addMobSpawnToBiome(biome, EntityClassification.valueOf(WAConfig.CONFIG.spawnType.get()), new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), WAConfig.CONFIG.spawnWeight.get(), WAConfig.CONFIG.minSpawn.get(), WAConfig.CONFIG.maxSpawn.get()));
                    }
                }
            }
        }
    }

    //Add the normal info along with a list of "Spawners"
    public static void addMobSpawnToBiome(Biome biome, EntityClassification classification, MobSpawnInfo.Spawners... spawners) {
        convertImmutableSpawners(biome);
        //Copy the list of spawners that already exist for the given biome.
        List<MobSpawnInfo.Spawners> spawnersList = new ArrayList<>(biome.func_242433_b().field_242554_e.get(classification));
        //Add all the spawners within our list.
        spawnersList.addAll(Arrays.asList(spawners));
        //Overwrite the list for the given classification with the old list and our new entries.
        biome.func_242433_b().field_242554_e.put(classification, spawnersList);
    }

    //Convert the immutable map to a mutable HashMap in order for us to change the data stored in these maps
    private static void convertImmutableSpawners(Biome biome) {
        if (biome.func_242433_b().field_242554_e instanceof ImmutableMap) {
            biome.func_242433_b().field_242554_e = new HashMap<>(biome.func_242433_b().field_242554_e);
        }
    }
}
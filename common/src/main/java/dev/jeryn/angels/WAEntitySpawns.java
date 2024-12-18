package dev.jeryn.angels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.jeryn.angels.common.WAEntities;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static net.minecraft.data.BuiltinRegistries.BIOME;

public class WAEntitySpawns {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/weeping_angels_spawns.json");

    private static Map<ResourceLocation, BiomeSpawnConfig> biomeSpawnConfigs = new HashMap<>();

    public static void init(MinecraftServer minecraftServer) {
        loadConfig();
    }


    public static boolean canSpawnInThisBiome(Biome biome) {
        Registry<Biome> biomeRegistry = BIOME;

        if(!biomeSpawnConfigs.containsKey(biomeRegistry.getKey(biome))){
            return false;
        }

        return biomeSpawnConfigs.get(biomeRegistry.getKey(biome)).canSpawnHere;
    }

    public static MobCategory getSpawnType(Biome biome) {
        Registry<Biome> biomeRegistry = BIOME;

        if(!biomeSpawnConfigs.containsKey(biomeRegistry.getKey(biome))){
            return MobCategory.MONSTER;
        }

        return biomeSpawnConfigs.get(biomeRegistry.getKey(biome)).mobCategory;
    }

    public static Integer getSpawnMin(Biome biome) {
        Registry<Biome> biomeRegistry = BIOME;

        if(!biomeSpawnConfigs.containsKey(biomeRegistry.getKey(biome))){
            return 0;
        }


        return biomeSpawnConfigs.get(biomeRegistry.getKey(biome)).minCount;
    }

    public static Integer getSpawnMax(Biome biome) {
        Registry<Biome> biomeRegistry = BIOME;

        if(!biomeSpawnConfigs.containsKey(biomeRegistry.getKey(biome))){
            return 0;
        }

        return biomeSpawnConfigs.get(biomeRegistry.getKey(biome)).maxCount;
    }

    public static Integer getSpawnWeight(Biome biome) {
        Registry<Biome> biomeRegistry = BIOME;

        if(!biomeSpawnConfigs.containsKey(biomeRegistry.getKey(biome))){
            return 0;
        }

        return biomeSpawnConfigs.get(biomeRegistry.getKey(biome)).spawnWeight;
    }

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (Reader reader = new FileReader(CONFIG_FILE)) {
                Type type = new TypeToken<Map<String, BiomeSpawnConfig>>() {
                }.getType();
                Map<String, BiomeSpawnConfig> configMap = GSON.fromJson(reader, type);
                for (Map.Entry<String, BiomeSpawnConfig> entry : configMap.entrySet()) {
                    biomeSpawnConfigs.put(new ResourceLocation(entry.getKey()), entry.getValue());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            generateDefaultConfig();
        }
    }

    public static void saveConfig() {
        Map<String, BiomeSpawnConfig> configMap = new HashMap<>();
        for (Map.Entry<ResourceLocation, BiomeSpawnConfig> entry : biomeSpawnConfigs.entrySet()) {
            configMap.put(entry.getKey().toString(), entry.getValue());
        }

        try (Writer writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(configMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateDefaultConfig() {
        Registry<Biome> biomeRegistry = BIOME;
        for (Biome biome : biomeRegistry) {
            ResourceLocation biomeKey = biomeRegistry.getKey(biome);
            boolean isNetherBiome = isNetherBiome(biomeKey);
            boolean isTardis = isTardis(biomeKey);
            boolean isWater = isWater(biomeKey);

            int minCount = 1;
            int maxCount = 4;
            int spawnWeight = 8;
            boolean canSpawnHere = !isTardis && !isWater;

            if (isNetherBiome) {
                minCount = 0;
                maxCount = 1;
                spawnWeight = 1;
            }

            BiomeSpawnConfig config = new BiomeSpawnConfig(MobCategory.MONSTER, minCount, maxCount, spawnWeight, canSpawnHere);
            biomeSpawnConfigs.put(biomeKey, config);
        }
        saveConfig();
    }

    // Check if the biome is from the Nether
    private static boolean isNetherBiome(ResourceLocation biomeKey) {
        Registry<Biome> biomeRegistry = BIOME;
        return biomeRegistry.getTag(BiomeTags.IS_NETHER)
                .map(tag -> tag.contains(biomeRegistry.getHolderOrThrow(ResourceKey.create(Registry.BIOME_REGISTRY, biomeKey))))
                .orElse(false);
    }

    private static boolean isWater(ResourceLocation biomeKey) {
        return biomeKey.getPath().contains("ocean") || (biomeKey.getPath().contains("river"));
    }

    private static boolean isTardis(ResourceLocation biomeKey) {
        return biomeKey.getPath().contains("tardis");
    }

    public static class BiomeSpawnConfig {
        private final MobCategory mobCategory;
        public int minCount;
        public int maxCount;
        public int spawnWeight;
        public boolean canSpawnHere;

        public BiomeSpawnConfig(MobCategory mobCategory, int minCount, int maxCount, int spawnWeight, boolean canSpawnHere) {
            this.minCount = minCount;
            this.maxCount = maxCount;
            this.spawnWeight = spawnWeight;
            this.canSpawnHere = canSpawnHere;
            this.mobCategory = mobCategory;
        }
    }
}

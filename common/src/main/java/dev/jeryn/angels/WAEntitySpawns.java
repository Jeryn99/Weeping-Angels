package dev.jeryn.angels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WAEntitySpawns {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File("config/weeping_angels_spawns.json");

    private static final Map<ResourceLocation, BiomeSpawnConfig> biomeSpawnConfigs = new HashMap<>();
    private static boolean loaded = false;
    private static boolean dirty = false;

    public static synchronized void init(MinecraftServer minecraftServer) {
        ensureLoaded();
        Registry<Biome> biomeRegistry = minecraftServer.registryAccess().registryOrThrow(Registries.BIOME);
        for (Map.Entry<net.minecraft.resources.ResourceKey<Biome>, Biome> entry : biomeRegistry.entrySet()) {
            Holder<Biome> holder = biomeRegistry.getHolderOrThrow(entry.getKey());
            getConfig(entry.getKey().location(), holder.is(BiomeTags.IS_OVERWORLD), holder.is(BiomeTags.IS_NETHER));
        }
        if (dirty) {
            saveConfig();
        }
    }

    public static synchronized void reset() {
        biomeSpawnConfigs.clear();
        loaded = false;
        dirty = false;
    }

    public static synchronized BiomeSpawnConfig getConfig(ResourceLocation biome, boolean isOverworld, boolean isNether) {
        ensureLoaded();
        BiomeSpawnConfig config = biomeSpawnConfigs.get(biome);
        if (config == null) {
            config = createDefault(biome, isOverworld, isNether);
            biomeSpawnConfigs.put(biome, config);
            dirty = true;
        }
        return config;
    }

    private static void ensureLoaded() {
        if (loaded) {
            return;
        }
        loaded = true;
        if (!CONFIG_FILE.exists()) {
            return;
        }
        try (Reader reader = new InputStreamReader(new FileInputStream(CONFIG_FILE), StandardCharsets.UTF_8)) {
            Type type = new TypeToken<Map<String, BiomeSpawnConfig>>() {
            }.getType();
            Map<String, BiomeSpawnConfig> configMap = GSON.fromJson(reader, type);
            if (configMap == null) {
                return;
            }
            for (Map.Entry<String, BiomeSpawnConfig> entry : configMap.entrySet()) {
                ResourceLocation biome = ResourceLocation.tryParse(entry.getKey());
                if (biome != null && entry.getValue() != null) {
                    biomeSpawnConfigs.put(biome, entry.getValue().validated());
                }
            }
        } catch (IOException | JsonParseException e) {
            WeepingAngels.LOGGER.error("Could not read {}, using default spawn settings", CONFIG_FILE, e);
        }
    }

    public static synchronized void saveConfig() {
        Map<String, BiomeSpawnConfig> configMap = new TreeMap<>();
        for (Map.Entry<ResourceLocation, BiomeSpawnConfig> entry : biomeSpawnConfigs.entrySet()) {
            configMap.put(entry.getKey().toString(), entry.getValue());
        }

        File parent = CONFIG_FILE.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(CONFIG_FILE), StandardCharsets.UTF_8)) {
            GSON.toJson(configMap, writer);
            dirty = false;
        } catch (IOException e) {
            WeepingAngels.LOGGER.error("Could not write {}", CONFIG_FILE, e);
        }
    }

    private static BiomeSpawnConfig createDefault(ResourceLocation biomeKey, boolean isOverworld, boolean isNether) {
        boolean canSpawnHere = (isOverworld || isNether) && !isTardis(biomeKey) && !isWater(biomeKey);
        if (isNether) {
            return new BiomeSpawnConfig(MobCategory.MONSTER, 0, 1, 1, canSpawnHere);
        }
        return new BiomeSpawnConfig(MobCategory.MONSTER, 1, 4, 8, canSpawnHere);
    }

    private static boolean isWater(ResourceLocation biomeKey) {
        return biomeKey.getPath().contains("ocean") || biomeKey.getPath().contains("river");
    }

    private static boolean isTardis(ResourceLocation biomeKey) {
        return biomeKey.getPath().contains("tardis");
    }

    public static class BiomeSpawnConfig {
        private MobCategory mobCategory;
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

        public MobCategory getMobCategory() {
            return mobCategory;
        }

        public boolean shouldSpawn() {
            return canSpawnHere && spawnWeight > 0 && maxCount > 0;
        }

        private BiomeSpawnConfig validated() {
            if (mobCategory == null) {
                mobCategory = MobCategory.MONSTER;
            }
            minCount = Math.max(0, minCount);
            maxCount = Math.max(minCount, maxCount);
            spawnWeight = Math.max(0, spawnWeight);
            return this;
        }
    }
}

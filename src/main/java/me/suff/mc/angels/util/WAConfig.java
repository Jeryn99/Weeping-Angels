package me.suff.mc.angels.util;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import com.oroarmor.config.command.ConfigCommand;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.biome.Biome;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static me.suff.mc.angels.WeepingAngels.CONFIG;

/* Created by Craig on 18/02/2021 */
public class WAConfig extends Config {
    public static final ConfigItemGroup world = new WorldConfig();
    public static final ConfigItemGroup angelBehaviour = new AngelBehaviour();

    public static final List< ConfigItemGroup > configs = of(world, angelBehaviour);

    public WAConfig() {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), "weeping_angels.json"), "weeping_angels");
    }

    public static void init() {
        CommandRegistrationCallback.EVENT.register(new ConfigCommand(CONFIG)::register);
        CONFIG.readConfigFromFile();
        CONFIG.saveConfigToFile();
    }

    public static String genConfigString(String config) {
        return "config.weeping_angels." + config;
    }

    public static class AngelBehaviour extends ConfigItemGroup {
        public static final ConfigItem< Integer > stalkRange = new ConfigItem<>("stalkRange", 25, genConfigString("stalkRange"));
        public static final ConfigItem< Integer > teleportRange = new ConfigItem<>("teleportRange", 250, genConfigString("teleportRange"));
        public static final ConfigItem< Boolean > chickenBoom = new ConfigItem<>("chickenBoom", true, genConfigString("chickenBoom"));
        public static final ConfigItem< Boolean > playSeenSounds = new ConfigItem<>("playSeenSounds", true, genConfigString("playSeenSounds"));
        public static final ConfigItem< Integer > attackDamage = new ConfigItem<>("attackDamage", 15, genConfigString("attackDamage"));

        public AngelBehaviour() {
            super(of(stalkRange, chickenBoom, playSeenSounds, attackDamage), "angel_behaviour");
        }
    }

    public static class WorldConfig extends ConfigItemGroup {
        public static final ConfigItem< Double > breakRange = new ConfigItem<>("breakRange", 25D, genConfigString("breakRange"));
        public static final ConfigItem< Boolean > breakBlocks = new ConfigItem<>("breakBlocks", true, genConfigString("breakBlocks"));
        public static final ConfigItem< Boolean > catacombs = new ConfigItem<>("catacombs", true, genConfigString("catacombs"));
        public static final ConfigItem< Boolean > graveyards = new ConfigItem<>("graveyards", true, genConfigString("graveyards"));

        public static final ConfigItemGroup spawns = new Spawns();


        public WorldConfig() {
            super(of(breakRange, breakBlocks, catacombs, graveyards, spawns), "world");
        }

    }

    private static class Spawns extends ConfigItemGroup {
        public static final ConfigItem< Integer > spawnWeight = new ConfigItem<>("spawnWeight", 5, genConfigString("spawnWeight"));
        public static final ConfigItem< Integer > spawnMin = new ConfigItem<>("spawnMin", 1, genConfigString("spawnMin"));
        public static final ConfigItem< Integer > spawnMax = new ConfigItem<>("spawnMax", 1, genConfigString("spawnMax"));

        public Spawns() {
            super(of(spawnWeight, spawnMin, spawnMax), "spawns");
        }
    }
}

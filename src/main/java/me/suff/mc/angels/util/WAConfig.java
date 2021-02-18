package me.suff.mc.angels.util;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import com.oroarmor.config.command.ConfigCommand;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static me.suff.mc.angels.WeepingAngels.CONFIG;

/* Created by Craig on 18/02/2021 */
//TODO THIS DOESNT WORK
public class WAConfig extends Config {
    public static final ConfigItemGroup breakConfig = new BreakConfig();
    public static final ConfigItemGroup angelBehaviour = new AngelBehaviour();

    public static final List< ConfigItemGroup > configs = of(breakConfig, angelBehaviour);

    public WAConfig() {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), "weeping_angels.json"), "weeping_angels");
    }

    public static class AngelBehaviour extends ConfigItemGroup {
        public static final ConfigItem< Integer > stalkRange = new ConfigItem<>("stalkRange", 25, "stalkRange");
        public static final ConfigItem< Boolean > chickenBoom = new ConfigItem<>("chickenBoom", true, "chickenBoom");

        public AngelBehaviour() {
            super(of(stalkRange, chickenBoom), "angel_behaviour");
        }
    }

    public static class BreakConfig extends ConfigItemGroup {
        public static final ConfigItem< Double > breakRange = new ConfigItem<>("breakRange", 25D, "breakRange");
        public static final ConfigItem< Boolean > breakBlocks = new ConfigItem<>("breakBlocks", true, "breakBlocks");

        public BreakConfig() {
            super(of(breakRange, breakBlocks), "block_breaking");
        }
    }

    public static void init(){
        CommandRegistrationCallback.EVENT.register(new ConfigCommand(CONFIG)::register);
        CONFIG.readConfigFromFile();
        CONFIG.saveConfigToFile();
    }
}

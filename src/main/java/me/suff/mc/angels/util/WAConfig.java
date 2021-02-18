package me.suff.mc.angels.util;

import com.oroarmor.config.Config;
import com.oroarmor.config.ConfigItem;
import com.oroarmor.config.ConfigItemGroup;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.List;

import static com.google.common.collect.ImmutableList.of;

/* Created by Craig on 18/02/2021 */
//TODO THIS DOESNT WORK
public class WAConfig extends Config {
    public static final ConfigItemGroup mainGroup = new Common();

    public static final List< ConfigItemGroup > configs = of(mainGroup);

    public WAConfig() {
        super(configs, new File(FabricLoader.getInstance().getConfigDir().toFile(), "weeping_angels.json"), "weeping_angels");
    }

    public static class Common extends ConfigItemGroup {
        public static final ConfigItem< Integer > stalkRange = new ConfigItem<>("stalk_range", 25, "stalk_range");

        public Common() {
            super(of(stalkRange), "group");
        }

    }
}

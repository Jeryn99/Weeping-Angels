package dev.jeryn.angels;

import com.google.common.collect.Lists;
import dev.jeryn.angels.util.HurtHelper;
import dev.jeryn.angels.util.Platform;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.level.biome.Biomes.PLAINS;
import static net.minecraft.world.level.biome.Biomes.SUNFLOWER_PLAINS;

public class WAConfiguration {

    public static final WAConfiguration CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    public static Client CLIENT;
    public static ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<WAConfiguration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WAConfiguration::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();

        Pair<Client, ForgeConfigSpec> specClientPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specClientPair.getRight();
        CLIENT = specClientPair.getLeft();
    }

    // Behaviour
    public final ForgeConfigSpec.IntValue stalkRange;
    public final ForgeConfigSpec.BooleanValue blockBreaking;
    public final ForgeConfigSpec.BooleanValue interdimensionalTeleporting;
    public final ForgeConfigSpec.BooleanValue angelTheft;

    // Damage
    public final ForgeConfigSpec.EnumValue<HurtHelper.HurtType> hurtType;

    // Teleport
    public final ForgeConfigSpec.IntValue teleportRange;
    public final ForgeConfigSpec.IntValue teleportChance;

    public final ForgeConfigSpec.ConfigValue<List<? extends String>> bannedDimensions;

    // Spawns
    public final ForgeConfigSpec.IntValue maxNearby;
    public final ForgeConfigSpec.IntValue nearbyRadius;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawnDimensionBlacklist;
    public final ForgeConfigSpec.BooleanValue snowAngels;

    public static class Client {
        // Seasonal
        public final ForgeConfigSpec.BooleanValue santaHats;

        public final ForgeConfigSpec.BooleanValue donatorLookup;

        Client(ForgeConfigSpec.Builder builder) {
            builder.push("seasonal");
            santaHats = builder.translation("config.weeping_angels.santa_hats")
                    .comment("Show Santa hats on angels at Xmas?")
                    .define("santa_hats", true);
            builder.pop();

            builder.push("donators");
            donatorLookup = builder.translation("config.weeping_angels.donator_lookup")
                    .comment("Look up the mod's donator list online (used for donator wings). Disable this if you play offline or the lookup causes problems.")
                    .define("donator_lookup", true);
            builder.pop();
        }
    }


    public WAConfiguration(ForgeConfigSpec.Builder builder) {
        builder.push("behaviour")
                .comment("This section determines the behaviour of the Weeping Angels.");
        stalkRange = builder.translation("config.weeping_angels.stalk_range")
                .comment("Range for player visibility check.")
                .defineInRange("stalk_range", 65, 1, 100);
        blockBreaking = builder.translation("config.weeping_angels.block_breaking")
                .comment("Allow angels to break light-emitting blocks?")
                .define("block_breaking", true);
        builder.pop();

        builder.push("damage");
        hurtType = builder.translation("config.weeping_angels.hurt_type")
                .comment("Type of hurt inflicted by angels.")
                .defineEnum("hurt_type", HurtHelper.HurtType.PICKAXE_AND_GENERATOR);
        builder.pop();

        builder.push("teleporting");
        teleportRange = builder.translation("config.weeping_angels.teleport_range")
                .comment("Range for teleportation.")
                .defineInRange("teleport_range", 400, 1, Integer.MAX_VALUE);
        interdimensionalTeleporting = builder.translation("config.weeping_angels.interdimensional_teleporting")
                .comment("Allow teleporting across dimensions?")
                .define("interdimensional_teleporting", true);
        angelTheft = builder.translation("config.weeping_angels.angel_theft")
                .comment("Allow Angel theft?")
                .define("angel_theft", true);
        teleportChance = builder.translation("config.weeping_angels.teleport_chance")
                .comment("Chance of teleportation occurring.")
                .defineInRange("teleport_chance", 50, 1, 100);
        bannedDimensions = builder.translation("config.weeping_angels.banned_dimensions")
                .comment("Dimensions where teleportation is banned.")
                .defineList("banned_dimensions", Lists.newArrayList(Level.END.location().toString()), String.class::isInstance);
        builder.pop();

        builder.push("spawns");
        maxNearby = builder.translation("config.weeping_angels.max_nearby")
                .comment("Natural spawning stops once this many Weeping Angels are already within 'nearby_radius' blocks of the spawn position. 0 = no limit.")
                .defineInRange("max_nearby", 6, 0, 1000);
        nearbyRadius = builder.translation("config.weeping_angels.nearby_radius")
                .comment("The radius (in blocks) used by 'max_nearby'.")
                .defineInRange("nearby_radius", 48, 1, 256);
        spawnDimensionBlacklist = builder.translation("config.weeping_angels.spawn_dimension_blacklist")
                .comment("Dimensions Weeping Angels will never spawn in naturally, e.g. [\"ad_astra:moon\"]. Per-biome settings are in config/weeping_angels_spawns.json.")
                .defineListAllowEmpty(List.of("spawn_dimension_blacklist"), List::<String>of, String.class::isInstance);
        snowAngels = builder.translation("config.weeping_angels.snow_angels")
                .comment("Generate Snow Angels (angels buried in snow) in snowy biomes. Only affects newly generated chunks. Requires a restart.")
                .define("snow_angels", true);
        builder.pop();
    }
}

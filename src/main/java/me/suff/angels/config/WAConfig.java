package me.suff.angels.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

public class WAConfig {
	public static final WAConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;

	//WorldGen
	public final ForgeConfigSpec.BooleanValue arms;
    public final ForgeConfigSpec.BooleanValue catacombs;
    public final ForgeConfigSpec.IntValue chanceCatacombs;
    public final ForgeConfigSpec.BooleanValue genOres;

    //Spawn
    public final ForgeConfigSpec.IntValue maxSpawn;
    public final ForgeConfigSpec.IntValue spawnProbability;
    public final ForgeConfigSpec.IntValue minSpawn;
    public final ForgeConfigSpec.ConfigValue<String> spawnType;
    public final ForgeConfigSpec.ConfigValue<String[]> notAllowedBiomes;
    public final ForgeConfigSpec.ConfigValue<Integer[]> dimensionWhitelist;

    //Angel
    public final ForgeConfigSpec.BooleanValue hardcoreMode;
    public final ForgeConfigSpec.BooleanValue updateChecker;
    public final ForgeConfigSpec.BooleanValue playScrapSounds;
    public final ForgeConfigSpec.BooleanValue playSeenSounds;
    public final ForgeConfigSpec.DoubleValue damage;
    public final ForgeConfigSpec.IntValue xpGained;
    public final ForgeConfigSpec.BooleanValue blockBreaking;
    public final ForgeConfigSpec.IntValue blockBreakRange;
    public final ForgeConfigSpec.BooleanValue chickenGoboom;
    public final ForgeConfigSpec.BooleanValue torchBlowOut;
    public final ForgeConfigSpec.ConfigValue<String[]> disAllowedBlocks;
    public final ForgeConfigSpec.BooleanValue freezeOnAngel;
    public final ForgeConfigSpec.BooleanValue pickaxeOnly;
    public final ForgeConfigSpec.IntValue stalkRange;
    public final ForgeConfigSpec.DoubleValue moveSpeed;
    public final ForgeConfigSpec.ConfigValue<String[]> transparent_blocks;

    //Integration
    public final ForgeConfigSpec.ConfigValue<String[]> keyStrings;

    //Teleport
    public final ForgeConfigSpec.ConfigValue<String> teleportType;
    public final ForgeConfigSpec.ConfigValue<Integer[]> notAllowedDimensions;
    public final ForgeConfigSpec.BooleanValue justTeleport;
    public final ForgeConfigSpec.IntValue teleportRange;
    public final ForgeConfigSpec.BooleanValue angelDimTeleport;

    static {
        final Pair<WAConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WAConfig::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();
    }

    public WAConfig(ForgeConfigSpec.Builder builder) {
        builder.push("world_gen");
        arms = builder
                .translation("config.weeping-angels.gen_arms")
                .comment("Config to toggle the generation of arms in snow biomes")
                .define("arms", true);
        catacombs = builder
                .translation("config.weeping-angels.genCatacombs")
                .comment("Generate catacombs?")
                .define("genCatacombs", false);
        chanceCatacombs = builder
                .translation("config.weeping-angels.chanceGenCatacombs")
                .comment("Chance to generate catacombs? 1 chance of ... (default :25)")
                .defineInRange("chanceCatacombs",25, 1, Integer.MAX_VALUE);
        genOres = builder
                .translation("config.weeping-angels.genOre")
                .comment("Configure whether the mods ores spawn. This MAY require a restart when changed.")
                .define("genOres",true);
        builder.pop();
        builder.push("spawn");
        maxSpawn = builder
                .translation("config.weeping-angels.max_spawn")
                .comment("The maximum amount of angels per biome")
                .defineInRange("maximumSpawn", 4,1,25);
        spawnProbability = builder
                .translation("config.weeping-angels.spawn_probability")
                .comment("The angel spawn probability rate")
                .defineInRange("spawnProbability",50, 1, 100);
        minSpawn = builder
                .translation("config.weeping-angels.min_spawn")
                .comment("The minimum amount of angels per biome")
                .defineInRange("minimumSpawn",2, 1, 24);
        spawnType = builder
                .translation("config.weeping-angels.spawntype")
                .comment("This will only accept: WATER_CREATURE, AMBIENT, CREATURE, MONSTER || Anything else WILL crash your game.")
                .worldRestart()
                .defineInList("spawnType","MONSTER", Arrays.asList("WATER_CREATURE", "AMBIENT", "CREATURE", "MONSTER"));
        notAllowedBiomes = builder
                .translation("config.weeping-angels.disallowed_spawn_biomes")
                .comment("Note: A list of biomes where angels should NOT spawn.")
                .define("notAllowedBiomes",new String[]{"minecraft:void", "minecraft:sky", "minecraft:hell", "minecraft:deep_ocean", "minecraft:ocean"});
        dimensionWhitelist = builder
                .translation("config.weeping-angels.allowed_spawn_dimensions")
                .comment("Note: A list of dimension ids where angels should spawn.")
                .define("dimensionWhitelist", new Integer[]{-1,0,1});
        builder.pop();
        builder.push("angel");
        hardcoreMode = builder
                .translation("config.weeping-angels.hardcore")
                .comment("if enabled, No way to attack/kill angels. Just running.")
                .define("hardcoreMode", false);
        updateChecker = builder
                .translation("config.weeping-angels.update_checker")
                .comment("Config to toggle the update available checker")
                .define("enableUpdateChecker", true);
        playScrapSounds = builder
                .translation("config.weeping-angels.angel_move_sound")
                .comment("Non-child angels play scraping sounds when moving, this toggles that")
                .define("playScrapSound", true);
        playSeenSounds = builder
                .translation("config.weeping-angels.angel_seen_sound")
                .comment("Toggle seen sounds")
                .define("playSeenSounds",true);
        damage = builder
                .translation("config.weeping-angels.angel_damage")
                .comment("The damage dealt by an angel")
                .defineInRange("damage",8.0D, 1.0D, Double.MAX_VALUE);
        xpGained = builder
                .translation("config.weeping-angels.angel_xp_value")
                .comment("XP gained from angels")
                .defineInRange("xpGained",25,1, Integer.MAX_VALUE);
        blockBreaking = builder
                .translation("config.weeping-angels.angel.block_break")
                .comment("If this is enabled, angels will break blocks (If gamerules allow)")
                .define("blockBreaking",true);
        blockBreakRange = builder
                .translation("config.weeping-angels.block_break_range")
                .comment("The maximum range a angel can break blocks within")
                .defineInRange("blockBreakRange", 25, 1, Integer.MAX_VALUE);
        chickenGoboom = builder
                .translation("config.weeping-angels.chicken_go_boom")
                .comment("If this is enabled, the timey wimey detector can blow up chickens when in use randomly")
                .define("chickenGoboom",true);
        torchBlowOut = builder
                .translation("config.weeping-angels.blowout_torch")
                .comment("If this is enabled, baby angels will blow out light items from the players hand")
                .define("torchBlowOut", true);
        disAllowedBlocks = builder
                .translation("config.weeping-angels.disallowed_blocks")
                .define("disAllowedBlocks",new String[]{"thedalekmod:tardis", "tardis:tardis", "tardis:tardisblocktop", "minecraft:air"});
        freezeOnAngel = builder
                .translation("config.weeping-angels.ql")
                .comment("if enabled, angels will freeze when they see one another.")
                .define("freezeOnAngel",false);
        pickaxeOnly = builder
                .translation("config.weeping-angels.pickaxe_only")
                .comment("if enabled, Only pickaxes and generators will work on the angels")
                .define("pickaxeOnly",true);
        stalkRange = builder
                .translation("config.weeping-angels.around_player_range")
                .comment("Determines the range the angels will look for players within, personally, I'd stay under 100")
                .defineInRange("stalkRange",65, 1, 100);
        moveSpeed = builder
                .translation("config.weeping-angels.moveSpeed")
                .comment("Determines the angels move speed")
                .defineInRange("moveSpeed", 1.5, 1.0, Double.MAX_VALUE);
        transparent_blocks = builder
                .translation("config.weeping-angels.transparent_blocks")
                .comment("List of blocks that you should be able to see angels through")
                .define("transparentBlocks", new String[]{"modid:block_name"});
        builder.pop();
        builder.push("integration");
        keyStrings = builder
                .define("keyStrings",new String[]{"thedalekmod:tardisKey", "tardis:key"});
        builder.pop();
        builder.push("teleport");
        teleportType = builder
                .translation("config.weeping-angels.teleport_enabled")
                .comment("Teleport type, Acceptable entries: RANDOM_PLACE, DONT, STRUCTURES")
                .defineInList("teleportType","RANDOM_PLACE", Arrays.asList("RANDOM_PLACE", "DONT", "STRUCTURES"));
        notAllowedDimensions = builder
                .translation("config.weeping-angels.disallowed_dimensions")
                .comment("Note: This a list of dimensions that angels should NOT teleport you to.")
                .define("notAllowedDimension", new Integer[]{1});
        justTeleport = builder
                .translation("config.weeping-angels.teleport_instant")
                .comment("just teleport. no damage.")
                .define("justTeleport", false);
        teleportRange = builder
                .translation("config.weeping-angels.teleportRange")
                .comment("The maximum range a user can be teleported by the Angels")
                .defineInRange("teleportRange", 450, 1, Integer.MAX_VALUE); //TODO Maybe change that, idk
        angelDimTeleport = builder
                .translation("config.weeping-angels.angeldimteleport")
                .comment("If this is enabled, angel teleporting can also tp the player to other dimensions")
                .define("angelDimTeleport", true);
        builder.pop();
    }

    //TODO : Getters
}

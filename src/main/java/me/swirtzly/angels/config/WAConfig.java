package me.swirtzly.angels.config;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.utils.AngelUtils;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = WeepingAngels.MODID, name = "Weeping Angels")
public class WAConfig {

    @Config.LangKey("category.weeping-angels.angels")
    public static final Angels angels = new Angels();

    @Config.LangKey("category.weeping-angels.spawn")
    public static final Spawn spawn = new Spawn();

    @Config.LangKey("category.weeping-angels.worldgen")
    public static final WorldGen worldGen = new WorldGen();

    @Config.LangKey("category.weeping-angels.mod_intergrations")
    public static Integrations integrations = new Integrations();

    public static Teleport teleport = new Teleport();

    public static class WorldGen {

        @Config.LangKey("config.weeping-angels.gen_arms")
        @Config.Comment("Config to toggle the generation of arms in snow biomes")
        public boolean arms = true;

        @Config.LangKey("config.weeping-angels.genCatacombs")
        @Config.Comment("Generate catacombs?")
        public boolean genCatacombs = false;

        @Config.LangKey("config.weeping-angels.chanceGenCatacombs")
        @Config.Comment("Chance to generate catacombs? 1 chance of ... (default :25)")
        public int chanceCatacombs = 25;

        @Config.LangKey("config.weeping-angels.genOre")
        @Config.Comment("Configure whether the mods ores spawn. This MAY require a restart when changed.")
        public boolean genOres = true;
    }

    public static class Spawn {

        @Config.LangKey("config.weeping-angels.max_spawn")
        @Config.Comment("The maximum amount of angels per biome")
        @Config.RangeInt(max = 25)
        public int maximumSpawn = 4;

        @Config.LangKey("config.weeping-angels.spawn_probability")
        @Config.Comment("The angel spawn probability rate")
        public int spawnProbability = 50;

        @Config.LangKey("config.weeping-angels.min_spawn")
        @Config.Comment("The minimum amount of angels per biome")
        @Config.RangeInt(max = 24)
        public int minimumSpawn = 2;

        @Config.LangKey("config.weeping-angels.spawntype")
        @Config.Comment("This will only accept: WATER_CREATURE, AMBIENT, CREATURE, MONSTER || Anything else WILL crash your game.")
        @Config.RequiresMcRestart
        public EnumCreatureType spawnType = EnumCreatureType.MONSTER;

        @Config.LangKey("config.weeping-angels.disallowed_spawn_biomes")
        @Config.Comment("Note: A list of biomes where angels should NOT spawn.")
        public String[] notAllowedBiomes = {"minecraft:void", "minecraft:sky", "minecraft:hell", "minecraft:deep_ocean", "minecraft:ocean"};

        @Config.LangKey("config.weeping-angels.allowed_spawn_dimensions")
        @Config.Comment("Note: A list of dimension ids where angels should spawn.")
        public int[] dimensionWhitelist = {-1, 0, 1};

    }

    public static class Angels {

        @Config.LangKey("config.weeping-angels.hardcore")
        @Config.Comment("if enabled, No way to attack/kill angels. Just running.")
        public boolean hardcoreMode = false;

        @Config.LangKey("config.weeping-angels.update_checker")
        @Config.Comment("Config to toggle the update available checker")
        public boolean enableUpdateChecker = true;

        @Config.LangKey("config.weeping-angels.angel_move_sound")
        @Config.Comment("Non-child angels play scraping sounds when moving, this toggles that")
        public boolean playScrapSounds = true;

        @Config.LangKey("config.weeping-angels.angel_seen_sound")
        @Config.Comment("Toggle seen sounds")
        public boolean playSeenSounds = true;

        @Config.LangKey("config.weeping-angels.angel_damage")
        @Config.Comment("The damage dealt by an angel")
        public double damage = 8.0D;

        @Config.LangKey("config.weeping-angels.angel_xp_value")
        @Config.Comment("XP gained from angels")
        public int xpGained = 25;

        @Config.LangKey("config.weeping-angels.angel.block_break")
        @Config.Comment("If this is enabled, angels will break blocks (If gamerules allow)")
        public boolean blockBreaking = true;

        @Config.LangKey("config.weeping-angels.block_break_range")
        @Config.Comment("The maximum range a angel can break blocks within")
        public int blockBreakRange = 25;

        @Config.LangKey("config.weeping-angels.chicken_go_boom")
        @Config.Comment("If this is enabled, the timey wimey detector can blow up chickens when in use randomly")
        public boolean chickenGoboom = true;

        @Config.LangKey("config.weeping-angels.blowout_torch")
        @Config.Comment("If this is enabled, baby angels will blow out light items from the players hand")
        public boolean torchBlowOut = true;

        @Config.LangKey("config.weeping-angels.disallowed_blocks")
        public String[] disAllowedBlocks = {Blocks.AIR.getRegistryName().toString(), "thedalekmod:tardis", "tardis:tardis", "tardis:tardisblocktop", "minecraft:air"};

        @Config.LangKey("config.weeping-angels.ql")
        @Config.Comment("if enabled, angels will freeze when they see one another.")
        public boolean freezeOnAngel = false;

        @Config.LangKey("config.weeping-angels.pickaxe_only")
        @Config.Comment("if enabled, Only pickaxes and generators will work on the angels")
        public boolean pickaxeOnly = true;

        @Config.LangKey("config.weeping-angels.around_player_range")
        @Config.Comment("Determines the range the angels will look for players within, personally, I'd stay under 100")
        public int stalkRange = 65;

        @Config.LangKey("config.weeping-angels.moveSpeed")
        @Config.Comment("Determines the angels move speed")
        public double moveSpeed = 1.5;

        @Config.LangKey("config.weeping-angels.transparent_blocks")
        @Config.Comment("List of blocks that you should be able to see angels through")
        public String[] transparent_blocks = {"modid:block_name"};

        @Config.LangKey("config.weeping-angels.pick_cooldown")
        @Config.Comment("If enabled, your picxaxe is given a cooldown until you can hit a angel again")
        public boolean pickaxeCooldown = true;

        @Config.LangKey("config.weeping-angels.pick_cooldown_ticks")
        @Config.Comment("If enabled, your picxaxe is given a cooldown until you can hit a angel again (20 ticks = 1 second)")
        public int pickaxeCooldownTicks = 250;


        @Config.Comment("If enabled, Angels will NOT teleport you in creative")
        public boolean teleportInCreative = false;
    }

    public static class Integrations {
        @Config.LangKey("config.weeping-angels.stealable")
        @Config.Comment("List of items the Weeping angels will be able to steal from you")
        public String[] keyStrings = new String[]{"thedalekmod:tardisKey", "tardis:key","tardis:key_01"};


        @Config.LangKey("config.weeping-angels.tardis_mod")
        @Config.Comment("Whether integration with the TARDIS mod is enabled")
        public boolean tardisModIntegration = true;

        @Config.LangKey("config.weeping-angels.tardis_theft")
        @Config.Comment("Whether the angel will steal your TARDIS on entry")
        public boolean tardisTheft = true;

        @Config.LangKey("config.weeping-angels.tardis_theft_dimensions")
        @Config.Comment("Whether the angel will move a stolen tardis between dimensions")
        public boolean tardisTheftDimensional = true;

        @Config.LangKey("config.weeping-angels.tardis_theft_range")
        @Config.Comment("How far a Angel can move a stolen TARDIS from it's previous destination")
        public int theftRange = 5000;

        @Config.LangKey("config.weeping-angels.tardis_fuel")
        @Config.Comment("Whether the angel will drain a TARDIS of it's fuel or not")
        public boolean tardisFuelTheft = true;

    }

    public static class Teleport {

        @Config.LangKey("config.weeping-angels.teleport_enabled")
        @Config.Comment("Teleport type, Acceptable entries: RANDOM_PLACE, DONT, STRUCTURES")
        public AngelUtils.EnumTeleportType teleportType = AngelUtils.EnumTeleportType.RANDOM_PLACE;

        @Config.LangKey("config.weeping-angels.disallowed_dimensions")
        @Config.Comment("Note: This a list of dimensions that angels should NOT teleport you to.")
        public int[] notAllowedDimensions = {1};

        @Config.LangKey("config.weeping-angels.teleport_instant")
        @Config.Comment("just teleport. no damage.")
        public boolean justTeleport = false;

        @Config.LangKey("config.weeping-angels.teleportRange")
        @Config.Comment("The maximum range a user can be teleported by the Angels")
        public int teleportRange = 450;

        @Config.LangKey("config.weeping-angels.angeldimteleport")
        @Config.Comment("If this is enabled, angel teleporting can also tp the player to other dimensions")
        public boolean angelDimTeleport = true;
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(WeepingAngels.MODID)) {
                ConfigManager.sync(WeepingAngels.MODID, Config.Type.INSTANCE);
            }
        }
    }

}

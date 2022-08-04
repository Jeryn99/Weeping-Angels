package craig.software.mc.angels.config;

import com.google.common.collect.Lists;
import craig.software.mc.angels.common.entities.WeepingAngelTypes;
import craig.software.mc.angels.common.variants.AngelVariant;
import craig.software.mc.angels.utils.AngelUtil;
import craig.software.mc.angels.utils.DamageType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class WAConfiguration {
    public static final WAConfiguration CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        final Pair<WAConfiguration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WAConfiguration::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();
    }

    // Angel
    public final ForgeConfigSpec.EnumValue<DamageType> damageType;
    public final ForgeConfigSpec.BooleanValue playScrapeSounds;
    public final ForgeConfigSpec.BooleanValue playSeenSounds;

    public final ForgeConfigSpec.IntValue xpGained;
    public final ForgeConfigSpec.BooleanValue blockBreaking;
    public final ForgeConfigSpec.BooleanValue chickenGoboom;
    public final ForgeConfigSpec.BooleanValue torchBlowOut;
    public final ForgeConfigSpec.BooleanValue freezeOnAngel;
    public final ForgeConfigSpec.IntValue stalkRange;
    public final ForgeConfigSpec.DoubleValue moveSpeed;

    public final ForgeConfigSpec.BooleanValue spawnFromBlocks;


    // Teleport
    public final ForgeConfigSpec.EnumValue<AngelUtil.EnumTeleportType> teleportType;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> notAllowedDimensions;
    public final ForgeConfigSpec.BooleanValue justTeleport;
    public final ForgeConfigSpec.IntValue teleportRange;
    public final ForgeConfigSpec.IntValue teleportChance;
    public final ForgeConfigSpec.BooleanValue angelDimTeleport;
    public final ForgeConfigSpec.BooleanValue aggroCreative;

    // Easter Eggs
    public final ForgeConfigSpec.BooleanValue showSantaHatsAtXmas;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> allowedAngelTypes;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> allowedVariants;


    public WAConfiguration(ForgeConfigSpec.Builder builder) {

        builder.push("angel");
        damageType = builder.translation("config.weeping_angels.damageType").comment("Damage Type For Angels").defineEnum("damageType", DamageType.ANY_PICKAXE_AND_GENERATOR_ONLY);
        playScrapeSounds = builder.translation("config.weeping_angels.angel_move_sound").comment("Non-child angels play scraping sounds when moving, this toggles that").define("playScrapeSound", true);
        playSeenSounds = builder.translation("config.weeping_angels.angel_seen_sound").comment("Toggle seen sounds").define("playSeenSounds", true);
        xpGained = builder.translation("config.weeping_angels.angel_xp_value").comment("XP gained from angels").defineInRange("xpGained", 25, 1, Integer.MAX_VALUE);
        chickenGoboom = builder.translation("config.weeping_angels.chicken_go_boom").comment("If this is enabled, the timey wimey detector can blow up chickens when in use randomly").define("chickenGoboom", true);
        torchBlowOut = builder.translation("config.weeping_angels.blowout_torch").comment("If this is enabled, baby angels will blow out light items from the players hand").define("torchBlowOut", true);
        freezeOnAngel = builder.translation("config.weeping_angels.ql").comment("if enabled, angels will freeze when they see one another. (Impacts performance a bit)").define("freezeOnAngel", false);
        stalkRange = builder.translation("config.weeping_angels.around_player_range").comment("Determines the range the angels will look for players within, personally, I'd stay under 100").defineInRange("stalkRange", 65, 1, 100);
        moveSpeed = builder.translation("config.weeping_angels.moveSpeed").comment("Determines the angels move speed").defineInRange("angelMovementSpeed", 0.2, 0.1, Double.MAX_VALUE);
        blockBreaking = builder.translation("config.weeping_angels.angel.block_break").comment("If mobGriefing is enabled, angels will break blocks [You may be looking for a config option in order to stop certain blocks from being broken. You can do this with a data-pack using weeping_angels:unbreakable_blocks]").define("blockBreaking", true);
        aggroCreative = builder.translation("config.weeping_angels.aggroCreative").comment("Should Angels target creative players?").define("aggroCreative", false);
        builder.pop();

        builder.push("teleport");
        teleportType = builder.translation("config.weeping_angels.teleport_enabled").comment("Teleport Type - STRUCTURES: Teleports you to Structures Only - DONT: No Teleporting, only damage - RANDOM: Anywhere").defineEnum("teleportType", AngelUtil.EnumTeleportType.RANDOM_PLACE);
        angelDimTeleport = builder.translation("config.weeping_angels.angeldimteleport").comment("Toggle whether Weeping Angels can teleport you across dimensions").define("angelDimTeleport", true);
        notAllowedDimensions = builder.translation("config.weeping_angels.disallowed_dimensions").comment("A list of Dimensions that you cannot be teleported to").defineList("notAllowedDimensions", Lists.newArrayList(Level.END.location().toString()), String.class::isInstance);
        justTeleport = builder.translation("config.weeping_angels.teleport_instant").comment("If toggled, players will not be damaged by angels, just teleported").define("justTeleport", false);
        teleportRange = builder.translation("config.weeping_angels.teleportRange").comment("The maximum range a user can be teleported").defineInRange("teleportRange", 450, 1, Integer.MAX_VALUE);
        teleportChance = builder.translation("config.weeping_angels.teleport_chance").comment("The chance of which a player can be teleported. Set to -1 to only allow damage").defineInRange("teleportRange", 50, -1, 100);
        builder.pop();

        builder.push("block");
        spawnFromBlocks = builder.translation("config.weeping_angels.spawnFromBlocks").comment("This config option toggles whether angels can spawn from Statues/Plinths when they receive a reds-tone signal").define("spawnFromBlocks", true);
        builder.pop();

        builder.push("misc");
        showSantaHatsAtXmas = builder.translation("config.weeping_angels.santa_hat").comment("Toggle whether santa hats are shown at Xmas").define("showSantaHatsAtXmas", true);
        allowedAngelTypes = builder.translation("config.weeping_angels.allowed_types").comment("Toggle certain angel models (Only applies to Entity)").defineList("allowedAngelTypes", genAngelTypes(), String.class::isInstance);
        allowedVariants = builder.translation("config.weeping_angels.allowed_variants").comment("Toggle certain angel variants (Only applies to Entity)").defineList("allowedVariants", getAngelVariants(), String.class::isInstance);
        builder.pop();
    }


    public boolean isModelPermitted(WeepingAngelTypes weepingAngelTypes) {
        for (String s : allowedAngelTypes.get()) {
            if (s.equalsIgnoreCase(weepingAngelTypes.name())) {
                return true;
            }
        }
        return false;
    }

    public boolean isVariantPermitted(AngelVariant angelVariant) {
        for (String s : allowedVariants.get()) {
            if (s.equalsIgnoreCase(angelVariant.getRegistryName().toString())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getAngelVariants() {
        ArrayList<String> allowedTypes = new ArrayList<>();
        allowedTypes.add("weeping_angels:gold");
        allowedTypes.add("weeping_angels:diamond");
        allowedTypes.add("weeping_angels:iron");
        allowedTypes.add("weeping_angels:mossy");
        allowedTypes.add("weeping_angels:normal");
        allowedTypes.add("weeping_angels:basalt");
        allowedTypes.add("weeping_angels:rusted");
        allowedTypes.add("weeping_angels:rusted_no_arm");
        allowedTypes.add("weeping_angels:rusted_no_wing");
        allowedTypes.add("weeping_angels:rusted_no_head");
        allowedTypes.add("weeping_angels:dirt");
        allowedTypes.add("weeping_angels:emerald");
        allowedTypes.add("weeping_angels:copper");
        allowedTypes.add("weeping_angels:lapis_lazuli");
        allowedTypes.add("weeping_angels:quartz");
        return allowedTypes;
    }

    public ArrayList<String> genAngelTypes() {
        ArrayList<String> allowedTypes = new ArrayList<>();
        allowedTypes.add("DISASTER_MC");
        allowedTypes.add("DOCTOR");
        allowedTypes.add("ED");
        allowedTypes.add("ED_ANGEL_CHILD");
        allowedTypes.add("A_DIZZLE");
        allowedTypes.add("DYING");
        allowedTypes.add("VILLAGER");
        allowedTypes.add("VIO_1");
        allowedTypes.add("VIO_2");
        allowedTypes.add("SPARE_TIME");
        return allowedTypes;
    }
}

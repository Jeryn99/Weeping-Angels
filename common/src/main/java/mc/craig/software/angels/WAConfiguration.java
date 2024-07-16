package mc.craig.software.angels;

import com.google.common.collect.Lists;
import mc.craig.software.angels.util.HurtHelper;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class WAConfiguration {

    public static final WAConfiguration CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public static Client CLIENT;
    public static ModConfigSpec CLIENT_SPEC;

    public static Spawns SPAWNS;
    public static ModConfigSpec SPAWNS_SPEC;

    static {
        final Pair<WAConfiguration, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(WAConfiguration::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();

        Pair<Client, ModConfigSpec> specClientPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specClientPair.getRight();
        CLIENT = specClientPair.getLeft();

        Pair<Spawns, ModConfigSpec> spawns = new ModConfigSpec.Builder().configure(Spawns::new);
        SPAWNS_SPEC = spawns.getRight();
        SPAWNS = spawns.getLeft();
    }

    // Behaviour
    public final ModConfigSpec.IntValue stalkRange;
    public final ModConfigSpec.BooleanValue blockBreaking;
    public final ModConfigSpec.BooleanValue interdimensionalTeleporting;

    // Damage
    public final ModConfigSpec.EnumValue<HurtHelper.HurtType> hurtType;

    // Teleport
    public final ModConfigSpec.IntValue teleportRange;
    public final ModConfigSpec.IntValue teleportChance;

    public final ModConfigSpec.ConfigValue<List<? extends String>> bannedDimensions;

    public static class Client {
        // Seasonal
        public final ModConfigSpec.BooleanValue santaHats;

        Client(ModConfigSpec.Builder builder) {
            builder.push("seasonal");
            santaHats = builder.translation("config.weeping_angels.santa_hats").comment("Show Santa hats on angels at xmas?").define("santa_hats", true);
            builder.pop();
        }
    }


    public static class Spawns{

        public final ModConfigSpec.IntValue maxCount;
        public final ModConfigSpec.IntValue spawnWeight;
        public final ModConfigSpec.IntValue minCount;
        public final ModConfigSpec.EnumValue<MobCategory> spawnType;
        public Spawns(ModConfigSpec.Builder builder){
            builder.push("spawn");
            minCount = builder.translation("config.weeping_angels.minCount").comment("The minimum amount of 'Weeping Angels' that spawn at each spawn attempt").defineInRange("minCount", 1, 1, 100);
            maxCount = builder.translation("config.weeping_angels.maxCount").comment("The maximum amount of 'Weeping Angels' that spawn at each spawn attempt").defineInRange("maxCount", 4, 1, 100);
            spawnWeight = builder.translation("config.weeping_angels.spawn_weight").comment("The weight of spawn in relation to other mods 'Weeping Angels' will spawn in. Less than 100 = Rarer").defineInRange("spawn_weight", 8, 1, Integer.MAX_VALUE);
            spawnType = builder.translation("config.weeping_angels.spawntype").comment("'Weeping Angel' spawn classification").worldRestart().defineEnum("spawnType", MobCategory.MONSTER);
            builder.pop();
        }
    }

    public WAConfiguration(ModConfigSpec.Builder builder) {
        builder.push("behaviour").comment("This section determines the behaviour of the Weeping Angels - if you wish to ban a block from being interacted with by Weeping Angels, you will need to create a datapack and edit weeping_angels:no_breaking");
        stalkRange = builder.translation("config.weeping_angels.stalk_range").comment("Determines the range quantum locked entities will check if the player is looking in").defineInRange("stalk_range", 65, 1, 100);
        blockBreaking = builder.translation("config.weeping_angels.block_breaking").comment("If enabled alongside the mobGriefing gamerule, angels will interact with blocks that emit light").define("block_breaking", true);
        builder.pop();

        builder.push("damage");
        hurtType = builder.translation("config.weeping_angels.hurt_type").comment("Hurt").defineEnum("hurt_type", HurtHelper.HurtType.PICKAXE_AND_GENERATOR);
        builder.pop();

        builder.push("teleporting");
        teleportRange = builder.translation("config.weeping_angels.teleport_range").comment("Determines the range that a player can be teleported from their current location").defineInRange("teleport_range", 400, 1, Integer.MAX_VALUE);
        interdimensionalTeleporting = builder.translation("config.weeping_angels.interdimensional_teleporting").comment("Determines whether angels can teleport players across dimensions").define("interdimensional_teleporting", true);
        teleportChance = builder.translation("config.weeping_angels.teleport_chance").comment("Determines the chance a player will be teleported").defineInRange("teleport_chance", 50, 1, 100);
        bannedDimensions = builder.translation("config.weeping_angels.banned_dimensions").comment("A list of Dimensions that angels cannot teleport players to").defineList("banned_dimensions", Lists.newArrayList(Level.END.location().toString()), String.class::isInstance);
        builder.pop();

    }

}
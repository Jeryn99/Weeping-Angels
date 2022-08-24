package mc.craig.software.angels;

import mc.craig.software.angels.util.HurtHelper;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.TierSortingRegistry;
import org.apache.commons.lang3.tuple.Pair;

public class WAConfiguration {
    public static final WAConfiguration CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        final Pair<WAConfiguration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WAConfiguration::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();
    }

    // Behaviour
    public final ForgeConfigSpec.IntValue stalkRange;

    // Damage
    public final ForgeConfigSpec.EnumValue<HurtHelper.HurtType> hurtType;


    public WAConfiguration(ForgeConfigSpec.Builder builder) {
        builder.push("behaviour");
        stalkRange = builder.translation("config.weeping_angels.stalk_range").comment("Determines the range quantum locked entities will check if the player is looking in").defineInRange("stalk_range", 65, 1, 100);
        builder.pop();

        builder.push("damage");
        hurtType = builder.translation("config.weeping_angels.hurt_type").comment("Hurt").defineEnum("hurt_type", HurtHelper.HurtType.PICKAXE_AND_GENERATOR);
        builder.pop();
    }


}
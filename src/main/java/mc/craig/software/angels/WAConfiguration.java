package mc.craig.software.angels;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class WAConfiguration {
    public static final WAConfiguration CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        final Pair<WAConfiguration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(WAConfiguration::new);
        CONFIG = specPair.getLeft();
        CONFIG_SPEC = specPair.getRight();
    }

    public final ForgeConfigSpec.IntValue stalkRange;


    public WAConfiguration(ForgeConfigSpec.Builder builder) {
        builder.push("behaviour");
        stalkRange = builder.translation("config.weeping_angels.stalk_range").comment("Determines the range quantum locked entities will check if the player is looking in").defineInRange("stalkRange", 65, 1, 100);
        builder.pop();
    }


}
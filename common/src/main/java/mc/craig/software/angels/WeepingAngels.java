package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static void init() {
        ModLoadingContext.registerConfig(MODID, ModConfig.Type.COMMON, WAConfiguration.CONFIG_SPEC);
    }

}

package mc.craig.software.angels;

import com.mojang.logging.LogUtils;
import mc.craig.software.angels.compat.vivecraft.ServerReflector;
import org.slf4j.Logger;

public class WeepingAngels {

    public static final ServerReflector VR_REFLECTOR = new ServerReflector();
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "weeping_angels";

    public static void init() {
        // Do all your common basic setup shit here
    }

}

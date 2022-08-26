package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;

public class WAConstants {

    // NBT
    public static final String TIME_SEEN = "timeSeen";
    public static final String EMOTION = "emotion";
    public static final String IS_SEEN = "isSeen";

    // Messages/UI
    public static final String ANOMALIES_DETECTED = createMessage("anomalies_detected");

    private static String createMessage(String s) {
        return "message."+ WeepingAngels.MODID + "." + s;
    }
}

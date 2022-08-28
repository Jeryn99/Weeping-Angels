package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;

public class WAConstants {

    // NBT
    public static final String TIME_SEEN = "timeSeen";
    public static final String EMOTION = "emotion";
    public static final String IS_HOOKED = "isHooked";
    public static final String IS_SEEN = "isSeen";
    public static final String COFFIN_TYPE = "coffinType";
    public static final String IS_DEMAT = "isDemat";
    public static final String ALPHA = "alpha";
    public static final String VARIANT = "variant";


    // Messages/UI
    public static final String ANOMALIES_DETECTED = createMessage("anomalies_detected");

    private static String createMessage(String s) {
        return "message." + WeepingAngels.MODID + "." + s;
    }
}

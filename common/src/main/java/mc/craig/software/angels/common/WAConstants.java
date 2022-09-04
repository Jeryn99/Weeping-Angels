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
    public static final String VARIANT = "angelVariant";
    public static final String ANIMATION = "animation";
    public static final String DROPS_LOOT = "dropsLoot";
    public static final String NEEDS_BOX = "needsBox";
    public static final String TICK_COUNT = "tickCount";
    public static final String ACTIVATED = "activated";
    public static final String SPAWNED = "hasSpawned";

    public static final String SNOW_STAGE = "snowStage";


    // Messages/UI
    public static final String ANOMALIES_DETECTED = createMessage("anomalies_detected");
    public static final String CHISEL_POSE = createMessage("chisel_pose");
    public static final String CHISEL_VARIANT = createMessage("chisel_variant");
    public static final String ANGEL_EMOTION = createMessage("angel_emotion");
    public static final String ANGEL_VARIANT = createMessage("angel_variant");
    public static final String ANGEL_POSES = createMessage("angel_poses");

    private static String createMessage(String s) {
        return "message." + WeepingAngels.MODID + "." + s;
    }
}

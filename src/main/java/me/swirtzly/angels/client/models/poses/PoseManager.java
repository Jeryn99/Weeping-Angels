package me.swirtzly.angels.client.models.poses;

import java.util.Random;

public class PoseManager {

    private static Random RANDOM = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static AngelPoses getPoseFromString(String poseName) {
        for (AngelPoses pose : AngelPoses.values()) {
            if (pose.name().equalsIgnoreCase(poseName)) {
                return pose;
            }
        }
        return AngelPoses.HIDING_FACE;
    }

    public enum AngelPoses {
        ANGRY(true), ANGRY_TWO(true), SHY, HIDING_FACE, OPEN_ARMS, IDLE, HIDING_FACE_ANGRY(true);

        private final boolean angryFace;

        AngelPoses(boolean hasAngryFace) {
            this.angryFace = hasAngryFace;
        }

        AngelPoses() {
            angryFace = false;
        }

        public boolean hasAngryFace() {
            return angryFace;
        }
    }
}

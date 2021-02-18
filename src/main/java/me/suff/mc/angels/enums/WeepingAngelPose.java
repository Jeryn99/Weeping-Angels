package me.suff.mc.angels.enums;

import java.util.Random;

public enum WeepingAngelPose {

    ANGRY(Emotion.ANGRY), FURIOUS(Emotion.ANGRY), SHY, HIDING, APPROACH(Emotion.SCREAM), IDLE;

    private final Emotion emotion;


    WeepingAngelPose(Emotion emotion) {
        this.emotion = emotion;
    }

    WeepingAngelPose() {
        this.emotion = Emotion.IDLE;
    }

    public static WeepingAngelPose getRandomPose(Random random) {
        int pick = random.nextInt(WeepingAngelPose.values().length);
        return WeepingAngelPose.values()[pick];
    }

    public static WeepingAngelPose getPose(String name) {
        for (WeepingAngelPose pose : WeepingAngelPose.values()) {
            if (pose.name().equalsIgnoreCase(name)) {
                return pose;
            }
        }
        return HIDING;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public enum Emotion {
        SCREAM, IDLE, ANGRY
    }


}
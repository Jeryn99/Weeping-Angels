package dev.jeryn.angels.common.entity.angel.ai;

import net.minecraft.util.RandomSource;

import java.util.Locale;

public enum AngelEmotion {
    ANGRY, IDLE, SCREAM;

    public String getId(){
        return name().toLowerCase();
    }

    public static AngelEmotion randomEmotion(RandomSource randomSource) {
        int pick = randomSource.nextInt(AngelEmotion.values().length);
        return AngelEmotion.values()[pick];
    }

    public static AngelEmotion find(String id){
        for (AngelEmotion value : values()) {
            if(value.getId().equalsIgnoreCase(id)){
                return value;
            }
        }
        return AngelEmotion.ANGRY;
    }
}

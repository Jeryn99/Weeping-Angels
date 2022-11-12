package mc.craig.software.angels.common.entity.angel.ai;

import java.util.Locale;
import java.util.Random;

public enum AngelEmotion {
    ANGRY, IDLE, SCREAM;

    public String getId(){
        return name().toLowerCase();
    }

    public static AngelEmotion randomEmotion(Random randomSource) {
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

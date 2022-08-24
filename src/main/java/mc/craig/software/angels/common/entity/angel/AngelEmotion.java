package mc.craig.software.angels.common.entity.angel;

import java.util.Locale;

public enum AngelEmotion {
    ANGRY, IDLE, SCREAM;

    public String getId(){
        return name().toLowerCase(Locale.ROOT);
    }
}

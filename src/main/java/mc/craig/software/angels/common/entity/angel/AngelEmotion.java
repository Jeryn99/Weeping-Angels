package mc.craig.software.angels.common.entity.angel;

import net.minecraft.util.RandomSource;

import java.util.Locale;

public enum AngelEmotion {
    ANGRY, IDLE, SCREAM;

    public String getId(){
        return name().toLowerCase(Locale.ROOT);
    }

    public static AngelEmotion randomEmotion(RandomSource randomSource) {
        int pick = randomSource.nextInt(AngelEmotion.values().length);
        return AngelEmotion.values()[pick];
    }
}

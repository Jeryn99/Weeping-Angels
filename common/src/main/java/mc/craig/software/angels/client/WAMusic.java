package mc.craig.software.angels.client;

import mc.craig.software.angels.common.WASounds;
import net.minecraft.sounds.Music;

public class WAMusic {

    public static Music CATACOMB_MUSIC;

    public static void init() {
        CATACOMB_MUSIC = new Music(WASounds.CATACOMB.get(), -1, -1, true);
    }

}

package mc.craig.software.angels.client;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

public class WAMusic {

    public static Music CATACOMB_MUSIC;

    public static void init() {
        CATACOMB_MUSIC = new Music(new SoundEvent(new ResourceLocation(WeepingAngels.MODID, "catacomb")), -1, -1, true);
    }

}

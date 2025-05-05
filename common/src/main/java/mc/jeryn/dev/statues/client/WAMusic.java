package mc.jeryn.dev.statues.client;

import mc.jeryn.dev.statues.WeepingAngels;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

public class WAMusic {

    public static Music CATACOMB_MUSIC;

    public static void init() {
        CATACOMB_MUSIC = new Music(Holder.direct(SoundEvent.createVariableRangeEvent(ResourceLocation.tryBuild(WeepingAngels.MODID, "catacomb"))), -1, -1, true);
    }

}

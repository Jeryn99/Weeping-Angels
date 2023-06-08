package mc.craig.software.angels.client.sounds;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.events.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EXTEfx;

public class EchoSound {

    private static boolean available;
    private static boolean setup;

    private static int auxEffectSlot;

    public static void addReloader() {
        ((ReloadableResourceManager) Minecraft.getInstance().getResourceManager()).registerReloadListener((ResourceManagerReloadListener) p_10758_ -> setup = false);
    }

    public static void setSelfPosition(int soundId) {
        if (!setup) {
            setupEffects();
            setup = true;
        }

        if (available && shouldEcho()) {
            AL11.alSource3i(soundId, EXTEfx.AL_AUXILIARY_SEND_FILTER, auxEffectSlot, 0, EXTEfx.AL_FILTER_NULL);
        }
    }

    private static void setupEffects() {
        available = AL.getCapabilities().ALC_EXT_EFX;
        if (!available) {
            WeepingAngels.LOGGER.warn("Unable to setup reverb effects, AL EFX not supported!");
            return;
        }

        auxEffectSlot = EXTEfx.alGenAuxiliaryEffectSlots();
        EXTEfx.alAuxiliaryEffectSloti(auxEffectSlot, EXTEfx.AL_EFFECTSLOT_AUXILIARY_SEND_AUTO, AL10.AL_TRUE);

        int reverbEffectSlot = EXTEfx.alGenEffects();

        EXTEfx.alEffecti(reverbEffectSlot, EXTEfx.AL_EFFECT_TYPE, EXTEfx.AL_EFFECT_EAXREVERB);
        EXTEfx.alEffectf(reverbEffectSlot, EXTEfx.AL_EAXREVERB_DECAY_TIME, 4F);

        EXTEfx.alAuxiliaryEffectSloti(auxEffectSlot, EXTEfx.AL_EFFECTSLOT_EFFECT, reverbEffectSlot);
    }

    private static boolean shouldEcho() {
        return ClientEvents.isInCatacombs;
    }

}
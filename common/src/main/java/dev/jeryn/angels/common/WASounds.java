package dev.jeryn.angels.common;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class WASounds {

    public static final DeferredRegistry<SoundEvent> SOUNDS = DeferredRegistry.create(WeepingAngels.MODID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> DING = setUpSound("ding");
    public static final RegistrySupplier<SoundEvent> BLOW = setUpSound("blow");
    public static final RegistrySupplier<SoundEvent> NECK_SNAP = setUpSound("neck_snap");
    public static final RegistrySupplier<SoundEvent> PROJECTOR = setUpSound("projector");
    public static final RegistrySupplier<SoundEvent> ANGEL_MOCKING = setUpSound("angel_mocking");
    public static final RegistrySupplier<SoundEvent> TARDIS_TAKEOFF = setUpSound("tardis_takeoff");
    public static final RegistrySupplier<SoundEvent> DISC_SALLY = setUpSound("disc_sally");
    public static final RegistrySupplier<SoundEvent> DISC_TIME_PREVAILS = setUpSound("disc_time_prevails");
    public static final RegistrySupplier<SoundEvent> KNOCK = setUpSound("knock");
    public static final RegistrySupplier<SoundEvent> LOCKED = setUpSound("locked");
    public static final RegistrySupplier<SoundEvent> CRUMBLING = setUpSound("crumbling");
    public static final RegistrySupplier<SoundEvent> CATACOMB = setUpSound("catacomb");
    public static final RegistrySupplier<SoundEvent> TELEPORT = setUpSound("teleport");

    private static RegistrySupplier<SoundEvent> setUpSound(String soundName) {
        SoundEvent sound = SoundEvent.createFixedRangeEvent(new ResourceLocation(WeepingAngels.MODID, soundName), 1);
        return SOUNDS.register(soundName, () -> sound);
    }

}

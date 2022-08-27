package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class WASounds {

    public static final DeferredRegistry<SoundEvent> SOUNDS = DeferredRegistry.create(WeepingAngels.MODID, Registry.SOUND_EVENT_REGISTRY);

    public static final RegistrySupplier<SoundEvent> DING = SOUNDS.register("ding", () -> setUpSound("ding"));
    public static final RegistrySupplier<SoundEvent> BLOW = SOUNDS.register("blow", () -> setUpSound("blow"));
    public static final RegistrySupplier<SoundEvent> NECK_SNAP = SOUNDS.register("neck_snap", () -> setUpSound("neck_snap"));
    public static final RegistrySupplier<SoundEvent> PROJECTOR = SOUNDS.register("projector", () -> setUpSound("projector"));
    public static final RegistrySupplier<SoundEvent> ANGEL_MOCKING = SOUNDS.register("angel_mocking", () -> setUpSound("angel_mocking"));
    public static final RegistrySupplier<SoundEvent> TARDIS_TAKEOFF = SOUNDS.register("tardis_takeoff", () -> setUpSound("tardis_takeoff"));
    public static final RegistrySupplier<SoundEvent> DISC_SALLY = SOUNDS.register("disc_sally", () -> setUpSound("disc_sally"));
    public static final RegistrySupplier<SoundEvent> DISC_TIME_PREVAILS = SOUNDS.register("disc_time_prevails", () -> setUpSound("disc_time_prevails"));
    public static final RegistrySupplier<SoundEvent> KNOCK = SOUNDS.register("knock", () -> setUpSound("knock"));

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName));
    }

}

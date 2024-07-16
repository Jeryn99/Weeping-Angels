package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class WASounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(WeepingAngels.MODID, Registries.SOUND_EVENT);

    public static final RegistryHolder<SoundEvent, SoundEvent> DING = setUpSound("ding");
    public static final RegistryHolder<SoundEvent, SoundEvent> BLOW = setUpSound("blow");
    public static final RegistryHolder<SoundEvent, SoundEvent> NECK_SNAP = setUpSound("neck_snap");
    public static final RegistryHolder<SoundEvent, SoundEvent> PROJECTOR = setUpSound("projector");
    public static final RegistryHolder<SoundEvent, SoundEvent> ANGEL_MOCKING = setUpSound("angel_mocking");
    public static final RegistryHolder<SoundEvent, SoundEvent> TARDIS_TAKEOFF = setUpSound("tardis_takeoff");
    public static final RegistryHolder<SoundEvent, SoundEvent> DISC_SALLY = setUpSound("disc_sally");
    public static final RegistryHolder<SoundEvent, SoundEvent> DISC_TIME_PREVAILS = setUpSound("disc_time_prevails");
    public static final RegistryHolder<SoundEvent, SoundEvent> KNOCK = setUpSound("knock");
    public static final RegistryHolder<SoundEvent, SoundEvent> LOCKED = setUpSound("locked");
    public static final RegistryHolder<SoundEvent, SoundEvent> CRUMBLING = setUpSound("crumbling");
    public static final RegistryHolder<SoundEvent, SoundEvent> CATACOMB = setUpSound("catacomb");
    public static final RegistryHolder<SoundEvent, SoundEvent> TELEPORT = setUpSound("teleport");

    private static RegistryHolder<SoundEvent, SoundEvent> setUpSound(String soundName) {
        SoundEvent sound = SoundEvent.createFixedRangeEvent(ResourceLocation.tryBuild(WeepingAngels.MODID, soundName), 1);
        return SOUNDS.register(soundName, () -> sound);
    }

}

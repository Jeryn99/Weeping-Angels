package mc.craig.software.angels.common;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WASounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WeepingAngels.MODID);


    public static final RegistryObject<SoundEvent> DING = SOUNDS.register("ding", () -> setUpSound("ding"));
    public static final RegistryObject<SoundEvent> BLOW = SOUNDS.register("blow", () -> setUpSound("blow"));
    public static final RegistryObject<SoundEvent> NECK_SNAP = SOUNDS.register("neck_snap", () -> setUpSound("neck_snap"));
    public static final RegistryObject<SoundEvent> PROJECTOR = SOUNDS.register("projector", () -> setUpSound("projector"));
    public static final RegistryObject<SoundEvent> ANGEL_MOCKING = SOUNDS.register("angel_mocking", () -> setUpSound("angel_mocking"));
    public static final RegistryObject<SoundEvent> TARDIS_TAKEOFF = SOUNDS.register("tardis_takeoff", () -> setUpSound("tardis_takeoff"));
    public static final RegistryObject<SoundEvent> DISC_SALLY = SOUNDS.register("disc_sally", () -> setUpSound("disc_sally"));
    public static final RegistryObject<SoundEvent> DISC_TIME_PREVAILS = SOUNDS.register("disc_time_prevails", () -> setUpSound("disc_time_prevails"));
    public static final RegistryObject<SoundEvent> KNOCK = SOUNDS.register("knock", () -> setUpSound("knock"));

    private static SoundEvent setUpSound(String soundName) {
        return new SoundEvent(new ResourceLocation(WeepingAngels.MODID, soundName));
    }

}

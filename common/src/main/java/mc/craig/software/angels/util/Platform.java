package mc.craig.software.angels.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.PlatformOnly;
import dev.architectury.injectables.targets.ArchitecturyTarget;
import net.minecraft.server.MinecraftServer;

import java.util.Collection;

public class Platform {

    private static final boolean FORGE = ArchitecturyTarget.getCurrentTarget().equals(PlatformOnly.FORGE);

    private static byte ARCHITECTURY_LOADED = 0;


    @ExpectPlatform
    public static void init() {
    }

    @ExpectPlatform
    public static boolean isProduction() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Collection<String> getModIds() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isClient() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isServer() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static MinecraftServer getServer() {
        throw new AssertionError();
    }

    public static boolean isArchitecturyLoaded() {
        if (ARCHITECTURY_LOADED == 0) {
            ARCHITECTURY_LOADED = (byte) (isModLoaded("architectury") ? 2 : 1);
        }
        return ARCHITECTURY_LOADED == 2;
    }
}

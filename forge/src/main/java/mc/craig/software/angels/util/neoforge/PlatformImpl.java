package mc.craig.software.angels.util.neoforge;

import net.minecraft.server.MinecraftServer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import net.neoforged.neoforgespi.language.IModInfo;


import java.util.ArrayList;
import java.util.Collection;

public class PlatformImpl {

    public static boolean isProduction() {
        return FMLLoader.isProduction();
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static Collection<String> getModIds() {
        ArrayList<String> modIds = new ArrayList<>();
        for (IModInfo mod : ModList.get().getMods()) {
            modIds.add(mod.getModId());
        }

        return modIds;
    }

    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    public static boolean isServer() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    public static void init() {
    }

    public static MinecraftServer getServer() {
        return ServerLifecycleHooks.getCurrentServer();
    }
}

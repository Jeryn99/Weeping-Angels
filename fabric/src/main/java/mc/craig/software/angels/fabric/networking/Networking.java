package mc.craig.software.angels.fabric.networking;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.CatacombTracker;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.resources.ResourceLocation;

public class Networking {

    public static final ResourceLocation UPDATE_CATACOMB = new ResourceLocation(WeepingAngels.MODID, "update_catacomb");


    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(UPDATE_CATACOMB, (client, handler, buf, responseSender) -> {
            CatacombTracker.setIsInCatacomb(buf.readBoolean());
        });
    }

}

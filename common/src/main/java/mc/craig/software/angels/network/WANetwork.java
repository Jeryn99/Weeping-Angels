package mc.craig.software.angels.network;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.network.messages.UpdateCatacombMessage;
import net.minecraft.resources.ResourceLocation;

public class WANetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(new ResourceLocation(WeepingAngels.MODID, "channel"));

    public static MessageType UPDATE_CATACOMB;

    public static void init() {
        UPDATE_CATACOMB = NETWORK.registerS2C("update_catacomb", UpdateCatacombMessage::new);
    }

}

package mc.craig.software.angels.network;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.network.messages.UpdateCatacombMessage;
import mc.craig.software.angels.network.messages.UpdateStatueMessage;
import net.minecraft.resources.ResourceLocation;

public class WANetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(ResourceLocation.tryBuild(WeepingAngels.MODID, "channel"));

    public static MessageType UPDATE_CATACOMB, UPDATE_STATUE;

    public static void init() {
        UPDATE_CATACOMB = NETWORK.registerS2C("update_catacomb", UpdateCatacombMessage::new);
        UPDATE_STATUE = NETWORK.registerC2S("update_statue", UpdateStatueMessage::new);
    }

}

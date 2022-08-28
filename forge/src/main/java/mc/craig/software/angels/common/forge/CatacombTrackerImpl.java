package mc.craig.software.angels.common.forge;

import mc.craig.software.angels.forge.network.Networking;
import mc.craig.software.angels.forge.network.messages.MessageCatacomb;
import net.minecraft.server.level.ServerPlayer;

public class CatacombTrackerImpl {

    public static void tellClient(ServerPlayer player, boolean isInCatacomb) {
        Networking.sendTo(new MessageCatacomb(isInCatacomb), player);
    }

}

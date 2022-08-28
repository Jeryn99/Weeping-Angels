package mc.craig.software.angels.common.fabric;

import io.netty.buffer.Unpooled;
import mc.craig.software.angels.fabric.networking.Networking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class CatacombTrackerImpl {

    public static void tellClient(ServerPlayer player, boolean isInCatacomb) {
        FriendlyByteBuf passedData = new FriendlyByteBuf(Unpooled.buffer());
        passedData.writeBoolean(isInCatacomb);
        ServerPlayNetworking.send(player, Networking.UPDATE_CATACOMB, passedData);
    }

}

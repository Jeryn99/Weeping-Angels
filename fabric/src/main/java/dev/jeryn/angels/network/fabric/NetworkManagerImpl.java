package dev.jeryn.angels.network.fabric;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.network.MessageC2S;
import dev.jeryn.angels.network.MessageS2C;
import dev.jeryn.angels.network.MessageType;
import dev.jeryn.angels.network.NetworkManager;
import dev.jeryn.angels.util.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class NetworkManagerImpl extends NetworkManager {

    public static NetworkManager create(ResourceLocation channelName) {
        return new NetworkManagerImpl(channelName);
    }

    public NetworkManagerImpl(ResourceLocation channelName) {
        super(channelName);
        ServerPlayNetworking.registerGlobalReceiver(channelName, (server, player, handler, buf, responseSender) -> {
            var msgId = buf.readUtf();

            if (!this.toServer.containsKey(msgId)) {
                WeepingAngels.LOGGER.warn("Unknown message id received on server: " + msgId);
                return;
            }

            MessageType type = this.toServer.get(msgId);
            MessageC2S message = (MessageC2S) type.getDecoder().decode(buf);
            server.execute(message::handle);
        });

        if(Platform.isClient()) {
            this.registerClient();
        }
    }

    @Environment(EnvType.CLIENT)
    private void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(channelName, (client, handler, buf, responseSender) -> {
            var msgId = buf.readUtf();

            if (!this.toClient.containsKey(msgId)) {
                WeepingAngels.LOGGER.warn("Unknown message id received on client: " + msgId);
                return;
            }

            MessageType type = this.toClient.get(msgId);
            MessageS2C message = (MessageS2C) type.getDecoder().decode(buf);
            client.execute(message::handle);
        });
    }

    @Override
    public void sendToServer(MessageC2S message) {
        if (!this.toServer.containsValue(message.getType())) {
            WeepingAngels.LOGGER.warn("Message type not registered: " + message.getType().getId());
            return;
        }

        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeUtf(message.getType().getId());
        message.toBytes(buf);
        ClientPlayNetworking.send(this.channelName, buf);
    }

    @Override
    public void sendToPlayer(ServerPlayer player, MessageS2C message) {
        if (!this.toClient.containsValue(message.getType())) {
            WeepingAngels.LOGGER.warn("Message type not registered: " + message.getType().getId());
            return;
        }

        FriendlyByteBuf buf = PacketByteBufs.create();
        buf.writeUtf(message.getType().getId());
        message.toBytes(buf);
        ServerPlayNetworking.send(player, this.channelName, buf);
    }
}

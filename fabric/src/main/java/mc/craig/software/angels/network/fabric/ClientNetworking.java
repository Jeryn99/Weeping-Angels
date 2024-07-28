package mc.craig.software.angels.network.fabric;

import mc.craig.software.angels.network.WANetworkManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBundlePacket;

import java.util.ArrayList;
import java.util.List;

import static mc.craig.software.angels.network.fabric.WANetworkManagerImpl.makeContext;

public class ClientNetworking {

    @Environment(EnvType.CLIENT)
    public static <T extends CustomPacketPayload> void doClientRegister(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, WANetworkManager.Handler<T> receiver) {
        PayloadTypeRegistry.playS2C().register(type, codec);
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            receiver.receive(payload, makeContext(context.player(), context.client(), true));
        });


    }

    @Environment(EnvType.CLIENT)
    public static <T extends CustomPacketPayload> Packet<?> createC2SPacket(T payload) {
        return ClientPlayNetworking.createC2SPacket(payload);
    }

    @Environment(EnvType.CLIENT)
    public static void sendToServer(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        ClientPlayNetworking.send(payload);
        for (CustomPacketPayload packetPayload : payloads) {
            ClientPlayNetworking.send(packetPayload);
        }
    }

    public static Packet<?> makeClientboundPacket(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        if (payloads.length > 0) {
            final List<Packet<? super ClientGamePacketListener>> packets = new ArrayList<>();
            packets.add(new ClientboundCustomPayloadPacket(payload));
            for (CustomPacketPayload otherPayload : payloads) {
                packets.add(new ClientboundCustomPayloadPacket(otherPayload));
            }
            return new ClientboundBundlePacket(packets);
        } else {
            return new ClientboundCustomPayloadPacket(payload);
        }
    }

}

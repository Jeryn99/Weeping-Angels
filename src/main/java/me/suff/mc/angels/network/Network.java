package me.suff.mc.angels.network;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.network.messages.MessageCatacomb;
import me.suff.mc.angels.network.messages.MessageSFX;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * Created by Craig on 02/03/2020 @ 11:50
 */
public class Network {
    private static final String PROTOCOL_VERSION = Integer.toString(1);

    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(WeepingAngels.MODID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

    public static int MESSAGE_COUNTER = 0;


    public static void init() {
        INSTANCE.messageBuilder(MessageSFX.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(MessageSFX::encode)
                .decoder(MessageSFX::decode)
                .consumer(MessageSFX.Handler::handle)
                .add();

        INSTANCE.messageBuilder(MessageCatacomb.class, nextId(), NetworkDirection.PLAY_TO_CLIENT)
                .encoder(MessageCatacomb::encode)
                .decoder(MessageCatacomb::decode)
                .consumer(MessageCatacomb.Handler::handle)
                .add();
    }

    private static int nextId() {
        MESSAGE_COUNTER++;
        return MESSAGE_COUNTER;
    }

    /**
     * Sends a packet to the server.<br>
     * Must be called Client side.
     */
    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    /**
     * Send a packet to a specific player.<br>
     * Must be called Server side.
     */
    public static void sendTo(Object msg, ServerPlayer player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendPacketToAll(Object packet) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendTo(packet, player);
        }
    }
}

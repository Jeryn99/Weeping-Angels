package dev.jeryn.angels.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides an abstract interface for sending messages over a network. It maintains a map of
 * registered {@link MessageType} objects, which are used to encode and decode messages to be sent to the
 * server and to clients.
 * */
public abstract class NetworkManager {

    /** The resource location representing the channel name for this network manager. */
    protected final ResourceLocation channelName;
    /** A map of registered message types that can be sent to the server. */
    protected final Map<String, MessageType> toServer = new HashMap<>();
    /** A map of registered message types that can be sent to clients. */
    protected final Map<String, MessageType> toClient = new HashMap<>();

    /**
     * Factory method for creating a new instance of a `NetworkManager` for the specified channel name.
     *
     * @param channelName the resource location representing the channel name for the new `NetworkManager`
     * @return a new `NetworkManager` instance for the specified channel name
     */
    @ExpectPlatform
    public static NetworkManager create(ResourceLocation channelName) {
        throw new AssertionError();
    }

    /**
     * Constructs a new `NetworkManager` instance with the specified channel name.
     *
     * @param channelName the resource location representing the channel name for this `NetworkManager`
     */
    public NetworkManager(ResourceLocation channelName) {
        this.channelName = channelName;
    }

    /**
     * Registers a new message type that can be sent from the server to clients.
     *
     * @param id the identifier for the message type
     * @param decoder the {@link MessageDecoder} to use for encoding and decoding messages of this type
     * @return the newly registered {@link MessageType}
     */
    public MessageType registerS2C(String id, MessageDecoder<MessageS2C> decoder) {
        var msgType = new MessageType(id, this, decoder, false);
        this.toClient.put(id, msgType);
        return msgType;
    }

    /**
     * Registers a new message type that can be sent from clients to the server.
     *
     * @param id the identifier for the message type
     * @param decoder the {@link MessageDecoder} to use for encoding and decoding messages of this type
     * @return the newly registered {@link MessageType}
     */
    public MessageType registerC2S(String id, MessageDecoder<MessageC2S> decoder) {
        var msgType = new MessageType(id, this, decoder, true);
        this.toServer.put(id, msgType);
        return msgType;
    }

    /**
     * Sends the specified message to the server.
     *
     * @param message the message to send to the server
     */
    public abstract void sendToServer(MessageC2S message);

    /**
     * Sends the specified message to the specified player.
     *
     * @param player the player to send the message to
     * @param message the message to send to the player
     */
    public abstract void sendToPlayer(ServerPlayer player, MessageS2C message);

    /**
     * Sends the specified message to all players in the specified level (dimension).
     *
     * @param level the level (dimension) to send the message to
     * @param message the message to send to all players in the level
     */
    public void sendToDimension(Level level, MessageS2C message) {
        if(!level.isClientSide) {
            for (Player player : level.players()) {
                this.sendToPlayer((ServerPlayer) player, message);
            }
        }
    }

    /**
     * This functional interface defines a method for decoding a message from a `FriendlyByteBuf`.
     *
     * @param <T> the type of message to decode
     */
    @FunctionalInterface
    public interface MessageDecoder<T extends Message> {

        /**
         * Decodes the specified message from the given `FriendlyByteBuf`.
         *
         * @param buf the `FriendlyByteBuf` to decode the message from
         * @return the decoded message
         */
        T decode(FriendlyByteBuf buf);

    }

}

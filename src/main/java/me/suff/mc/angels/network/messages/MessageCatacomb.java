package me.suff.mc.angels.network.messages;

import me.suff.mc.angels.common.events.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * Created by Craig on 20/01/2019.
 */
public class MessageCatacomb {

    private final boolean isInCata;

    public MessageCatacomb(boolean isInCata) {
        this.isInCata = isInCata;
    }

    public static void encode(MessageCatacomb message, FriendlyByteBuf buffer) {
        buffer.writeBoolean(message.isInCata);
    }

    public static MessageCatacomb decode(FriendlyByteBuf buffer) {
        return new MessageCatacomb(buffer.readBoolean());
    }

    public static class Handler {

        public static void handle(MessageCatacomb message, Supplier<NetworkEvent.Context> ctx) {
            Minecraft.getInstance().submitAsync(() -> ClientEvents.isInCatacombs = message.isInCata);
            ctx.get().setPacketHandled(true);
        }
    }

}

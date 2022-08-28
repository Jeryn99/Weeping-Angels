package mc.craig.software.angels.forge.network.messages;

import mc.craig.software.angels.common.CatacombTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

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
            Minecraft.getInstance().submitAsync(() -> CatacombTracker.setIsInCatacomb(message.isInCata));
            ctx.get().setPacketHandled(true);
        }
    }

}
package craig.software.mc.angels.network.messages;

import craig.software.mc.angels.client.ClientEvents;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Created by Craig on 20/01/2019.
 */
public class MessageCatacomb {

    private final boolean isInCata;

    public MessageCatacomb(boolean isInCata) {
        this.isInCata = isInCata;
    }

    public static void encode(MessageCatacomb message, PacketBuffer buffer) {
        buffer.writeBoolean(message.isInCata);
    }

    public static MessageCatacomb decode(PacketBuffer buffer) {
        return new MessageCatacomb(buffer.readBoolean());
    }

    public static class Handler {

        public static void handle(MessageCatacomb message, Supplier<NetworkEvent.Context> ctx) {
            Minecraft.getInstance().submitAsync(() -> ClientEvents.isInCatacombs = message.isInCata);
            ctx.get().setPacketHandled(true);
        }
    }

}

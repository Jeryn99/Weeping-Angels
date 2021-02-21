package me.swirtzly.minecraft.angels.network.messages;

import me.swirtzly.minecraft.angels.client.ClientEvents;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Created by Craig on 20/01/2019.
 */
public class MessageCatacomb {

    private boolean isInCata;

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

        public static void handle(MessageCatacomb message, Supplier< NetworkEvent.Context > ctx) {
            Minecraft.getInstance().deferTask(() -> ClientEvents.isInCatacombs = message.isInCata);
            ctx.get().setPacketHandled(true);
        }
    }

}

package me.suff.mc.angels.network.messages;

import java.util.function.Supplier;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by Craig on 20/01/2019.
 */
public class MessageSFX {

    private final ResourceLocation sound;

    public MessageSFX(ResourceLocation sound) {
        this.sound = sound;
    }

    public static void encode(MessageSFX message, PacketBuffer buffer) {
        buffer.writeResourceLocation(message.sound);
    }

    public static MessageSFX decode(PacketBuffer buffer) {
        return new MessageSFX(buffer.readResourceLocation());
    }

    public static class Handler {

        public static void handle(MessageSFX message, Supplier<NetworkEvent.Context> ctx) {
            Minecraft.getInstance().submitAsync(() -> ClientUtil.playSound(ForgeRegistries.SOUND_EVENTS.getValue(message.sound), 1));
            ctx.get().setPacketHandled(true);
        }
    }

}

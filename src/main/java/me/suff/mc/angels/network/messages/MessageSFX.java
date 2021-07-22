package me.suff.mc.angels.network.messages;

import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fmllegacy.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

/**
 * Created by Craig on 20/01/2019.
 */
public class MessageSFX {

    private final ResourceLocation sound;

    public MessageSFX(ResourceLocation sound) {
        this.sound = sound;
    }

    public static void encode(MessageSFX message, FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(message.sound);
    }

    public static MessageSFX decode(FriendlyByteBuf buffer) {
        return new MessageSFX(buffer.readResourceLocation());
    }

    public static class Handler {

        public static void handle(MessageSFX message, Supplier<NetworkEvent.Context> ctx) {
            Minecraft.getInstance().submitAsync(() -> ClientUtil.playSound(ForgeRegistries.SOUND_EVENTS.getValue(message.sound), 1));
            ctx.get().setPacketHandled(true);
        }
    }

}

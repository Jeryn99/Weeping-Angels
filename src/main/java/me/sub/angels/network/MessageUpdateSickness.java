package me.sub.angels.network;

import io.netty.buffer.ByteBuf;
import me.sub.angels.common.sickness.CapabilitySickness;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageUpdateSickness implements IMessage {
    public UUID playerUUID;
    public NBTTagCompound nbt;

    public MessageUpdateSickness() {
    }

    public MessageUpdateSickness(EntityPlayer player) {
        this.playerUUID = player.getPersistentID();
        nbt = (NBTTagCompound) CapabilitySickness.CAPABILITY.getStorage().writeNBT(CapabilitySickness.CAPABILITY, player.getCapability(CapabilitySickness.CAPABILITY, null), null);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
        this.nbt = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.playerUUID.toString());
        ByteBufUtils.writeTag(buf, this.nbt);
    }

    public static class Handler implements IMessageHandler<MessageUpdateSickness, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateSickness message, MessageContext ctx) {
            Minecraft.getMinecraft().addScheduledTask(() -> CapabilitySickness.CAPABILITY.getStorage().readNBT(CapabilitySickness.CAPABILITY, Minecraft.getMinecraft().world.getPlayerEntityByUUID(message.playerUUID).getCapability(CapabilitySickness.CAPABILITY, null), null, message.nbt));
            return null;
        }
    }
}

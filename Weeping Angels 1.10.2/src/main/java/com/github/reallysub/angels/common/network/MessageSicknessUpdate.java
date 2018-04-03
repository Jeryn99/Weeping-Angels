package com.github.reallysub.angels.common.network;

import java.util.UUID;

import com.github.reallysub.angels.common.capability.CapabilityAngelSickness;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSicknessUpdate implements IMessage {
	public UUID playerUUID;
	public NBTTagCompound nbt;
	
	public MessageSicknessUpdate() {}
	
	public MessageSicknessUpdate(EntityPlayer player) {
		this.playerUUID = player.getPersistentID();
		nbt = (NBTTagCompound) CapabilityAngelSickness.CAP.getStorage().writeNBT(CapabilityAngelSickness.CAP, player.getCapability(CapabilityAngelSickness.CAP, null), null);
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
	
	public static class Handler implements IMessageHandler<MessageSicknessUpdate, IMessage> {
		@Override
		public IMessage onMessage(MessageSicknessUpdate message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> CapabilityAngelSickness.CAP.getStorage().readNBT(CapabilityAngelSickness.CAP, Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(message.playerUUID).getCapability(CapabilityAngelSickness.CAP, null), null, message.nbt));
			return null;
		}
	}
}

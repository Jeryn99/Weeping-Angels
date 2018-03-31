package com.github.reallysub.angels.common.packets;

import com.github.reallysub.angels.common.entities.EntityAngel;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageAngelSeen implements IMessage {
	
	public MessageAngelSeen() {}
	
	private int angel;
	
	public MessageAngelSeen(int angel) {
		this.angel = angel;
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(angel);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		angel = buf.readInt();
	}
	
	public static class AngelSeenHandler implements IMessageHandler<MessageAngelSeen, IMessage> {
		
		@Override
		public IMessage onMessage(MessageAngelSeen message, MessageContext ctx) {
			
			EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			
			serverPlayer.getServerWorld().addScheduledTask(() -> {
				
				Entity entity = serverPlayer.world.getEntityByID(message.angel);
				
				if (entity instanceof EntityAngel) {
					EntityAngel angel = (EntityAngel) entity;
					angel.setSeen(true);
				}
				
			});
			return null;
		}
	}
}

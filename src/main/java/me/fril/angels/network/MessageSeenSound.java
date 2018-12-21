package me.fril.angels.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSeenSound implements IMessage {
	
	public MessageSeenSound() {
	}
	
	public MessageSeenSound(int aaa){
	
	}
	
	@Override
	public void toBytes(ByteBuf buf) {
	
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	
	}
	
	public static class Handler implements IMessageHandler<MessageSeenSound, IMessage> {
		@Override
		public IMessage onMessage(MessageSeenSound message, MessageContext ctx) {
			
			return null;
		}
	}
}

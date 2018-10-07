package me.sub.angels.common.packets;

import io.netty.buffer.ByteBuf;
import me.sub.angels.common.entities.EntityQuantumLockBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateSeen implements IMessage {

    private int entityID;

    public MessageUpdateSeen() {
    }

    public MessageUpdateSeen(int entityID) {
        this.entityID = entityID;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(entityID);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        entityID = buf.readInt();
    }

    public static class Handler implements IMessageHandler<MessageUpdateSeen, IMessage> {
        @Override
        public IMessage onMessage(MessageUpdateSeen message, MessageContext ctx) {
            EntityPlayerMP serverPlayer = ctx.getServerHandler().player;

            serverPlayer.getServerWorld().addScheduledTask(() -> {
                Entity entity = serverPlayer.getEntityWorld().getEntityByID(message.entityID);

                if (entity instanceof EntityQuantumLockBase) {
                    EntityQuantumLockBase quantumLockBase = (EntityQuantumLockBase) entity;
                    if (quantumLockBase.getSeenTime() == 0) {
                        quantumLockBase.setSeenTime(1);
                    } else {
                        quantumLockBase.setSeenTime(quantumLockBase.getSeenTime() + 1);
                    }
                }
            });

            return null;
        }
    }
}

package dev.jeryn.angels.network;

import net.minecraft.network.FriendlyByteBuf;


public abstract class Message {
    public abstract MessageType getType();

    public abstract void toBytes(FriendlyByteBuf buf);

    public abstract void handle();

}

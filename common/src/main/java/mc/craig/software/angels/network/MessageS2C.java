package mc.craig.software.angels.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

public abstract class MessageS2C extends Message {

    public void send(ServerPlayer player) {
        this.getType().getNetworkManager().sendToPlayer(player, this);
    }

    public void sendToDimension(Level level) {
        this.getType().getNetworkManager().sendToDimension(level, this);
    }

}

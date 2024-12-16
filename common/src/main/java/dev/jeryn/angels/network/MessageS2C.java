package dev.jeryn.angels.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class MessageS2C extends Message {

    public void send(ServerPlayer player) {
        this.getType().getNetworkManager().sendToPlayer(player, this);
    }

    public void sendToDimension(Level level) {
        this.getType().getNetworkManager().sendToDimension(level, this);
    }

    public void sendToAll(ServerLevel level){
        MinecraftServer server = level.getServer();
        List<ServerPlayer> player = server.getPlayerList().getPlayers();
        for (ServerPlayer serverPlayer : player) {
            send(serverPlayer);
        }
    }

}

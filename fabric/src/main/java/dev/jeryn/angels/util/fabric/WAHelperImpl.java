package dev.jeryn.angels.util.fabric;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.Entity;

public class WAHelperImpl {

    public static Packet<?> spawnPacket(Entity livingEntity) {
        return new ClientboundAddEntityPacket(livingEntity);
    }
}

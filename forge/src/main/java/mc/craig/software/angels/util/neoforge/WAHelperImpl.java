package mc.craig.software.angels.util.neoforge;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkHooks;

public class WAHelperImpl {

    public static Packet<?> spawnPacket(Entity livingEntity) {
        return NetworkHooks.getEntitySpawningPacket(livingEntity);
    }

}

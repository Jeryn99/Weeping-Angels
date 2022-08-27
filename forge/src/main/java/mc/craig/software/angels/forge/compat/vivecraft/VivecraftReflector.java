package mc.craig.software.angels.forge.compat.vivecraft;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public abstract class VivecraftReflector {
    public abstract boolean init();

    public abstract boolean isVRPlayer(Player player);

    public abstract Vec3 getHMDPos(Player player);

    public abstract Vec3 getHMDRot(Player player);
}
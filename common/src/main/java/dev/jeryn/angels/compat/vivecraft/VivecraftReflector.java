package dev.jeryn.angels.compat.vivecraft;

import net.blf02.vrapi.api.IVRAPI;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public abstract class VivecraftReflector {
    public abstract boolean init(IVRAPI ivrapi);

    public abstract boolean isVRPlayer(Player player);

    public abstract Vec3 getHMDPos(Player player);

    public abstract Vec3 getHMDRot(Player player);
}
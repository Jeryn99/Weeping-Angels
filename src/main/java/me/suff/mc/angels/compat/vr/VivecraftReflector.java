package me.suff.mc.angels.compat.vr;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;

public abstract class VivecraftReflector {
    public abstract boolean init();

    public abstract boolean isVRPlayer(PlayerEntity player);

    public abstract Vector3d getHMDPos(PlayerEntity player);

    public abstract Vector3d getHMDRot(PlayerEntity player);
}
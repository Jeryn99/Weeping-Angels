<<<<<<<< HEAD:forge/src/main/java/mc/craig/software/angels/forge/compat/vivecraft/VivecraftReflector.java
package mc.craig.software.angels.forge.compat.vivecraft;
========
package dev.jeryn.angels.compat.vivecraft;
>>>>>>>> b49d229f (Hello dev.jeryn):common/src/main/java/dev/jeryn/angels/compat/vivecraft/VivecraftReflector.java

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public abstract class VivecraftReflector {
    public abstract boolean init();

    public abstract boolean isVRPlayer(Player player);

    public abstract Vec3 getHMDPos(Player player);

    public abstract Vec3 getHMDRot(Player player);
}
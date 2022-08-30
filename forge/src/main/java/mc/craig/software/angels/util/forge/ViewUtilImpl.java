package mc.craig.software.angels.util.forge;

import mc.craig.software.angels.forge.WeepingAngelsForge;
import mc.craig.software.angels.util.ViewUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ViewUtilImpl {

    public static Vec3 manipulateVrRotation(Player player, Vec3 vec3) {
        if (ViewUtil.isVrPlayer(player)) {
            return WeepingAngelsForge.VR_REFLECTOR.getHMDRot(player);
        }
        return vec3;
    }

    public static Vec3 manipulateVrPosition(Player player, Vec3 vec3) {
        if (ViewUtil.isVrPlayer(player)) {
            return WeepingAngelsForge.VR_REFLECTOR.getHMDPos(player);
        }
        return vec3;
    }

    public static boolean isVrPlayer(Player player) {
        return WeepingAngelsForge.VR_REFLECTOR.isVRPlayer(player);
    }
}

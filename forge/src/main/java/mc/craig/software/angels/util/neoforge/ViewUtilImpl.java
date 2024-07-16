package mc.craig.software.angels.util.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.ViewUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ViewUtilImpl {

    public static Vec3 manipulateVrRotation(Player player, Vec3 vec3) {
        if (ViewUtil.isVrPlayer(player)) {
            return WeepingAngels.VR_HANDLER.getHMDRot(player);
        }
        return vec3;
    }

    public static Vec3 manipulateVrPosition(Player player, Vec3 vec3) {
        if (ViewUtil.isVrPlayer(player)) {
            return WeepingAngels.VR_HANDLER.getHMDPos(player);
        }
        return vec3;
    }

    public static boolean isVrPlayer(Player player) {
        return WeepingAngels.VR_HANDLER.isVRPlayer(player);
    }
}

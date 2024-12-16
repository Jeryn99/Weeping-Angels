package dev.jeryn.angels.compat.vivecraft;

import dev.jeryn.angels.WeepingAngels;
import net.blf02.vrapi.api.IVRAPI;
import net.blf02.vrapi.api.data.IVRPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class WAVRPluginHandler extends VivecraftReflector {

    @Override
    public boolean init(IVRAPI ivrapi) {
        
        WeepingAngels.LOGGER.info("Checking for Vivecraft Compatibility...");

        if(WAVRPluginStatus.hasPlugin){
            WeepingAngels.LOGGER.info("Vivecraft Compatibility enabled");
            VRInstance.ivrapi = ivrapi;
        } else {
            WeepingAngels.LOGGER.info("Vivecraft Compatibility disabled as it was not detected");
        }

        return WAVRPluginStatus.hasPlugin;
    }

    /**
     * Checks to see if the player is currently using a VR headset.
     *
     * @param player The Player
     * @return Returns true if the player is listed in the VR player lists.
     */
    @Override
    public boolean isVRPlayer(Player player) {
        if(WAVRPluginStatus.hasPlugin) {
            return VRInstance.ivrapi.playerInVR(player);
        }
        return false;
    }

    /**
     * Gets the position of the player's headset. Seems to be in world-space
     *
     * @param player The Player
     * @return The position
     */
    @Override
    public Vec3 getHMDPos(Player player) {
        if (isVRPlayer(player)) {
            IVRPlayer vrPlayer = VRInstance.ivrapi.getVRPlayer(player);
            return vrPlayer.getHMD().position();
        }
        return player.position().add(0, 1.62, 0);
    }


    /**
     * Gets a vector representing the direction the headset is facing. Does not include side-to-side tilts.
     *
     * @param player The Player
     * @return The direction vector
     */
    @Override
    public Vec3 getHMDRot(Player player) {

        if (isVRPlayer(player)) {
            IVRPlayer vrPlayer = VRInstance.ivrapi.getVRPlayer(player);
            return vrPlayer.getHMD().getLookAngle();
        }

        return player.getLookAngle();
    }
}
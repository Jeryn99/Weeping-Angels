package dev.jeryn.angels.compat;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.compat.vivecraft.WAVRPluginStatus;
import net.blf02.forge.VRAPIPlugin;
import net.blf02.forge.VRAPIPluginProvider;
import net.blf02.vrapi.api.IVRAPI;

@VRAPIPlugin
public class WAVRPluginForge implements VRAPIPluginProvider {

    public static IVRAPI vrAPI = null;

    @Override
    public void getVRAPI(IVRAPI ivrapi) {
        vrAPI = ivrapi;
        WAVRPluginStatus.hasPlugin = true;
        WeepingAngels.VR_HANDLER.init(vrAPI);
    }
}
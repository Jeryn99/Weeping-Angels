package mc.craig.software.angels.compat;

import mc.craig.software.angels.compat.vivecraft.WAVRPluginStatus;
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
    }
}
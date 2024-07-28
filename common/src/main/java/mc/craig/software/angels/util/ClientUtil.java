package mc.craig.software.angels.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtil {

    @Environment(EnvType.CLIENT)
    public static void playDectorSound(Player player){
        Minecraft mc = Minecraft.getInstance();
        mc.getSoundManager().playDelayed(new DetectorTickableSound(player), 100);
    }

}

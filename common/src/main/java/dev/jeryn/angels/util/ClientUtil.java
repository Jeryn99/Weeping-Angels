package dev.jeryn.angels.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtil {

    public static void playDectorSound(Player player){
        Minecraft mc = Minecraft.getInstance();
        mc.getSoundManager().play(new DetectorTickableSound(player));
    }

}

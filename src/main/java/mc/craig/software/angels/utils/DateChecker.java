package mc.craig.software.angels.utils;


import mc.craig.software.angels.client.renders.layers.WingsLayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;

import java.util.Calendar;

/* Created by Craig on 26/02/2021 */
public class DateChecker {

    public static Runnable DONATOR_RUNNABLE = WingsLayer::update;
    static Calendar calendar = Calendar.getInstance();

    public static void tick(TickEvent.ClientTickEvent event) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        if ((minutes == 0 || minutes == 39) && seconds == 10) {
            Minecraft.getInstance().submitAsync(DONATOR_RUNNABLE);
        }
    }

    public static boolean isXmas() {
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER;
    }
}

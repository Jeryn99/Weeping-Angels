package craig.software.mc.angels.utils;

import craig.software.mc.angels.client.renders.WingsLayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;

import java.util.Calendar;
import java.util.TimeZone;

/* Created by Craig on 26/02/2021 */
@OnlyIn(Dist.CLIENT)
public class DateChecker {

    static Calendar calendar = Calendar.getInstance();

    public static Runnable DONATOR_RUNNABLE = WingsLayer::update;


    public static void tick(TickEvent.ClientTickEvent event) {
        calendar.setTimeInMillis(System.currentTimeMillis());
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        if((minutes == 0 || minutes == 39) && seconds == 10) { //TODO download spam
            Minecraft.getInstance().submitAsync(DONATOR_RUNNABLE);
        }
    }

    public static boolean isXmas() {
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER;
    }
}

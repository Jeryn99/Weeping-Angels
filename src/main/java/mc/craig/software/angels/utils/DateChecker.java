package mc.craig.software.angels.utils;

import mc.craig.software.angels.client.renders.WingsLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;
import java.util.TimeZone;

/* Created by Craig on 26/02/2021 */
@OnlyIn(Dist.CLIENT)
public class DateChecker {

    static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    public static void tick() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        if (update(false)) {
            Thread thread = new Thread(WingsLayer::update);
            thread.start();
        }
    }

    public static boolean isXmas() {
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER;
    }

    public static boolean update(boolean force) {
        if (force) {
            Thread thread = new Thread(WingsLayer::update);
            thread.start();
        }
        return calendar.getTime().getMinutes() == 30;
    }
}

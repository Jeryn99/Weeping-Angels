package me.suff.mc.angels.utils;


import me.suff.mc.angels.client.renders.layers.WingsLayer;

import java.util.Calendar;
import java.util.TimeZone;

/* Created by Craig on 26/02/2021 */
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

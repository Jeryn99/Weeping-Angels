package me.suff.mc.angels.utils;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Calendar;

/* Created by Craig on 26/02/2021 */
@OnlyIn(Dist.CLIENT)
public class DateChecker {

    private static final Calendar calendar = Calendar.getInstance();

    public static void tick() {
        calendar.setTimeInMillis(System.currentTimeMillis());
    }

    public static boolean isXmas() {
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER;
    }

}

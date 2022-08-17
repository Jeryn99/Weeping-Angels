package craig.software.mc.angels.utils;

import craig.software.mc.angels.client.renders.WingsLayer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;

/* Created by Craig on 26/02/2021 */
@OnlyIn(Dist.CLIENT)
public class DateChecker {


    public static Runnable DONATOR_RUNNABLE = WingsLayer::update;

    public static void tick(TickEvent.ClientTickEvent event) {
        Calendar rightNow = Calendar.getInstance();
        int minutes = rightNow.get(Calendar.MINUTE);
        int second = rightNow.get(Calendar.SECOND);
        if ((minutes == 0 || minutes == 39) && second == 1) {
            Minecraft.getInstance().submitAsync(DONATOR_RUNNABLE);
        }
    }

    public static boolean isHalloween() {
        LocalDate localdate = LocalDate.now();
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return month == 10;
    }

    public static boolean isXmas() {
        LocalDate localdate = LocalDate.now();
        int month = localdate.get(ChronoField.MONTH_OF_YEAR);
        return month == 12;
    }
}

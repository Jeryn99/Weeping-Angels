package mc.craig.software.angels.utils;

import com.google.gson.JsonObject;
import mc.craig.software.angels.WeepingAngels;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.text.TranslationTextComponent;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class PlayerUtil {

    public static boolean isInHand(Hand hand, LivingEntity holder, Item item) {
        ItemStack heldItem = holder.getItemInHand(hand);
        return heldItem.getItem() == item;
    }

    public static boolean isInMainHand(LivingEntity holder, Item item) {
        return isInHand(Hand.MAIN_HAND, holder, item);
    }

    /**
     * Checks if player has item in offhand
     */
    public static boolean isInOffHand(LivingEntity holder, Item item) {
        return isInHand(Hand.OFF_HAND, holder, item);
    }

    /**
     * Checks if player has item in either hand
     */
    public static boolean isInEitherHand(LivingEntity holder, Item item) {
        return isInMainHand(holder, item) || isInOffHand(holder, item);
    }

    // MAIN_HAND xor OFF_HAND
    public static boolean isInOneHand(LivingEntity holder, Item item) {
        boolean mainHand = (isInMainHand(holder, item) && !isInOffHand(holder, item));
        boolean offHand = (isInOffHand(holder, item) && !isInMainHand(holder, item));
        return mainHand || offHand;
    }


    public static void sendMessageToPlayer(PlayerEntity player, TranslationTextComponent textComponent, boolean isHotBar) {
        if (player.level.isClientSide) return;
        player.displayClientMessage(textComponent, isHotBar);
    }

    public static JsonObject getResponse(URL url) throws IOException {
        try {
            HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
            uc.setConnectTimeout(1000);
            uc.connect();
            uc = (HttpsURLConnection) url.openConnection();
            uc.addRequestProperty("User-Agent", USER_AGENT);
            InputStream inputStream = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            return JSONUtils.parse(br);
        } catch (ConnectException e) {
            WeepingAngels.LOGGER.error("Connection issue when retrieving Donators!");
            e.printStackTrace();

            return null;
        }
    }
}

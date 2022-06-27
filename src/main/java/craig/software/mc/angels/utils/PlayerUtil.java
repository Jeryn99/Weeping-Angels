package craig.software.mc.angels.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.client.renders.Donator;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

import static org.apache.http.HttpHeaders.USER_AGENT;

public class PlayerUtil {

    public static boolean isInHand(InteractionHand hand, LivingEntity holder, Item item) {
        ItemStack heldItem = holder.getItemInHand(hand);
        return heldItem.getItem() == item;
    }

    public static boolean isInMainHand(LivingEntity holder, Item item) {
        return isInHand(InteractionHand.MAIN_HAND, holder, item);
    }

    /**
     * Checks if player has item in offhand
     */
    public static boolean isInOffHand(LivingEntity holder, Item item) {
        return isInHand(InteractionHand.OFF_HAND, holder, item);
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


    public static void sendMessageToPlayer(Player player, MutableComponent textComponent, boolean isHotBar) {
        if (player.level.isClientSide) return;
        player.displayClientMessage(textComponent, isHotBar);
    }

    public static String uuidToUsername(UUID uuid) {
        try {
            JsonObject response = getResponse(new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString()));
            return response.get("name").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JsonObject getResponse(URL url) throws IOException {
        HttpsURLConnection uc = (HttpsURLConnection) url.openConnection();
        uc.connect();
        uc = (HttpsURLConnection) url.openConnection();
        uc.addRequestProperty("User-Agent", USER_AGENT);
        InputStream inputStream = uc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        JsonObject finalData = GsonHelper.parse(br);
        uc.disconnect();
        return finalData;
    }
}

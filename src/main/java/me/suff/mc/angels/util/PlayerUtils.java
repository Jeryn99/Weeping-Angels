package me.suff.mc.angels.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;

/* Created by Craig on 18/02/2021 */
public class PlayerUtils {
    public static boolean isInHand(Hand hand, LivingEntity holder, Item item) {
        ItemStack heldItem = holder.getStackInHand(hand);
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


    public static void sendMessageToPlayer(PlayerEntity player, TranslatableText textComponent, boolean isHotBar) {
        if (player.world.isClient) return;
        player.sendMessage(textComponent, isHotBar);
    }

    public static ItemStack getItemFromActive(LivingEntity livingEntity){
        return livingEntity.getMainHandStack();
    }
}

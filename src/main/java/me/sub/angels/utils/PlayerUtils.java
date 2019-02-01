package me.sub.angels.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.Hand;

public class PlayerUtils {

    public static boolean isInHand(Hand hand, PlayerEntity holder, ItemStack item) {
        if (!holder.getStackInHand(hand).isEmpty()) {
            ItemStack heldItem = holder.getStackInHand(hand);
            return heldItem.isEqualIgnoreTags(item);
        } else {
            return false;
        }
    }

    public static boolean isInMainHand(PlayerEntity holder, Item item) {
        return isInHand(Hand.MAIN, holder, new ItemStack(item));
    }

    /**
     * Checks if player has item in offhand
     */
    public static boolean isInOffHand(PlayerEntity holder, Item item) {
        return isInHand(Hand.OFF, holder, new ItemStack(item));
    }

    /**
     * Checks if player has item in either hand
     */
    public static boolean isInEitherHand(PlayerEntity holder, Item item) {
        return isInMainHand(holder, item) || isInOffHand(holder, item);
    }

    // MAIN_HAND xor OFF_HAND
    public static boolean isInOneHand(PlayerEntity holder, Item item) {
        boolean mainHand = (isInMainHand(holder, item) && !isInOffHand(holder, item));
        boolean offHand = (isInOffHand(holder, item) && !isInMainHand(holder, item));
        return mainHand || offHand;
    }

    public static void sendMessageToPlayer(PlayerEntity player, TranslatableTextComponent textComponent, boolean isHotBar) {
        if (!player.world.isClient) return;
        player.addChatMessage(textComponent, isHotBar);
    }
	
}

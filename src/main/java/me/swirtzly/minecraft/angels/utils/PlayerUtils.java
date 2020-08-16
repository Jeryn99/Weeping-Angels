package me.swirtzly.minecraft.angels.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;

public class PlayerUtils {
	
	public static boolean isInHand(Hand hand, LivingEntity holder, Item item) {
		ItemStack heldItem = holder.getHeldItem(hand);
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
		if (player.world.isRemote) return;
		player.sendStatusMessage(textComponent, isHotBar);
	}
	
}

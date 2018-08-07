package me.sub.angels.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;

public class PlayerUtils {
	
	public static boolean isInHand(EnumHand hand, EntityLivingBase holder, ItemStack item) {
		if (!holder.getHeldItem(hand).isEmpty()) {
			ItemStack heldItem = holder.getHeldItem(hand);
			return heldItem.isItemEqual(item);
		} else {
			return false;
		}
	}
	
	public static boolean isInMainHand(EntityLivingBase holder, Item item) {
		return isInHand(EnumHand.MAIN_HAND, holder, new ItemStack(item));
	}
	
	/**
	 * Checks if player has item in offhand
	 */
	public static boolean isInOffHand(EntityLivingBase holder, Item item) {
		return isInHand(EnumHand.OFF_HAND, holder, new ItemStack(item));
	}
	
	/**
	 * Checks if player has item in either hand
	 */
	public static boolean isInEitherHand(EntityLivingBase holder, Item item) {
		return isInMainHand(holder, item) || isInOffHand(holder, item);
	}
	
	// MAIN_HAND xor OFF_HAND
	public static boolean isInOneHand(EntityLivingBase holder, Item item) {
		boolean mainHand = (isInMainHand(holder, item) && !isInOffHand(holder, item));
		boolean offHand = (isInOffHand(holder, item) && !isInMainHand(holder, item));
		return mainHand || offHand;
	}
	
	public static void sendMessageToPlayer(EntityPlayer player, TextComponentTranslation textComponent, boolean isHotBar) {
		if (!player.world.isRemote) return;
		player.sendStatusMessage(textComponent, isHotBar);
	}
	
}

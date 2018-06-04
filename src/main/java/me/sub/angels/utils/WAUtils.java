package me.sub.angels.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class WAUtils {
	
	public static ArrayList<Item> lightItems = new ArrayList<Item>();
	
	public static void setupLightItems() {
		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			
			if (block.getLightValue(block.getDefaultState()) > 7) {
				lightItems.add(Item.getItemFromBlock(block));
			}
			
			lightItems.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
		}
	}
	
	public static boolean isInHand(EnumHand hand, EntityLivingBase holder, Item item) {
		if (!holder.getHeldItem(hand).isEmpty()) {
			ItemStack heldItem = holder.getHeldItem(hand);
			return heldItem.getItem() == item;
		}
		return false;
	}
	
	public static boolean isInMainHand(EntityLivingBase holder, Item item) {
		return isInHand(EnumHand.MAIN_HAND, holder, item);
	}
	
	public static boolean isInOffHand(EntityLivingBase holder, Item item) {
		return isInHand(EnumHand.OFF_HAND, holder, item);
	}
	
	public static boolean isInEitherHand(EntityLivingBase holder, Item item) {
		return isInMainHand(holder, item) || isInOffHand(holder, item);
	}
	
	public static boolean handLightCheck(EntityLivingBase player) {
		
		for (Item item : lightItems) {
			if (isInEitherHand(player, item)) {
				return true;
			}
		}
		return false;
	}
	
}

package me.sub.angels.client;

import me.sub.angels.common.WAObjects;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabAngels extends CreativeTabs {
	
	public TabAngels() {
		super("angels");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(WAObjects.WAItems.TIMEY_WIMEY_DETECTOR);
	}
	
}

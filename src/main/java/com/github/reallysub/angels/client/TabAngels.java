package com.github.reallysub.angels.client;

import com.github.reallysub.angels.common.WAObjects;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabAngels extends CreativeTabs {

	public TabAngels() {
		super("angels");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(WAObjects.WAItems.timeyWimeyDetector);
	}

}

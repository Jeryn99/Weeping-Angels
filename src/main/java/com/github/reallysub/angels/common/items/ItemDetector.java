package com.github.reallysub.angels.common.items;

import com.github.reallysub.angels.client.models.items.RenderTimeyWimeyDetector;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDetector extends Item {
	
	public ItemDetector() {
		this.setRegistryName("timeywimeydectector");
		this.setUnlocalizedName("timeywimeydectector");
		this.setTileEntityItemStackRenderer(new RenderTimeyWimeyDetector());
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		
	}
	
}

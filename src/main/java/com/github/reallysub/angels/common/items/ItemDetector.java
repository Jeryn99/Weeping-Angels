package com.github.reallysub.angels.common.items;

import com.github.reallysub.angels.client.models.items.RenderTimeyWimeyDetector;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

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

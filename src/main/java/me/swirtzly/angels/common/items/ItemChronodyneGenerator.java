package me.swirtzly.angels.common.items;

import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemChronodyneGenerator extends Item {
	public ItemChronodyneGenerator() {
		maxStackSize = 16;
	}
	
	/**
	 * Called when the equipped item is right clicked.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if (!playerIn.capabilities.isCreativeMode) {
			itemstack.shrink(1);
		}
		
		if (!worldIn.isRemote) {
			EntityChronodyneGenerator gen = new EntityChronodyneGenerator(worldIn, playerIn);
			worldIn.spawnEntity(gen);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}
}

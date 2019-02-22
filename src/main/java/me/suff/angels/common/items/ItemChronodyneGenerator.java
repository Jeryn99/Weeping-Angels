package me.suff.angels.common.items;

import me.suff.angels.common.misc.WATabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemChronodyneGenerator extends Item {
	
	public ItemChronodyneGenerator() {
		super(new Properties().maxStackSize(16).group(WATabs.MAIN_TAB));
	}
	
	/**
	 * Called when the equipped item is right clicked.
	 */
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		if (!playerIn.isCreative()) {
			itemstack.shrink(1);
		}
		
		if (!worldIn.isRemote) {
			//EntityChronodyneGenerator gen = new EntityChronodyneGenerator(worldIn, playerIn);
			//worldIn.spawnEntity(gen);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}
}

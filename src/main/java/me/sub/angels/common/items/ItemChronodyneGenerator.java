package me.sub.angels.common.items;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemChronodyneGenerator extends Item {
	public ItemChronodyneGenerator() {
		this.maxStackSize = 16;
		this.setCreativeTab(WAObjects.angelTab);
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
			gen.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead, 0, 1.5F, 1.0F);
		}
		return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
	}
}

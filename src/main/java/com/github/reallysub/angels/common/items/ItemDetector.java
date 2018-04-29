package com.github.reallysub.angels.common.items;

import java.util.List;

import com.github.reallysub.angels.client.models.items.RenderTimeyWimeyDetector;
import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.main.config.WAConfig;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDetector extends Item {
	
	public ItemDetector() {
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setTileEntityItemStackRenderer(new RenderTimeyWimeyDetector());
		this.setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!entityIn.world.isRemote) {
			List<EntityAngel> angels = entityIn.world.getEntitiesWithinAABB(EntityAngel.class, entityIn.getEntityBoundingBox().grow(25, 25, 25));
			
			if (!angels.isEmpty() && isSelected) {
				
				if (entityIn instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityIn;
					player.sendStatusMessage(new TextComponentString("There are " + TextFormatting.YELLOW + angels.size() + TextFormatting.WHITE + " angels around you.. "), true);
				}
				
				if ((Math.cos(entityIn.ticksExisted) + 3) > 3.84F) {
					worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, WAObjects.Sounds.ding, SoundCategory.PLAYERS, 0.5F, 1.0F);
				}
				
				if (worldIn.rand.nextInt(5) == 3 && WAConfig.angels.chickenGoboom) {
					for (Object chicken : entityIn.world.getEntitiesWithinAABB(EntityChicken.class, entityIn.getEntityBoundingBox().grow(30, 30, 30))) {
						if (chicken instanceof EntityChicken && entityIn.world.rand.nextBoolean()) {
							EntityChicken chick = (EntityChicken) chicken;
							chick.getEntityWorld().createExplosion(chick, chick.getPosition().getX(), chick.getPosition().getY(), chick.getPosition().getZ(), 0.5F, false);
							chick.dropItem(Items.EGG, 1);
							chick.setDead();
						}
					}
				}
				
			}
		}
		
	}
	
}

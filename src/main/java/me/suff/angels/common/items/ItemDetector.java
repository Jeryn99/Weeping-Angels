package me.suff.angels.common.items;

import me.suff.angels.common.WAObjects;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.config.WAConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;

public class ItemDetector extends Item {
	
	public ItemDetector() {
		setCreativeTab(CreativeTabs.TOOLS);
		setMaxStackSize(1);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!entityIn.world.isRemote) {
			List<EntityWeepingAngel> angels = entityIn.world.getEntitiesWithinAABB(EntityWeepingAngel.class, entityIn.getEntityBoundingBox().grow(25, 25, 25));
			
			if (entityIn instanceof EntityPlayer) {
				if (!angels.isEmpty() && isSelected) {
					{
						if ((Math.cos(entityIn.ticksExisted) + 3) > 3.84F) {
							worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, WAObjects.Sounds.DING, SoundCategory.PLAYERS, 0.5F, 1.0F);
						}
						
						if (worldIn.rand.nextInt(5) == 3 && WAConfig.angels.chickenGoboom) {
							for (EntityChicken chick : entityIn.world.getEntitiesWithinAABB(EntityChicken.class, entityIn.getEntityBoundingBox().grow(30, 30, 30))) {
								if (entityIn.world.rand.nextBoolean()) {
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
	}
}

package me.suff.angels.common.items;

import me.suff.angels.client.models.item.ModelDetector;
import me.suff.angels.client.renders.items.RenderItemStackBase;
import me.suff.angels.common.WAObjects;
import me.suff.angels.common.entities.EntityWeepingAngel;
import me.suff.angels.common.misc.WATabs;
import me.suff.angels.config.WAConfig;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class ItemDetector extends Item {
	
	public ItemDetector() {
		super(new Properties().group(WATabs.MAIN_TAB).maxStackSize(1).setTEISR(() -> () -> new RenderItemStackBase(new ModelDetector())));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!entityIn.world.isRemote) {
			List<EntityWeepingAngel> angels = entityIn.world.getEntitiesWithinAABB(EntityWeepingAngel.class, entityIn.getBoundingBox().grow(25, 25, 25));
			
			if (entityIn instanceof EntityPlayer) {
				if (!angels.isEmpty() && isSelected) {
					{
						if ((Math.cos(entityIn.ticksExisted) + 3) > 3.84F) {
							worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, WAObjects.Sounds.DING, SoundCategory.PLAYERS, 0.5F, 1.0F);
						}
						
						if (worldIn.rand.nextInt(5) == 3 && WAConfig.angels.chickenGoboom) {
							for (EntityChicken chick : entityIn.world.getEntitiesWithinAABB(EntityChicken.class, entityIn.getBoundingBox().grow(30, 30, 30))) {
								if (entityIn.world.rand.nextBoolean()) {
									chick.getEntityWorld().createExplosion(chick, chick.getPosition().getX(), chick.getPosition().getY(), chick.getPosition().getZ(), 0.5F, false);
									chick.entityDropItem(Items.EGG, 1);
									chick.remove();
								}
							}
						}
						
					}
				}
			}
		}
	}
}

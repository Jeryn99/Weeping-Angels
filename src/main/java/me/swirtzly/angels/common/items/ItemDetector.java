package me.swirtzly.angels.common.items;

import me.swirtzly.angels.client.models.item.ModelDetector;
import me.swirtzly.angels.client.renders.items.RenderItemStackBase;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.misc.WATabs;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class ItemDetector extends Item {
	
	public ItemDetector() {
		super(new Properties().group(WATabs.MAIN_TAB).maxStackSize(1).setTEISR(() -> () -> new RenderItemStackBase(new ModelDetector())));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!entityIn.world.isRemote) {
			List<EntityWeepingAngel> angels = entityIn.world.getEntitiesWithinAABB(EntityWeepingAngel.class, entityIn.getBoundingBox().grow(25, 25, 25));
			
			if (entityIn instanceof PlayerEntity) {
				if (!angels.isEmpty() && isSelected) {
					{
						if ((Math.cos(entityIn.ticksExisted) + 3) > 3.84F) {
							worldIn.playSound(null, entityIn.posX, entityIn.posY, entityIn.posZ, WAObjects.Sounds.DING, SoundCategory.PLAYERS, 0.5F, 1.0F);
						}
						
						if (worldIn.rand.nextInt(5) == 3 && WAConfig.CONFIG.chickenGoboom.get()) {
							for (ChickenEntity chick : entityIn.world.getEntitiesWithinAABB(ChickenEntity.class, entityIn.getBoundingBox().grow(30, 30, 30))) {
								if (entityIn.world.rand.nextBoolean()) {
									chick.getEntityWorld().createExplosion(chick, chick.getPosition().getX(), chick.getPosition().getY(), chick.getPosition().getZ(), 0.5F, Explosion.Mode.NONE);
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

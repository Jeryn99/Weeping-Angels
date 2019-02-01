package me.sub.angels.common.items;

import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.List;

public class ItemDetector extends Item {

    public ItemDetector() {
        super(new Settings().stackSize(1));
//        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override public void onEntityTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if (!entityIn.world.isClient) {
            List<EntityWeepingAngel> angels = entityIn.world.getEntities(EntityWeepingAngel.class, entityIn.getBoundingBox().expand(25), angel -> true);

            if (entityIn instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) entityIn;

                boolean isInHand = PlayerUtils.isInEitherHand(player, WAObjects.Items.TIMEY_WIMEY_DETECTOR);

                if (!angels.isEmpty() && isInHand) {
                    {
                        if ((Math.cos(entityIn.age) + 3) > 3.84F) {
                            //TODO sounds
                            //                            worldIn.playSound(null, entityIn.x, entityIn.y, entityIn.z, WAObjects.Sounds.DING, SoundCategory.PLAYERS, 0.5F, 1.0F);
                        }
                        //&& WAConfig.angels.chickenGoboom TODO config

                        if (worldIn.random.nextInt(5) == 3 ) {
                            for (ChickenEntity chick : entityIn.world.getEntities(ChickenEntity.class, entityIn.getBoundingBox().expand(30), chickenEntity -> true)) {
                                if (entityIn.world.random.nextBoolean()) {
                                    chick.getEntityWorld().createExplosion(chick, chick.getPos().getX(), chick.getPos().getY(), chick.getPos().getZ(), 0.5F, false);
                                    chick.dropItem(Items.EGG, 1);
                                    chick.invalidate();
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

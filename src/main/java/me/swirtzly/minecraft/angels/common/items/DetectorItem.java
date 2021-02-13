package me.swirtzly.minecraft.angels.common.items;

import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.misc.WATabs;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.utils.PlayerUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class DetectorItem extends Item {

    public DetectorItem() {
        super(new Properties().group(WATabs.MAIN_TAB).maxStackSize(1));
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList< ItemStack > items) {
        super.fillItemGroup(group, items);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

        if (!entityIn.world.isRemote) {

            List< WeepingAngelEntity > angels = entityIn.world.getEntitiesWithinAABB(WeepingAngelEntity.class, entityIn.getBoundingBox().grow(15, 15, 15));

            if (entityIn instanceof PlayerEntity) {

                if (PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    if (entityIn.ticksExisted % 160 == 0) {
                        worldIn.playSound(null, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), WAObjects.Sounds.PROJECTOR.get(), SoundCategory.PLAYERS, 0.2F, 1.0F);
                    }
                }

                if (!angels.isEmpty() && PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    {
                        if (entityIn.ticksExisted % 20 == 0) {
                            worldIn.playSound(null, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), WAObjects.Sounds.DING.get(), SoundCategory.PLAYERS, 0.2F, 1.0F);
                        }

                        if (worldIn.rand.nextInt(5) == 3 && WAConfig.CONFIG.chickenGoboom.get()) {
                            for (ChickenEntity chick : entityIn.world.getEntitiesWithinAABB(ChickenEntity.class, entityIn.getBoundingBox().grow(30, 30, 30))) {
                                if (entityIn.world.rand.nextBoolean()) {
                                    chick.getEntityWorld().createExplosion(chick, chick.getPosX(), chick.getPosY(), chick.getPosZ(), 0.5F, Explosion.Mode.NONE);
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

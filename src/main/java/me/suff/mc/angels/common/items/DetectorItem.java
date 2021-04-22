package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.utils.PlayerUtils;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.misc.WATabs;
import me.suff.mc.angels.config.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

import net.minecraft.item.Item.Properties;

public class DetectorItem extends Item {

    public DetectorItem() {
        super(new Properties().tab(WATabs.MAIN_TAB).stacksTo(1));
    }

    public static void setTime(ItemStack itemStack, int time) {
        CompoundNBT tag = itemStack.getOrCreateTag();
        if(time > 17){
            time = 0;
        }
        tag.putInt("time", time);
    }

    public static int getTime(ItemStack itemStack) {
        CompoundNBT tag = itemStack.getOrCreateTag();
        if (tag.contains("time")) {
            return tag.getInt("time");
        }
        return 0;
    }

    @Override
    public void fillItemCategory(ItemGroup group, NonNullList< ItemStack > items) {
        super.fillItemCategory(group, items);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!entityIn.level.isClientSide) {

            setTime(stack, getTime(stack) + 1);

            List< WeepingAngelEntity > angels = entityIn.level.getEntitiesOfClass(WeepingAngelEntity.class, entityIn.getBoundingBox().inflate(15, 15, 15));

            if (entityIn instanceof PlayerEntity) {

                if (PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    if (entityIn.tickCount % 160 == 0) {
                        worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), WAObjects.Sounds.PROJECTOR.get(), SoundCategory.PLAYERS, 0.2F, 1.0F);
                    }
                }

                if (!angels.isEmpty() && PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    {
                        if (entityIn.tickCount % 20 == 0) {
                            worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), WAObjects.Sounds.DING.get(), SoundCategory.PLAYERS, 0.2F, 1.0F);
                        }

                        if (worldIn.random.nextInt(5) == 3 && WAConfig.CONFIG.chickenGoboom.get()) {
                            for (ChickenEntity chick : entityIn.level.getEntitiesOfClass(ChickenEntity.class, entityIn.getBoundingBox().inflate(30, 30, 30))) {
                                if (entityIn.level.random.nextInt(100) < 5) {
                                    chick.level.explode(chick, chick.getX(), chick.getY(), chick.getZ(), 0.5F, Explosion.Mode.NONE);
                                    chick.spawnAtLocation(Items.EGG, 1);
                                    chick.remove();
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}

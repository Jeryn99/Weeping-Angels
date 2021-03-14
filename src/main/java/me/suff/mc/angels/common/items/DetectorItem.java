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

public class DetectorItem extends Item {

    public DetectorItem() {
        super(new Properties().group(WATabs.MAIN_TAB).maxStackSize(1));
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
    public void fillItemGroup(ItemGroup group, NonNullList< ItemStack > items) {
        super.fillItemGroup(group, items);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!entityIn.world.isRemote) {

            setTime(stack, getTime(stack) + 1);

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
                                if (entityIn.world.rand.nextInt(100) < 5) {
                                    chick.world.createExplosion(chick, chick.getPosX(), chick.getPosY(), chick.getPosZ(), 0.5F, Explosion.Mode.NONE);
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



    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}

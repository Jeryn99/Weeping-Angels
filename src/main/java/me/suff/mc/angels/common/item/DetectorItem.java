package me.suff.mc.angels.common.item;

import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import me.suff.mc.angels.common.objects.WASounds;
import me.suff.mc.angels.util.PlayerUtils;
import me.suff.mc.angels.util.WAConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

/* Created by Craig on 18/02/2021 */
public class DetectorItem extends Item {
    public DetectorItem(Settings settings) {
        super(settings);
    }

    public static void setTime(ItemStack itemStack, int time) {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (time > 17) {
            time = 0;
        }
        tag.putInt("time", time);
    }

    public static int getTime(ItemStack itemStack) {
        CompoundTag tag = itemStack.getOrCreateTag();
        if (tag.contains("time")) {
            return tag.getInt("time");
        }
        return 0;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!entityIn.world.isClient()) {

       /*     setTime(stack, getTime(stack) + 1);
            if(entityIn instanceof PlayerEntity){
                PlayerEntity playerEntity = (PlayerEntity) entityIn;
                playerEntity.getItemCooldownManager().getCooldownProgress(stack.getItem(), 1);
            }*/

            List< WeepingAngelEntity > angels = entityIn.world.getEntitiesByClass(WeepingAngelEntity.class, entityIn.getBoundingBox().expand(15, 15, 15), LivingEntity::isAlive);

            if (entityIn instanceof PlayerEntity) {

                if (PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    if (entityIn.age % 160 == 0) {
                        worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), WASounds.PROJECTOR, SoundCategory.PLAYERS, 0.2F, 1.0F);
                    }
                }

                if (!angels.isEmpty() && PlayerUtils.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    {
                        if (entityIn.age % 20 == 0) {
                            worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), WASounds.DING, SoundCategory.PLAYERS, 0.2F, 1.0F);
                        }

                        if (worldIn.random.nextInt(5) == 3 && WAConfig.AngelBehaviour.chickenBoom.getValue()) {
                            for (ChickenEntity chick : entityIn.world.getEntitiesByClass(ChickenEntity.class, entityIn.getBoundingBox().expand(30, 30, 30), LivingEntity::isAlive)) {
                                if (entityIn.world.random.nextInt(100) < 5) {
                                    chick.world.createExplosion(chick, chick.getX(), chick.getY(), chick.getZ(), 0.5F, Explosion.DestructionType.NONE);
                                    chick.dropItem(Items.EGG, 1);
                                    chick.remove(Entity.RemovalReason.DISCARDED);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.common.misc.WATabs;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetectorItem extends Item {

    public DetectorItem() {
        super(new Properties().tab(WATabs.MAIN_TAB).stacksTo(1));
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
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        super.fillItemCategory(group, items);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!entityIn.level.isClientSide) {
            setTime(stack, getTime(stack) + 1);
            List<WeepingAngel> angels = entityIn.level.getEntitiesOfClass(WeepingAngel.class, entityIn.getBoundingBox().inflate(15, 15, 15));
            if (entityIn instanceof Player) {
                if (!angels.isEmpty() && PlayerUtil.isInEitherHand((LivingEntity) entityIn, stack.getItem())) {
                    {
                        if (entityIn.tickCount % 20 == 0) {
                            worldIn.playSound(null, entityIn.getX(), entityIn.getY(), entityIn.getZ(), WAObjects.Sounds.DING.get(), SoundSource.PLAYERS, 0.2F, 1.0F);
                        }

                        if (worldIn.random.nextInt(5) == 3 && WAConfig.CONFIG.chickenGoboom.get()) {
                            for (Chicken chick : entityIn.level.getEntitiesOfClass(Chicken.class, entityIn.getBoundingBox().inflate(30, 30, 30))) {
                                if (entityIn.level.random.nextInt(100) < 5) {
                                    chick.level.explode(chick, chick.getX(), chick.getY(), chick.getZ(), 0.5F, Explosion.BlockInteraction.NONE);
                                    chick.spawnAtLocation(Items.EGG, 1);
                                    chick.remove(Entity.RemovalReason.KILLED);
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

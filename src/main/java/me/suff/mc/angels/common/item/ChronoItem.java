package me.suff.mc.angels.common.item;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entity.ChronoEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ChronoItem extends Item {
    public ChronoItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult< ItemStack > use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            ChronoEntity chronoEntity = new ChronoEntity(WeepingAngels.CHRONO, user, world);
            chronoEntity.setItem(itemStack);
            chronoEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(chronoEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
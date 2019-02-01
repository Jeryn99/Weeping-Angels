package me.sub.angels.common.items;

import me.sub.angels.common.entities.EntityChronodyneGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemChronodyneGenerator extends Item {
    public ItemChronodyneGenerator() {
        super(new Settings().stackSize(16));
    }

    @Override public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1)
    {
        ItemStack itemstack = playerEntity_1.getStackInHand(hand_1);

        if (!playerEntity_1.abilities.creativeMode) {
            itemstack.subtractAmount(1);
        }

        if (!world_1.isClient) {
            EntityChronodyneGenerator gen = new EntityChronodyneGenerator(playerEntity_1, world_1);
            world_1.spawnEntity(gen);
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemstack);

    }

}

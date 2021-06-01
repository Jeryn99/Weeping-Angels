package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.entities.ChronodyneGeneratorEntity;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.misc.WATabs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChronodyneGeneratorItem extends Item {

    public ChronodyneGeneratorItem() {
        super(new Properties().maxStackSize(16).group(WATabs.MAIN_TAB));
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public ActionResult< ItemStack > onItemRightClick(World world, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        if (!world.isRemote) {
            ChronodyneGeneratorEntity laser = new ChronodyneGeneratorEntity(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), playerIn, world);
            laser.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.5F, 0F);
            world.addEntity(laser);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}

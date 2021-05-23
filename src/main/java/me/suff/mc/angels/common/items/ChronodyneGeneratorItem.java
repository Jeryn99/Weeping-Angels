package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.ChronodyneGeneratorEntity;
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
        super(new Properties().stacksTo(16).tab(WATabs.MAIN_TAB));
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        if (!world.isClientSide) {
            ChronodyneGeneratorEntity laser = new ChronodyneGeneratorEntity(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), playerIn, world);
            laser.setItem(laser.getItem());
            laser.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(laser);
        }
        return new ActionResult<>(ActionResultType.SUCCESS, itemstack);
    }
}

package mc.craig.software.angels.common.items;

import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.entities.ChronodyneGeneratorProjectile;
import mc.craig.software.angels.common.misc.WATabs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChronodyneGeneratorItem extends Item {

    public ChronodyneGeneratorItem() {
        super(new Properties().stacksTo(16).tab(WATabs.MAIN_TAB));
    }

    /**
     * Called when the equipped item is right clicked.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        if (!world.isClientSide) {
            ChronodyneGeneratorProjectile laser = new ChronodyneGeneratorProjectile(WAObjects.EntityEntries.CHRONODYNE_GENERATOR.get(), playerIn, world);
            laser.setItem(laser.getItem());
            laser.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(laser);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }
}

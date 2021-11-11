package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.blockentities.IPlinth;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;

/* Created by Craig on 13/02/2021 */
public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    /**
     * Called when this item is used when targetting a Block
     */
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();

        if (world.getBlockEntity(blockpos) instanceof IPlinth plinth && context.getHand() == InteractionHand.MAIN_HAND) {
            if (player.isShiftKeyDown()) {
                plinth.changeModel();
                plinth.sendUpdatesToClient();
                return InteractionResult.PASS;
            }

            plinth.changePose();
            plinth.sendUpdatesToClient();
            return InteractionResult.PASS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslatableComponent("tooltip.weeping_angels.chisel"));
        tooltip.add(new TranslatableComponent("tooltip.weeping_angels.chisel_right_click"));
        tooltip.add(new TranslatableComponent("tooltip.weeping_angels.chisel_sneak"));

    }
}

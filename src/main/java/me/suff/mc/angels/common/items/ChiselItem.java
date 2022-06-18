package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.blockentities.IPlinth;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static me.suff.mc.angels.common.variants.AngelTypes.NORMAL;

/* Created by Craig on 13/02/2021 */
public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    /**
     * Called when this item is used when targetting a Block
     */
    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();

        if (world.getBlockEntity(blockpos) instanceof IPlinth plinth) {

            if (player.isShiftKeyDown()) {
                if (context.getHand() == InteractionHand.MAIN_HAND) {
                    player.swing(context.getHand());
                    plinth.changeModel();
                    plinth.sendUpdatesToClient();
                    plinth.setAbstractVariant(NORMAL.get());
                    PlayerUtil.sendMessageToPlayer(player, Component.translatable("Changed model to " + plinth.getCurrentType()), true);
                } else {
                    player.swing(context.getHand());
                    plinth.setAbstractVariant(plinth.getCurrentType().getWeightedHandler().getRandom());
                    plinth.sendUpdatesToClient();
                    PlayerUtil.sendMessageToPlayer(player, Component.translatable("Changed variant to " + plinth.getVariant().getRegistryName()), true);
                }
                return InteractionResult.PASS;
            }

            player.swing(context.getHand());
            plinth.changePose();
            PlayerUtil.sendMessageToPlayer(player, Component.translatable("Changed pose to " + plinth.getCurrentPose()), true);
            plinth.sendUpdatesToClient();
            return InteractionResult.PASS;
        }


        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable("tooltip.weeping_angels.chisel"));
        tooltip.add(Component.translatable("tooltip.weeping_angels.chisel_right_click"));
        tooltip.add(Component.translatable("tooltip.weeping_angels.chisel_sneak"));
        tooltip.add(Component.translatable("tooltip.weeping_angels.punch_variant"));

    }
}

package mc.craig.software.angels.common.items;

import mc.craig.software.angels.common.blockentities.IPlinth;
import mc.craig.software.angels.common.variants.AngelTypes;
import mc.craig.software.angels.utils.PlayerUtil;
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

        if (world.getBlockEntity(blockpos) instanceof IPlinth plinth) {

            if (player.isShiftKeyDown()) {
                if (context.getHand() == InteractionHand.MAIN_HAND) {
                    player.swing(context.getHand());
                    plinth.changeModel();
                    plinth.sendUpdatesToClient();
                    plinth.setAbstractVariant(AngelTypes.NORMAL.get());
                    PlayerUtil.sendMessageToPlayer(player, new TranslatableComponent("Changed model to " + plinth.getCurrentType()), true);
                } else {
                    player.swing(context.getHand());
                    plinth.setAbstractVariant(plinth.getCurrentType().getWeightedHandler().getRandom(null));
                    plinth.sendUpdatesToClient();
                    PlayerUtil.sendMessageToPlayer(player, new TranslatableComponent("Changed variant to " + plinth.getVariant().getRegistryName()), true);
                }
                return InteractionResult.PASS;
            }

            player.swing(context.getHand());
            plinth.changePose();
            PlayerUtil.sendMessageToPlayer(player, new TranslatableComponent("Changed pose to " + plinth.getCurrentPose()), true);
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
        tooltip.add(new TranslatableComponent("tooltip.weeping_angels.punch_variant"));

    }
}

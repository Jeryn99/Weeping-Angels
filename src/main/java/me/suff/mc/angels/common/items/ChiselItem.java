package me.suff.mc.angels.common.items;

import me.suff.mc.angels.common.tileentities.IPlinth;
import me.suff.mc.angels.utils.PlayerUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static me.suff.mc.angels.common.variants.AngelVariants.NORMAL;

/* Created by Craig on 13/02/2021 */
public class ChiselItem extends Item {
    public ChiselItem(Properties properties) {
        super(properties);
    }

    /**
     * Called when this item is used when targetting a Block
     */
    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        PlayerEntity player = context.getPlayer();

        if (world.getBlockEntity(blockpos) instanceof IPlinth) {
            IPlinth plinth = (IPlinth) world.getBlockEntity(blockpos);
            if (player.isShiftKeyDown()) {
                if (context.getHand() == Hand.MAIN_HAND) {
                    player.swing(context.getHand());
                    plinth.changeModel();
                    plinth.sendUpdatesToClient();
                    plinth.setAbstractVariant(NORMAL.get());
                    PlayerUtil.sendMessageToPlayer(player, new TranslationTextComponent("Changed model to " + plinth.getCurrentType()), true);
                }
                return ActionResultType.PASS;
            }

            player.swing(context.getHand());
            plinth.changePose();
            PlayerUtil.sendMessageToPlayer(player, new TranslationTextComponent("Changed pose to " + plinth.getCurrentPose()), true);
            plinth.sendUpdatesToClient();
            return ActionResultType.PASS;
        }


        return ActionResultType.FAIL;
    }


    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("tooltip.weeping_angels.chisel"));
        p_77624_3_.add(new TranslationTextComponent("tooltip.weeping_angels.chisel_right_click"));
        p_77624_3_.add(new TranslationTextComponent("tooltip.weeping_angels.chisel_sneak"));
        p_77624_3_.add(new TranslationTextComponent("tooltip.weeping_angels.punch_variant"));

    }
}

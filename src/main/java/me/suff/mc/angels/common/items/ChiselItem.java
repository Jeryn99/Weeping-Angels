package me.suff.mc.angels.common.items;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.tileentities.IPlinth;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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
        BlockState blockstate = world.getBlockState(blockpos);
        Player player = context.getPlayer();


        if (world.getBlockEntity(blockpos) instanceof IPlinth) {
            IPlinth plinth = (IPlinth) world.getBlockEntity(blockpos);
            if (player.isShiftKeyDown()) {
                plinth.changeModel();
                plinth.sendUpdatesToClient();
                return InteractionResult.PASS;
            }

            plinth.changePose();
            plinth.sendUpdatesToClient();
        }

        //Handle Statue
        if (blockstate.getBlock() == WAObjects.Blocks.STATUE.get()) {
            StatueTile statueTile = (StatueTile) world.getBlockEntity(blockpos);
            if (player.isShiftKeyDown()) {
                statueTile.setAngelType(AngelUtil.randomType());
            } else {
                statueTile.setPose(WeepingAngelPose.getRandomPose(AngelUtil.RAND));
            }
            statueTile.setChanged();
            return InteractionResult.PASS;
        }

        //Handle Plinth
        if (blockstate.getBlock() == WAObjects.Blocks.PLINTH.get()) {
            PlinthTile statueTile = (PlinthTile) world.getBlockEntity(blockpos);
            if (player.isShiftKeyDown()) {
                statueTile.setAngelType(AngelUtil.randomType());
            } else {
                statueTile.setPose(WeepingAngelPose.getRandomPose(AngelUtil.RAND));
            }
            statueTile.sendUpdates();
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

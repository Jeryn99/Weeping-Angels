package me.suff.mc.angels.common.items;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.tileentities.IPlinth;
import me.suff.mc.angels.common.tileentities.PlinthTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.AngelUtils;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getPos();
        BlockState blockstate = world.getBlockState(blockpos);
        PlayerEntity player = context.getPlayer();


        if (world.getTileEntity(blockpos) instanceof IPlinth) {
            IPlinth plinth = (IPlinth) world.getTileEntity(blockpos);
            if (player.isSneaking()) {
                plinth.changeModel();
                plinth.sendUpdatesToClient();
                return ActionResultType.PASS;
            }

            plinth.changePose();
            plinth.sendUpdatesToClient();
        }

        //Handle Statue
        if (blockstate.getBlock() == WAObjects.Blocks.STATUE.get()) {
            StatueTile statueTile = (StatueTile) world.getTileEntity(blockpos);
            if (player.isSneaking()) {
                statueTile.setAngelType(AngelUtils.randomType());
            } else {
                statueTile.setPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
            }
            statueTile.markDirty();
            return ActionResultType.PASS;
        }

        //Handle Plinth
        if (blockstate.getBlock() == WAObjects.Blocks.PLINTH.get()) {
            PlinthTile statueTile = (PlinthTile) world.getTileEntity(blockpos);
            if (player.isSneaking()) {
                statueTile.setAngelType(AngelUtils.randomType());
            } else {
                statueTile.setPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
            }
            statueTile.sendUpdates();
            return ActionResultType.PASS;
        }

        return ActionResultType.FAIL;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List< ITextComponent > tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(new TranslationTextComponent("tooltip.weeping_angels.chisel"));
        tooltip.add(new TranslationTextComponent("tooltip.weeping_angels.chisel_right_click"));
        tooltip.add(new TranslationTextComponent("tooltip.weeping_angels.chisel_sneak"));

    }
}

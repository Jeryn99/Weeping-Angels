package me.suff.mc.angels.common.item;

import me.suff.mc.angels.common.blockentity.IPlaceableStatue;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.util.AngelUtils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/* Created by Craig on 18/02/2021 */
public class ChiselItem extends Item {
    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockpos = context.getBlockPos();

        if (world.getBlockEntity(blockpos) instanceof IPlaceableStatue) {
            IPlaceableStatue iPlaceableStatue = (IPlaceableStatue) world.getBlockEntity(blockpos);
            iPlaceableStatue.setAngelPose(WeepingAngelPose.getRandomPose(AngelUtils.RAND));
            Objects.requireNonNull(world.getBlockEntity(blockpos)).markDirty();
            return ActionResult.SUCCESS;
        }

        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List< Text > tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel"));
        tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel_right_click"));
        //  tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel_sneak"));
    }
}

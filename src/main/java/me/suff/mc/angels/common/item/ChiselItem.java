package me.suff.mc.angels.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/* Created by Craig on 18/02/2021 */
public class ChiselItem extends Item {
    public ChiselItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List< Text > tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel"));
        tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel_right_click"));
        tooltip.add(new TranslatableText("tooltip.weeping_angels.chisel_sneak"));
    }
}

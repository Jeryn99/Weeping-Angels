package me.suff.mc.angels.enums;

import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

/* Created by Craig on 18/02/2021 */
public enum WeepingAngelVariants {
    MOSSY(new ItemStack(Blocks.VINE)), NORMAL(new ItemStack(Blocks.COBBLESTONE)), RUSTED(new ItemStack(Blocks.GRANITE)), RUSTED_NO_ARM(new ItemStack(Blocks.GRANITE)), RUSTED_NO_WING(new ItemStack(Blocks.GRANITE)), RUSTED_HEADLESS(true, new ItemStack(Blocks.GRANITE));

    private final boolean headless;
    private final ItemStack dropStack;

    WeepingAngelVariants(ItemStack stack) {
        this(false, stack);
    }

    WeepingAngelVariants(boolean b, ItemStack stack) {
        headless = b;
        this.dropStack = stack;
    }

    public static WeepingAngelVariants getVariant(String name) {
        for (WeepingAngelVariants variants : WeepingAngelVariants.values()) {
            if (variants.name().equalsIgnoreCase(name)) {
                return variants;
            }
        }
        return MOSSY;
    }

    public ItemStack getDropStack() {
        return dropStack;
    }

    public boolean isHeadless() {
        return headless;
    }
}

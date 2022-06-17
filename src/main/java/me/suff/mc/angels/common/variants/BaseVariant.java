package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static me.suff.mc.angels.common.variants.AngelTypes.BANNED_FROM_NETHER;

public class BaseVariant extends AngelVariant {

    private final int rarity;
    private final Supplier<ItemStack> dropStack;

    public BaseVariant(Supplier<ItemStack> itemStackSupplier, int rarity, Predicate<WeepingAngel> variantTest) {
        super(variantTest);
        this.dropStack = itemStackSupplier;
        this.rarity = rarity;
    }

    public BaseVariant(Supplier<ItemStack> itemStackSupplier, int rarity) {
        super(BANNED_FROM_NETHER);
        this.dropStack = itemStackSupplier;
        this.rarity = rarity;
    }

    @Override
    public ItemStack stackDrop() {
        return dropStack.get();
    }

    @Override
    public boolean shouldDrop(DamageSource damageSource, WeepingAngel quantumLockEntity) {
        return true;
    }

    @Override
    public double getRarity() {
        return rarity;
    }
}

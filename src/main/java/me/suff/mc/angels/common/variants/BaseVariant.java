package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.function.Predicate;
import java.util.function.Supplier;

import static me.suff.mc.angels.common.variants.AngelVariants.BANNED_FROM_NETHER;

public class BaseVariant extends AbstractVariant {

    private final int rarity;
    private Supplier<ItemStack> dropStack;

    public BaseVariant(Supplier<ItemStack> itemStackSupplier, int rarity, Predicate<WeepingAngelEntity> variantTest) {
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
    public boolean shouldDrop(DamageSource damageSource, WeepingAngelEntity quantumLockEntity) {
        return true;
    }

    @Override
    public double getRarity() {
        return rarity;
    }
}

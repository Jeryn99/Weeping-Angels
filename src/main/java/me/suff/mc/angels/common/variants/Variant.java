package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class Variant extends AbstractVariant {

    private Supplier<ItemStack> dropStack;

    public Variant(Supplier<ItemStack> itemStackSupplier) {
        this.dropStack = itemStackSupplier;
    }

    @Override
    public ItemStack stackDrop() {
        return dropStack.get();
    }

    @Override
    public boolean shouldDrop(DamageSource damageSource, WeepingAngelEntity quantumLockEntity) {
        return true;
    }
}

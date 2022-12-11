package mc.craig.software.angels.common.variants;

import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import java.util.function.Predicate;
import java.util.function.Supplier;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

import static mc.craig.software.angels.common.variants.AngelVariants.BANNED_FROM_NETHER;

public class BaseVariant extends AbstractVariant {

    private final int rarity;
    private final Supplier<ItemStack> dropStack;

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

package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.function.Predicate;

public abstract class AbstractVariant extends ForgeRegistryEntry<AbstractVariant> {

    private Predicate<WeepingAngelEntity> variantTest;
    private boolean isHeadless = false;

    public AbstractVariant(Predicate<WeepingAngelEntity> weepingAngelEntityPredicate) {
        this.variantTest = weepingAngelEntityPredicate;
    }

    public Predicate<WeepingAngelEntity> getVariantTest() {
        return variantTest;
    }

    public ItemStack stackDrop() {
        return new ItemStack(Blocks.AIR);
    }

    public boolean shouldDrop(DamageSource damageSource, WeepingAngelEntity quantumLockEntity) {
        return false;
    }

    public boolean isHeadless() {
        return isHeadless;
    }

    public AbstractVariant setHeadless(boolean isHeadless) {
        this.isHeadless = isHeadless;
        return this;
    }

    public abstract double getRarity();

    public boolean canVariantBeUsed(WeepingAngelEntity weepingAngelEntity) {
        if (variantTest == null) {
            return true;
        }
        return variantTest.test(weepingAngelEntity);
    }
}

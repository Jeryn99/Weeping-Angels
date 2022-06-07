package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Predicate;

public abstract class AbstractVariant {

    private final Predicate<WeepingAngel> variantTest;
    private boolean isHeadless = false;

    public AbstractVariant(Predicate<WeepingAngel> weepingAngelEntityPredicate) {
        this.variantTest = weepingAngelEntityPredicate;
    }

    public void tick(WeepingAngel weepingAngel) {
        if (weepingAngel.getVariant() == AngelTypes.RUSTED_NO_ARM.get()) {
            weepingAngel.setLeftHanded(true);
            if (!weepingAngel.getOffhandItem().isEmpty()) {
                weepingAngel.spawnAtLocation(weepingAngel.getOffhandItem());
                weepingAngel.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
            }
        } else {
            weepingAngel.setLeftHanded(false);
        }
    }

    public Predicate<WeepingAngel> getVariantTest() {
        return variantTest;
    }

    public ItemStack stackDrop() {
        return new ItemStack(Blocks.AIR);
    }

    public boolean shouldDrop(DamageSource damageSource, WeepingAngel quantumLockEntity) {
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

    public boolean canVariantBeUsed(WeepingAngel weepingAngel) {
        if (variantTest == null) {
            return true;
        }
        return variantTest.test(weepingAngel);
    }
}

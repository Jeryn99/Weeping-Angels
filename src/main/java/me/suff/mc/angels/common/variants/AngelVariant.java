package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Predicate;

public abstract class AngelVariant {

    private final Predicate<WeepingAngel> variantAllowedOnSpawn;
    private boolean isHeadless = false;

    public AngelVariant(Predicate<WeepingAngel> weepingAngelEntityPredicate) {
        this.variantAllowedOnSpawn = weepingAngelEntityPredicate;
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

    public Predicate<WeepingAngel> getVariantAllowedOnSpawn() {
        return variantAllowedOnSpawn;
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

    public AngelVariant setHeadless(boolean isHeadless) {
        this.isHeadless = isHeadless;
        return this;
    }

    public abstract double getRarity();

    public boolean canVariantBeUsed(WeepingAngel weepingAngel) {
        if (variantAllowedOnSpawn == null) {
            return true;
        }
        return variantAllowedOnSpawn.test(weepingAngel);
    }

    public ResourceLocation getRegistryName(){
        return AngelTypes.VARIANTS_REGISTRY.get().getKey(this);
    }
}

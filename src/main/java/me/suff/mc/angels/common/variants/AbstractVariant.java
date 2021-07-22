package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class AbstractVariant extends ForgeRegistryEntry<AbstractVariant> {

    private boolean isHeadless = false;

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
}

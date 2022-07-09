package craig.software.mc.angels.common.variants;

import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import java.util.function.Predicate;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class AbstractVariant extends ForgeRegistryEntry<AbstractVariant> {

    private final Predicate<WeepingAngelEntity> variantTest;
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
        if (variantTest == null || weepingAngelEntity == null) {
            return false;
        }
        return variantTest.test(weepingAngelEntity);
    }
}

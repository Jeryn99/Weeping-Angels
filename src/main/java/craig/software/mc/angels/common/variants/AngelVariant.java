package craig.software.mc.angels.common.variants;

import craig.software.mc.angels.common.entities.WeepingAngel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public abstract class AngelVariant {

    private boolean isHeadless = false;

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

    public ResourceLocation getRegistryName(){
        return AngelTypes.VARIANTS_REGISTRY.get().getKey(this);
    }
}

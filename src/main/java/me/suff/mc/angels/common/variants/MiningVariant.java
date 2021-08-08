package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class MiningVariant extends BaseVariant {

    public MiningVariant(Supplier<ItemStack> itemStackSupplier, int rarity, Predicate<WeepingAngel> variantTest) {
        super(itemStackSupplier, rarity, variantTest);
    }

    public MiningVariant(Supplier<ItemStack> itemStackSupplier, int rarity) {
        super(itemStackSupplier, rarity);
    }

    @Override
    public boolean shouldDrop(DamageSource damageSource, WeepingAngel quantumLockEntity) {
        if (damageSource.getEntity() instanceof Player playerEntity) {
            if (playerEntity.getMainHandItem().getItem() instanceof PickaxeItem pickaxeItem) {
                if (quantumLockEntity.getVariant().stackDrop().getItem() instanceof BlockItem) {
                    Block block = ((BlockItem) quantumLockEntity.getVariant().stackDrop().getItem()).getBlock();
                    return pickaxeItem.isCorrectToolForDrops(block.defaultBlockState());
                }
            }
        }
        return false;
    }
}

package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.DamageSource;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class MiningVariant extends BaseVariant {

    public MiningVariant(Supplier<ItemStack> itemStackSupplier, int rarity, Predicate<WeepingAngelEntity> variantTest) {
        super(itemStackSupplier, rarity, variantTest);
    }

    public MiningVariant(Supplier<ItemStack> itemStackSupplier, int rarity) {
        super(itemStackSupplier, rarity);
    }

    @Override
    public boolean shouldDrop(DamageSource damageSource, WeepingAngelEntity quantumLockEntity) {
        if(damageSource.getEntity() instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) damageSource.getEntity();
            if(playerEntity.getMainHandItem().getItem() instanceof PickaxeItem){
                PickaxeItem pickaxeItem = (PickaxeItem) playerEntity.getMainHandItem().getItem();
                if(quantumLockEntity.getVariant().stackDrop().getItem() instanceof BlockItem) {
                    Block block = ((BlockItem) quantumLockEntity.getVariant().stackDrop().getItem()).getBlock();
                    return pickaxeItem.isCorrectToolForDrops(block.defaultBlockState());
                }
            }
        }
        return false;
    }
}

package me.suff.mc.angels.common.variants;

import me.suff.mc.angels.common.entities.QuantumLockEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.DamageSource;

import java.util.function.Supplier;

public class MiningVariant extends Variant {
    public MiningVariant(Supplier<ItemStack> itemStackSupplier) {
        super(itemStackSupplier);
    }

    @Override
    public boolean shouldDrop(DamageSource damageSource, WeepingAngelEntity quantumLockEntity) {
        if(damageSource.getEntity() instanceof PlayerEntity){
            PlayerEntity playerEntity = (PlayerEntity) damageSource.getEntity();
            if(playerEntity.getMainHandItem().getItem() instanceof PickaxeItem){
                PickaxeItem pickaxeItem = (PickaxeItem) playerEntity.getMainHandItem().getItem();
                if(quantumLockEntity.getVariant().stackDrop().getItem() instanceof BlockItem) {
                    Block block = ((BlockItem) quantumLockEntity.getVariant().stackDrop().getItem()).getBlock();
                    System.out.println("Can I drop this? " + pickaxeItem.isCorrectToolForDrops(block.defaultBlockState()));
                    return pickaxeItem.isCorrectToolForDrops(block.defaultBlockState());
                }
            }
        }
        return false;
    }
}

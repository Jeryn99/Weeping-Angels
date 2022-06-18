package craig.software.mc.angels.common.variants;

import craig.software.mc.angels.common.entities.WeepingAngel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class OreVariant extends BaseVariant {

    public OreVariant(Supplier<ItemStack> itemStackSupplier, int rarity) {
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

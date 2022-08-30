package mc.craig.software.angels.common.items;

import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class DetectorItem extends Item {

    public DetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (!pEntity.level.isClientSide) {
            List<Entity> angels = WAHelper.getAnomaliesAroundEntity(pEntity, 64);
            if (pEntity instanceof Player player) {
                if (!angels.isEmpty() && pIsSelected && pEntity.tickCount % 20 == 0) {
                    pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), WASounds.DING.get(), SoundSource.PLAYERS, 0.2F, 1.0F);
                }
            }
        }
    }
}

package mc.craig.software.angels.util;

import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Predicate;

public class HurtHelper {
    public static boolean validatePickaxe(Player player, Predicate<ItemStack> predicate) {
        ItemStack heldItem = player.getItemBySlot(EquipmentSlot.MAINHAND);
        return predicate.test(heldItem) && heldItem.getItem().isCorrectToolForDrops(Blocks.STONE.defaultBlockState());
    }

    public static boolean handleAngelHurt(WeepingAngel weepingAngel, DamageSource pSource, float pAmount) {

        if(pSource == DamageSource.OUT_OF_WORLD){
            return true; // Required for /kill command else...yeah
        }

        HurtType hurtType = WAConfiguration.CONFIG.hurtType.get();
        switch (hurtType) {
            case NONE -> {
                return false;
            }
            case PICKAXE -> {
                return hasPickAxe(weepingAngel, pSource, itemStack -> true);
            }
            case GENERATOR -> {
                return pSource == WADamageSources.GENERATOR;
            }
            case PICKAXE_AND_GENERATOR -> {
                return pSource == WADamageSources.GENERATOR || hasPickAxe(weepingAngel, pSource, itemStack -> true);
            }
            case ANYTHING -> {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPickAxe(WeepingAngel weepingAngel, DamageSource pSource, Predicate<ItemStack> predicate) {
        if (pSource.getEntity() instanceof Player player) {
            boolean hasPickAxe = HurtHelper.validatePickaxe(player, predicate);
            if (!hasPickAxe) {
                player.hurt(WADamageSources.PUNCH_STONE, weepingAngel.level.random.nextInt(5));
                return false;
            }
            return true;
        }
        return false;
    }

    public enum HurtType {
        PICKAXE, PICKAXE_AND_GENERATOR, NONE, GENERATOR, ANYTHING
    }
}

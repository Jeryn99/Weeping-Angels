package dev.jeryn.angels.util;

import dev.jeryn.angels.WAConfiguration;
import dev.jeryn.angels.common.WASounds;
import dev.jeryn.angels.common.entity.angel.WeepingAngel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class HurtHelper {
    public static boolean validatePickaxe(Player player, WeepingAngel weepingAngel, Predicate<ItemStack> predicate) {
        ItemStack heldItem = player.getItemBySlot(EquipmentSlot.MAINHAND);
        if (weepingAngel.getVariant().getDrops().getItem() instanceof BlockItem blockItem) {
            return predicate.test(heldItem) && heldItem.getItem().isCorrectToolForDrops(blockItem.getBlock().defaultBlockState());
        }
        return heldItem.is(WATags.ATTACK_OVERRIDES);
    }

    public static boolean handleAngelHurt(WeepingAngel weepingAngel, DamageSource pSource, float pAmount) {

        if(pSource.is(DamageTypes.FELL_OUT_OF_WORLD)){
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
                return pSource.is(WADamageSources.GENERATOR);
            }
            case PICKAXE_AND_GENERATOR -> {
                return pSource.is(WADamageSources.GENERATOR) || hasPickAxe(weepingAngel, pSource, itemStack -> true);
            }
            case ANYTHING -> {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPickAxe(WeepingAngel weepingAngel, DamageSource pSource, Predicate<ItemStack> predicate) {
        if (pSource.getEntity() instanceof Player player) {
            boolean hasPickAxe = HurtHelper.validatePickaxe(player, weepingAngel, predicate);
            if (!hasPickAxe) {
                if (weepingAngel.level().random.nextInt(100) <= 10) {
                    weepingAngel.playSound(WASounds.ANGEL_MOCKING.get());
                }
                if(player.level() instanceof ServerLevel serverLevel) {
                    player.hurt(WADamageSources.getSource(serverLevel, WADamageSources.PUNCH_STONE), weepingAngel.level().random.nextInt(5));
                }
                return false;
            }
            ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
            stack.hurtAndBreak(weepingAngel.level().random.nextInt(4), weepingAngel, (Consumer<LivingEntity>) livingEntity -> {
                weepingAngel.playSound(WASounds.ANGEL_MOCKING.get());
                livingEntity.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
            return true;
        }
        return false;
    }

    public enum HurtType {
        PICKAXE, PICKAXE_AND_GENERATOR, NONE, GENERATOR, ANYTHING
    }
}

package mc.craig.software.angels.common.items;

import mc.craig.software.angels.common.entity.angel.ThrowableGenerator;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrowableGeneratorItem extends Item {
    public ThrowableGeneratorItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.OFF_HAND) return InteractionResultHolder.fail(player.getItemInHand(usedHand));
        ItemStack itemStack = player.getItemInHand(usedHand);
        ItemStack offHand = player.getItemInHand(InteractionHand.OFF_HAND);

        if (!level.isClientSide) {
            ThrowableGenerator throwableGenerator = new ThrowableGenerator(player, level);
            throwableGenerator.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            throwableGenerator.setActivated(offHand.is(WAItems.KONTRON_INGOT.get()));
            if(offHand.is(WAItems.KONTRON_INGOT.get()) && !player.isCreative()){
                offHand.shrink(1);
            }
            level.addFreshEntity(throwableGenerator);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}

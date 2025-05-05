package mc.jeryn.dev.statues.common.items;

import mc.jeryn.dev.statues.common.entity.projectile.ThrowableGenerator;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ThrowableGeneratorItem extends Item {
    public ThrowableGeneratorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.OFF_HAND) return InteractionResult.FAIL;
        ItemStack itemStack = player.getItemInHand(usedHand);

        if (!level.isClientSide) {
            ThrowableGenerator throwableGenerator = new ThrowableGenerator(player, level);
            throwableGenerator.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            throwableGenerator.setActivated(true);
            level.addFreshEntity(throwableGenerator);
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}

package mc.craig.software.angels.common.items;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blockentity.Plinth;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChiselItem extends Item {

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (level.getBlockEntity(pos) instanceof Plinth plinth) {
            player.swing(context.getHand());
            if (player.isCrouching()) {
                plinth.changeVariant(plinth);
                plinth.sendUpdates();
                return super.useOn(context);
            }
            if (plinth.getAnimation() == 12) {
                plinth.setAnimation(0);
                plinth.sendUpdates();
                return InteractionResult.SUCCESS;
            }
            plinth.setAnimation(Mth.clamp(plinth.getAnimation() + 1, 0, 12));
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(Component.translatable(WAConstants.CHISEL_POSE));
        tooltip.add(Component.translatable(WAConstants.CHISEL_VARIANT));

    }
}

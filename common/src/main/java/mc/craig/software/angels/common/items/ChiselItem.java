package mc.craig.software.angels.common.items;

import mc.craig.software.angels.client.screen.ChiselScreen;
import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blockentity.Plinth;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

        if (!level.isClientSide()) return InteractionResult.FAIL;
        Player player = context.getPlayer();
        if (level.getBlockEntity(pos) instanceof Plinth plinth) {
            if (level.isClientSide()) { //TODO bad bad bad bad
                Minecraft.getInstance().setScreen(new ChiselScreen(Component.literal("Chisel"), context.getClickedPos(), level.dimension()));
            }
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

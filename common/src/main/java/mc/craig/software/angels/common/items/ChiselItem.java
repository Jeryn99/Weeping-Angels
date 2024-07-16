package mc.craig.software.angels.common.items;

import mc.craig.software.angels.client.screen.ChiselScreen;
import mc.craig.software.angels.common.WAConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionResult;
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
        createUi(pos, level.dimension());
        return super.useOn(context);
    }

    @Environment(value = EnvType.CLIENT)
    public static void createUi(BlockPos blockPos, ResourceKey<Level> levelResourceKey) {
        Minecraft.getInstance().setScreen(new ChiselScreen(Component.literal("Chisel"), blockPos, levelResourceKey));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, list, tooltipFlag);
        list.add(Component.translatable(WAConstants.CHISEL_POSE));
        list.add(Component.translatable(WAConstants.CHISEL_VARIANT));
    }

}

package mc.craig.software.angels.forge.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.GeneratorBlock;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonBus {

    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        if (!GeneratorBlock.isBreakable(event.getLevel(), event.getPos())) {
            event.setCanceled(true);
        }
    }

}

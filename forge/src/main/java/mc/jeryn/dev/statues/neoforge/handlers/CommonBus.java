package mc.jeryn.dev.statues.neoforge.handlers;

import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.common.blocks.GeneratorBlock;
import mc.jeryn.dev.statues.util.WAHelper;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = WeepingAngels.MODID, bus = EventBusSubscriber.Bus.GAME)
public class CommonBus {

    @SubscribeEvent
    public static void onBreakBlock(BlockEvent.BreakEvent event) {
        if (!GeneratorBlock.isBreakable(event.getLevel(), event.getPos())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre event) {
        WAHelper.onPlayerTick(event.getEntity());
    }

}

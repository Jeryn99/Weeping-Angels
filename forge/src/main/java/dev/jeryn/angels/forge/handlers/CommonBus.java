package dev.jeryn.angels.forge.handlers;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.blocks.GeneratorBlock;
import dev.jeryn.angels.util.WAHelper;
import net.minecraftforge.event.TickEvent;
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

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        WAHelper.onPlayerTick(event.player);
    }

}

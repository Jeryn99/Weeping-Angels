package dev.jeryn.angels.forge.handlers;

import dev.jeryn.angels.WAEntitySpawns;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.common.blocks.GeneratorBlock;
import dev.jeryn.angels.util.WAHelper;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
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
    public static void onServerStart(ServerStartedEvent event){
        WAEntitySpawns.init(event.getServer());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        WAHelper.onPlayerTick(event.player);
    }

}

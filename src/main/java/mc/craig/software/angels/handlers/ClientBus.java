package mc.craig.software.angels.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientBus {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
        DonationChecker.checkForUpdate(clientTickEvent);
    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent tickEvent) {
        if (tickEvent.getEntity() instanceof Player player) {
            for (Donator donator : DonationChecker.getModDonators()) {
                if (player.getStringUUID().equals(donator.getUuid())) {
                    donator.tick(player);
                }
            }
        }
    }


}

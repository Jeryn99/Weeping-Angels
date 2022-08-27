package mc.craig.software.angels.forge.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.MercyWingsModel;
import mc.craig.software.angels.client.models.blockentity.CoffinModel;
import mc.craig.software.angels.client.models.entity.angel.AnomalyModel;
import mc.craig.software.angels.client.models.entity.angel.WeepingAngelModel;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static mc.craig.software.angels.client.models.ModelRegistration.*;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientBus {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent clientTickEvent) {
        DonationChecker.checkForUpdate();
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

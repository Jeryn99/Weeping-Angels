package mc.craig.software.angels.neoforge.handlers;

import com.mojang.blaze3d.shaders.FogShape;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import mc.craig.software.angels.util.ClientUtil;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = WeepingAngels.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientBus {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Pre clientTickEvent) {
        DonationChecker.checkForUpdate();
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            ClientUtil.playDectorSound(player);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Pre tickEvent) {
        // Tick Donations
        if (tickEvent.getEntity() instanceof Player player) {
            for (Donator donator : DonationChecker.getModDonators()) {
                if (player.getStringUUID().equals(donator.getUuid())) {
                    donator.tick(player);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onSetupFogDensity(ViewportEvent.RenderFog event) {
        if (Minecraft.getInstance().level != null && CatacombTracker.isInCatacomb()) {
            event.setCanceled(true);
            event.setNearPlaneDistance(-8);
            event.setFarPlaneDistance(60 * 0.5F);
            event.setFogShape(FogShape.SPHERE);
        }
    }

    @SubscribeEvent
    public static void onSetupFogColor(ViewportEvent.RenderFog.ComputeFogColor event) {
        if (Minecraft.getInstance().level != null && CatacombTracker.isInCatacomb()) {
            event.setRed((float) WAHelper.fogColor().x);
            event.setGreen((float) WAHelper.fogColor().y);
            event.setBlue((float) WAHelper.fogColor().z);
        }
    }

}

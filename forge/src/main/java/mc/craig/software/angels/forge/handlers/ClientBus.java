package mc.craig.software.angels.forge.handlers;

import com.mojang.blaze3d.shaders.FogShape;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.donators.DonationChecker;
import mc.craig.software.angels.donators.Donator;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
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
        /*   event.setRed(105 / 255F);
            event.setGreen(105 / 255F);
            event.setBlue(105 / 255F);*/
            event.setRed((float) WAHelper.fogColor().x);
            event.setGreen((float) WAHelper.fogColor().y);
            event.setBlue((float) WAHelper.fogColor().z);
        }
    }

}

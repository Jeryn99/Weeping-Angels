package mc.craig.software.angels.forge.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.WAMusic;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.forge.ModelRegistrationImpl;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.blockentity.StatueRenderer;
import mc.craig.software.angels.client.render.entity.AnomalyRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.client.render.entity.layers.DonationWingsLayer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.forge.overlays.TimeyWimeyOverlay;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModBus {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderOverlay(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("angel", new TimeyWimeyOverlay());
    }

    @SubscribeEvent
    public static void renderLayers(EntityRenderersEvent.AddLayers addLayers) {
        addLayers.getSkins().forEach(skin -> {
            LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> renderer = addLayers.getSkin(skin);
            renderer.addLayer(new DonationWingsLayer(renderer));
        });
    }

    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelRegistration.init();
        ModelRegistrationImpl.register(event);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {

        BlockEntityRenderers.register(WABlockEntities.COFFIN.get(), CoffinRenderer::new);
        BlockEntityRenderers.register(WABlockEntities.STATUE.get(), StatueRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(WABlocks.COFFIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WABlocks.STATUE.get(), RenderType.cutout());


        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(entity, 64);
            if (anomaliesAround.isEmpty()) return null;
            return GlobalPos.of(entity.level.dimension(), anomaliesAround.get(0).blockPosition());
        }));

        WAMusic.init();
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        event.registerEntityRenderer(WAEntities.ANOMALY.get(), AnomalyRenderer::new);
    }

}

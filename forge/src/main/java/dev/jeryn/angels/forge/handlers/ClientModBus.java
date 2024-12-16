package dev.jeryn.angels.forge.handlers;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.models.ModelRegistration;
import dev.jeryn.angels.client.models.forge.ModelRegistrationImpl;
import dev.jeryn.angels.client.render.blockentity.CoffinRenderer;
import dev.jeryn.angels.client.render.blockentity.GeneratorRenderer;
import dev.jeryn.angels.client.render.blockentity.SnowAngelRenderer;
import dev.jeryn.angels.client.render.blockentity.StatueRenderer;
import dev.jeryn.angels.client.render.entity.ThrowableGeneratorRenderer;
import dev.jeryn.angels.client.render.entity.WeepingAngelRenderer;
import dev.jeryn.angels.client.render.entity.layers.DonationWingsLayer;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.common.blockentity.WABlockEntities;
import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.donators.DonationChecker;
import dev.jeryn.angels.forge.overlays.TimeyWimeyOverlay;
import dev.jeryn.angels.util.WAHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.sounds.MusicManager;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
        BlockEntityRenderers.register(WABlockEntities.GENERATOR.get(), GeneratorRenderer::new);
        BlockEntityRenderers.register(WABlockEntities.SNOW_ANGEL.get(), SnowAngelRenderer::new);

        ItemBlockRenderTypes.setRenderLayer(WABlocks.COFFIN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(WABlocks.STATUE.get(), RenderType.cutout());

        DonationChecker.checkForUpdate(true);


        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(entity, 64);
            if (anomaliesAround.isEmpty()) return null;
            return GlobalPos.of(entity.level.dimension(), anomaliesAround.get(0).blockPosition());
        }));
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        event.registerEntityRenderer(WAEntities.GENERATOR.get(), ThrowableGeneratorRenderer::new);
    }

}
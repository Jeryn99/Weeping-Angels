package mc.craig.software.angels.neoforge.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.neoforge.ModelRegistrationImpl;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.blockentity.GeneratorRenderer;
import mc.craig.software.angels.client.render.blockentity.SnowAngelRenderer;
import mc.craig.software.angels.client.render.blockentity.StatueRenderer;
import mc.craig.software.angels.client.render.entity.ThrowableGeneratorRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.client.render.entity.layers.DonationWingsLayer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WATabs;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.neoforge.overlays.TimeyWimeyOverlay;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;


import java.util.List;
import java.util.stream.Stream;

@EventBusSubscriber(modid = WeepingAngels.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {


    @SubscribeEvent
    public static void on(BuildCreativeModeTabContentsEvent event) {
        Stream<Item> values = BuiltInRegistries.ITEM.stream().filter(item -> BuiltInRegistries.ITEM.getKey(item).getNamespace().matches(WeepingAngels.MODID));

        if (event.getTab() == WATabs.MAIN_TAB.get()) {
            for (Item item : values.toList()) {
                event.accept(item);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderOverlay(RegisterGuiLayersEvent event) {
        event.registerAboveAll(ResourceLocation.tryBuild(WeepingAngels.MODID ,"angel"), new TimeyWimeyOverlay());
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
        ItemBlockRenderTypes.setRenderLayer(WABlocks.PLINTH.get(), RenderType.cutout());


        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), ResourceLocation.tryBuild(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(entity, 64);
            if (anomaliesAround.isEmpty()) return null;
            return GlobalPos.of(entity.level().dimension(), anomaliesAround.get(0).blockPosition());
        }));
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        event.registerEntityRenderer(WAEntities.GENERATOR.get(), ThrowableGeneratorRenderer::new);
    }

}

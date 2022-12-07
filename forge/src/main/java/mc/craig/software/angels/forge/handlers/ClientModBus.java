package mc.craig.software.angels.forge.handlers;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.DectectorOverlay;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.forge.ModelRegistrationImpl;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.blockentity.GeneratorRenderer;
import mc.craig.software.angels.client.render.blockentity.SnowAngelRenderer;
import mc.craig.software.angels.client.render.blockentity.StatueRenderer;
import mc.craig.software.angels.client.render.entity.ThrowableGeneratorRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.client.render.entity.layers.DonationWingsLayer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WeepingAngels.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void onRenderOverlay(RenderGameOverlayEvent event) {
        DectectorOverlay.renderOverlay(event.getMatrixStack());
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


        //TODO FIX THIS
     /*   ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new Compass() {
            @Override
            public float unclampedCall(ItemStack arg, @Nullable ClientLevel arg2, @Nullable LivingEntity arg3, int i) {
                List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(arg3, 64);
                if (anomaliesAround.isEmpty()) return 0;
                return GlobalPos.of(arg2.dimension(), anomaliesAround.get(0).blockPosition());
            }
        });*/
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        event.registerEntityRenderer(WAEntities.GENERATOR.get(), ThrowableGeneratorRenderer::new);
    }

}

package dev.jeryn.angels.fabric;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.client.DetectorOverlay;
import dev.jeryn.angels.client.models.ModelRegistration;
import dev.jeryn.angels.client.render.blockentity.CoffinRenderer;
import dev.jeryn.angels.client.render.blockentity.GeneratorRenderer;
import dev.jeryn.angels.client.render.blockentity.SnowAngelRenderer;
import dev.jeryn.angels.client.render.blockentity.StatueRenderer;
import dev.jeryn.angels.client.render.entity.ThrowableGeneratorRenderer;
import dev.jeryn.angels.client.render.entity.WeepingAngelRenderer;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.common.blockentity.WABlockEntities;
import dev.jeryn.angels.common.blocks.WABlocks;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.donators.DonationChecker;
import dev.jeryn.angels.util.WAHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.List;

import static net.minecraft.client.renderer.RenderType.cutout;

public class WeepingAngelsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        entityRenders();
        beRenders();
        itemPredicates();
        ModelRegistration.init();
        overlay();
        DonationChecker.checkForUpdate(true);
    }

    private void overlay() {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> DetectorOverlay.renderOverlay(matrixStack));

    }

    private void beRenders() {
        BlockEntityRendererRegistry.register(WABlockEntities.COFFIN.get(), CoffinRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.STATUE.get(), StatueRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.GENERATOR.get(), GeneratorRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.SNOW_ANGEL.get(), SnowAngelRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(WABlocks.COFFIN.get(), cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WABlocks.STATUE.get(), cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WABlocks.PLINTH.get(), cutout());
    }

    private void itemPredicates() {
        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(entity, 64);
            if (anomaliesAround.isEmpty()) return null;
            return GlobalPos.of(entity.level.dimension(), anomaliesAround.get(0).blockPosition());
        }));
    }

    private void entityRenders() {
        EntityRendererRegistry.register(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        EntityRendererRegistry.register(WAEntities.GENERATOR.get(), ThrowableGeneratorRenderer::new);
    }
}

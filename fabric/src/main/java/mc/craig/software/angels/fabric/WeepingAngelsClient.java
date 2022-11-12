package mc.craig.software.angels.fabric;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.DectectorOverlay;
import mc.craig.software.angels.client.WAMusic;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.render.blockentity.GeneratorRenderer;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.blockentity.SnowAngelRenderer;
import mc.craig.software.angels.client.render.blockentity.StatueRenderer;
import mc.craig.software.angels.client.render.entity.ThrowableGeneratorRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.blocks.WABlocks;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WAHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
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
    }

    private void overlay() {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> DectectorOverlay.renderOverlay(matrixStack));

    }

    private void beRenders() {
        BlockEntityRendererRegistry.register(WABlockEntities.COFFIN.get(), CoffinRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.STATUE.get(), StatueRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.GENERATOR.get(), GeneratorRenderer::new);
        BlockEntityRendererRegistry.register(WABlockEntities.SNOW_ANGEL.get(), SnowAngelRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(WABlocks.COFFIN.get(), cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(WABlocks.STATUE.get(), cutout());
    }

    private void itemPredicates() {
//TODO: Fix this
//        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
//            List<Entity> anomaliesAround = WAHelper.getAnomaliesAroundEntity(entity, 64);
//            if (anomaliesAround.isEmpty()) return null;
//            return GlobalPos.of(entity.level.dimension(), anomaliesAround.get(0).blockPosition());
//        }));
    }

    private void entityRenders() {
        EntityRendererRegistry.register(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        EntityRendererRegistry.register(WAEntities.GENERATOR.get(), ThrowableGeneratorRenderer::new);
    }
}

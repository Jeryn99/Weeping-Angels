package mc.craig.software.angels.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.DectectorOverlay;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.entity.AnomalyRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.util.WAHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.GlobalPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

import java.util.List;

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
        EntityRendererRegistry.register(WAEntities.ANOMALY.get(), AnomalyRenderer::new);
    }
}

package mc.craig.software.angels.fabric;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.render.blockentity.CoffinRenderer;
import mc.craig.software.angels.client.render.entity.AnomalyRenderer;
import mc.craig.software.angels.client.render.entity.WeepingAngelRenderer;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.items.WAItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class WeepingAngelsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        entityRenders();
        beRenders();
        itemPredicates();
        renderLayers();
        ModelRegistration.init();
    }

    private void renderLayers() {

    }

    private void beRenders() {
        BlockEntityRendererRegistry.register(WABlockEntities.COFFIN.get(), CoffinRenderer::new);

    }

    private void itemPredicates() {
        ItemProperties.register(WAItems.TIMEY_WIMEY_DETECTOR.get(), new ResourceLocation(WeepingAngels.MODID, "time"), new CompassItemPropertyFunction((clientLevel, itemStack, entity) -> {
            if (entity instanceof Player player) {
                return player.getLastDeathLocation().orElse(null);
            } else {
                return null;
            }
        }));
    }

    private void entityRenders() {
        EntityRendererRegistry.register(WAEntities.WEEPING_ANGEL.get(), WeepingAngelRenderer::new);
        EntityRendererRegistry.register(WAEntities.ANOMALY.get(), AnomalyRenderer::new);
    }
}

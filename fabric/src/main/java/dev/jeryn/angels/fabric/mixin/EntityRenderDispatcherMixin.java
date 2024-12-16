package dev.jeryn.angels.fabric.mixin;

import dev.jeryn.angels.client.render.entity.layers.DonationWingsLayer;
import net.fabricmc.fabric.mixin.client.rendering.LivingEntityRendererAccessor;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {

    @Shadow
    private Map<String, EntityRenderer<? extends Player>> playerRenderers;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Inject(at = @At("RETURN"), method = "onResourceManagerReload")
    private void onResourceManagerReload(ResourceManager resourceManager, CallbackInfo ci) {
        this.playerRenderers.values().forEach(renderer -> {
            if (renderer instanceof LivingEntityRendererAccessor accessor) {
                accessor.callAddFeature(new DonationWingsLayer((RenderLayerParent) renderer));
            }
        });
    }
}

package mc.craig.software.angels.fabric.mixin;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import mc.craig.software.angels.common.CatacombTracker;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FogRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Inject(at = @At("TAIL"), method = "setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V")
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean bl, float f, CallbackInfo ci) {
        if (Minecraft.getInstance().player != null && CatacombTracker.isInCatacomb()) {
            RenderSystem.setShaderFogStart(-8);
            RenderSystem.setShaderFogEnd(60 * 0.5F);
            RenderSystem.setShaderFogShape(FogShape.SPHERE);
        }
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "levelFogColor()V")
    private static void setupColor(CallbackInfo callbackInfo) {
        if (Minecraft.getInstance().player != null && CatacombTracker.isInCatacomb()) {
            RenderSystem.setShaderFogColor((float) WAHelper.fogColor().x, (float) WAHelper.fogColor().y, (float) WAHelper.fogColor().z);
            callbackInfo.cancel();
        }
    }

}

package me.suff.mc.angels.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import me.suff.mc.angels.client.renders.WingsLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin {

    @Inject(at = @At("HEAD"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", cancellable = true)
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int p_116953_, LivingEntity livingEntity, float p_116955_, float p_116956_, float p_116957_, float p_116958_, float p_116959_, float p_116960_, CallbackInfo callbackInfo) {
        if (livingEntity instanceof Player player) {
            if (WingsLayer.shouldDisplay(player) != null && !player.isModelPartShown(PlayerModelPart.CAPE)) {
                callbackInfo.cancel();
            }
        }
    }
}

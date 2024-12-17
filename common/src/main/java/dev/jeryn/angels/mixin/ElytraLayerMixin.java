package dev.jeryn.angels.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.jeryn.angels.donators.DonationChecker;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.Entity;
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
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, LivingEntity livingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo callbackInfo) {
        if (livingEntity instanceof Player player) {
            DonationChecker.getDonatorData(player).ifPresent(donator -> {
                if (!player.isModelPartShown(PlayerModelPart.CAPE)) {
                    callbackInfo.cancel();
                }
            });
        }
    }

}

package mc.jeryn.dev.statues.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.jeryn.dev.statues.donators.DonationChecker;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WingsLayer.class)
public class ElytraLayerMixin {


    @Inject(at = @At("HEAD"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V", cancellable = true)
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, S humanoidRenderState, float f, float g, CallbackInfo ci) {
        if (livingEntity instanceof Player player) {
            DonationChecker.getDonatorData(player).ifPresent(donator -> {
                if (!player.isModelPartShown(PlayerModelPart.CAPE)) {
                    callbackInfo.cancel();
                }
            });
        }
    }

    @Inject(at = @At("HEAD"), method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/Entity;FFFFFF)V", cancellable = true)
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, Entity livingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo callbackInfo) {
        if (livingEntity instanceof Player player) {
            DonationChecker.getDonatorData(player).ifPresent(donator -> {
                if (!player.isModelPartShown(PlayerModelPart.CAPE)) {
                    callbackInfo.cancel();
                }
            });
        }
    }
}

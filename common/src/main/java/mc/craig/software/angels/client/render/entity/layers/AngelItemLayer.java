package mc.craig.software.angels.client.render.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import mc.craig.software.angels.client.models.entity.angel.AngelModel;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;

public class AngelItemLayer extends RenderLayer<WeepingAngel, AngelModel> {

    public AngelItemLayer(RenderLayerParent<WeepingAngel, AngelModel> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, WeepingAngel livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {

        poseStack.pushPose();
        float height = livingEntity.getBbHeight() - 1;
        poseStack.translate(0.0, -height, 0.0);
        poseStack.mulPose(Minecraft.getInstance().getEntityRenderDispatcher().cameraOrientation());
        Minecraft.getInstance().getItemRenderer().renderStatic(livingEntity.getMainHandItem(), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, livingEntity.level(), livingEntity.getId());
        poseStack.popPose();
    }
}

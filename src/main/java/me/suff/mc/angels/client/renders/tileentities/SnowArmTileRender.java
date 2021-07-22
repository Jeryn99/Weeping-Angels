package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.block.SnowArmModel;
import me.suff.mc.angels.client.models.block.SnowBodyModel;
import me.suff.mc.angels.client.models.block.SnowHeadModel;
import me.suff.mc.angels.client.models.block.SnowWingsModel;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.tileentities.SnowArmTile;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnowArmTileRender implements BlockEntityRenderer<SnowArmTile> {

    private final SnowArmModel armModel = new SnowArmModel();
    private final SnowBodyModel bodyModel = new SnowBodyModel();
    private final SnowHeadModel headModel = new SnowHeadModel();
    private final SnowWingsModel wingsModel = new SnowWingsModel();

    public static ResourceLocation getTexture(SnowArmTile snowArmTile) {
        IAngelModel iAngelModel = (IAngelModel) ClientUtil.getModelForAngel(AngelEnums.AngelType.ANGELA_MC);
        return iAngelModel.generateTex(WeepingAngelPose.APPROACH, snowArmTile.getVariant());
    }

    @Override
    public void render(SnowArmTile snowArmTile, float partialTicks, PoseStack matrixStack, MultiBufferSource iRenderTypeBuffer, int i, int i1) {
        matrixStack.pushPose();
        switch (snowArmTile.getSnowAngelStage()) {
            case ARM:
                matrixStack.translate(0.5F, -0.7F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                this.armModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case HEAD:
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.headModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case BODY:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case WINGS:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
        }

        matrixStack.popPose();
    }
}

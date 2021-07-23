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
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SnowArmTileRender implements BlockEntityRenderer<SnowArmTile>, BlockEntityRendererProvider<SnowArmTile> {

    private ModelPart armModel;
    private ModelPart bodyModel;
    private ModelPart headModel;
    private ModelPart wingsModel;
    
    public SnowArmTileRender(BlockEntityRendererProvider.Context context) {
        this.armModel = context.bakeLayer(ModelLayers.BED_HEAD);
        this.bodyModel = context.bakeLayer(ModelLayers.BED_FOOT);
        this.headModel = context.bakeLayer(ModelLayers.BED_FOOT);
        this.wingsModel = context.bakeLayer(ModelLayers.BED_FOOT);
    }
    
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
                this.armModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case HEAD:
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.headModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case BODY:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case WINGS:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
        }

        matrixStack.popPose();
    }

    @Override
    public BlockEntityRenderer<SnowArmTile> create(Context p_173571_) {
        return new SnowArmTileRender(p_173571_);
    }
}

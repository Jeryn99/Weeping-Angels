package me.suff.mc.angels.client.renders.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.client.models.entity.WAModels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.blockentities.SnowAngelBlockEntity;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.utils.ClientUtil;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SnowAngelRenderer implements BlockEntityRenderer<SnowAngelBlockEntity>, BlockEntityRendererProvider<SnowAngelBlockEntity> {

    private final ModelPart armModel;
    private final ModelPart bodyModel;
    private final ModelPart headModel;
    private final ModelPart wingsModel;

    public SnowAngelRenderer(BlockEntityRendererProvider.Context context) {
        this.armModel = context.bakeLayer(WAModels.SNOW_ANGEL_ARM);
        this.bodyModel = context.bakeLayer(WAModels.SNOW_ANGEL_BODY);
        this.headModel = context.bakeLayer(WAModels.SNOW_ANGEL_HEAD);
        this.wingsModel = context.bakeLayer(WAModels.SNOW_ANGEL_WING);
    }

    public static ResourceLocation getTexture(SnowAngelBlockEntity snowAngelBlockEntity) {
        IAngelModel iAngelModel = (IAngelModel) ClientUtil.getModelForAngel(AngelEnums.AngelType.ANGELA_MC);
        return iAngelModel.generateTex(WeepingAngelPose.APPROACH, snowAngelBlockEntity.getVariant());
    }

    @Override
    public void render(SnowAngelBlockEntity snowAngelBlockEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource iRenderTypeBuffer, int i, int i1) {
        matrixStack.pushPose();
        switch (snowAngelBlockEntity.getSnowAngelStage()) {
            case ARM -> {
                matrixStack.translate(0.5F, -1.1F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                this.armModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), i, i1, 1F, 1F, 1F, 1F);
            }
            case HEAD -> {
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.headModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), i, i1, 1F, 1F, 1F, 1F);
            }
            case BODY -> {
                matrixStack.translate(0.5F, 1.7F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), i, i1, 1F, 1F, 1F, 1F);
            }
            case WINGS -> {
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), i, i1, 1F, 1F, 1F, 1F);
            }
        }

        matrixStack.popPose();
    }

    @Override
    public BlockEntityRenderer<SnowAngelBlockEntity> create(Context p_173571_) {
        return new SnowAngelRenderer(p_173571_);
    }
}

package craig.software.mc.angels.client.renders.blockentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.models.entity.WAModels;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.utils.ClientUtil;
import craig.software.mc.angels.common.blockentities.SnowAngelBlockEntity;
import craig.software.mc.angels.common.entities.AngelType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

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
        IAngelModel iAngelModel = (IAngelModel) ClientUtil.getModelForAngel(AngelType.DISASTER_MC);
        return iAngelModel.generateTex(WeepingAngelPose.APPROACH, snowAngelBlockEntity.getVariant());
    }

    @Override
    public void render(SnowAngelBlockEntity snowAngelBlockEntity, float partialTicks, PoseStack matrixStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        switch (snowAngelBlockEntity.getSnowAngelStage()) {
            case ARM -> {
                matrixStack.translate(0.5F, -1.1F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                this.armModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
            }
            case HEAD -> {
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.headModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, combinedOverlayIn, 1F, 1F, 1F, 1F);
            }
            case BODY -> {
                matrixStack.translate(0.5F, 1.7F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, combinedOverlayIn, 1F, 1F, 1F, 1F);
            }
            case WINGS -> {
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowAngelBlockEntity.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, combinedOverlayIn, 1F, 1F, 1F, 1F);
            }
        }

        matrixStack.popPose();
    }

    @Override
    public @NotNull BlockEntityRenderer<SnowAngelBlockEntity> create(@NotNull Context p_173571_) {
        return new SnowAngelRenderer(p_173571_);
    }
}

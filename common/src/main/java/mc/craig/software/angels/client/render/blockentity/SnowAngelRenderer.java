package mc.craig.software.angels.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.math.Axis;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.common.blockentity.SnowAngelBlockEntity;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import mc.craig.software.angels.common.entity.angel.ai.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SnowAngelRenderer implements BlockEntityRenderer<SnowAngelBlockEntity>, BlockEntityRendererProvider<SnowAngelBlockEntity> {

    private final ModelPart armModel;
    private final ModelPart bodyModel;
    private final ModelPart headModel;
    private final ModelPart wingsModel;

    public SnowAngelRenderer(BlockEntityRendererProvider.Context context) {
        this.armModel = context.bakeLayer(ModelRegistration.SNOW_ARM);
        this.bodyModel = context.bakeLayer(ModelRegistration.SNOW_BODY);
        this.headModel = context.bakeLayer(ModelRegistration.SNOW_HEAD);
        this.wingsModel = context.bakeLayer(ModelRegistration.SNOW_WINGS);
    }

    public static ResourceLocation getTexture(SnowAngelBlockEntity snowAngelBlockEntity) {
        return ModelRegistration.getModelFor(AngelVariant.STONE).texture(AngelEmotion.ANGRY, AngelVariant.STONE);
    }

    @Override
    public void render(SnowAngelBlockEntity snowAngelBlockEntity, float partialTicks, PoseStack matrixStack, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();

        BlockState blockstate = snowAngelBlockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(CoffinBlock.ROTATION);
        
        switch (snowAngelBlockEntity.getSnowAngelStages()) {
            case ARM -> {
                matrixStack.translate(0.5F, -1.1F, 0.5F);
                matrixStack.mulPose(Axis.YN.rotationDegrees(rotation));
                this.armModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
            }
            case HEAD -> {
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Axis.YN.rotationDegrees(rotation));
                matrixStack.mulPose(Axis.ZP.rotationDegrees(180));
                this.headModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, combinedOverlayIn, 1F, 1F, 1F, 1F);
            }
            case BODY -> {
                matrixStack.translate(0.5F, 1.7F, 0.5F);
                matrixStack.mulPose(Axis.YN.rotationDegrees(rotation));
                matrixStack.mulPose(Axis.ZP.rotationDegrees(180));
                this.bodyModel.render(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(getTexture(snowAngelBlockEntity))), combinedLightIn, combinedOverlayIn, 1F, 1F, 1F, 1F);
            }
            case WINGS -> {
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Axis.YN.rotationDegrees(rotation));
                matrixStack.mulPose(Axis.ZP.rotationDegrees(180));
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
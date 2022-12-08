package mc.craig.software.angels.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.entity.angel.AngelModel;
import mc.craig.software.angels.common.blockentity.StatueBlockEntity;
import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;

public class StatueRenderer implements BlockEntityRenderer<StatueBlockEntity>, BlockEntityRendererProvider<StatueBlockEntity> {


    public StatueRenderer(BlockEntityRendererProvider.Context context) {
        ModelRegistration.regModels(context);
    }

    @Override
    public void render(StatueBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        BlockState blockstate = blockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBaseBlock.ROTATION);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        AngelModel model = ModelRegistration.getModelFor(blockEntity.getVariant());
        model.animateTile(blockEntity);
        model.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityCutout(model.texture(blockEntity.getEmotion(), blockEntity.getVariant()))), packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        poseStack.popPose();
    }

    @Override
    public BlockEntityRenderer<StatueBlockEntity> create(Context context) {
        return new StatueRenderer(context);
    }
}

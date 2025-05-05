package mc.jeryn.dev.statues.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mc.jeryn.dev.statues.client.models.ModelRegistration;
import mc.jeryn.dev.statues.client.models.entity.angel.AngelModel;
import mc.jeryn.dev.statues.common.blockentity.StatueBlockEntity;
import mc.jeryn.dev.statues.common.blocks.StatueBaseBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class StatueRenderer implements BlockEntityRenderer<StatueBlockEntity>, BlockEntityRendererProvider<StatueBlockEntity> {


    public StatueRenderer(BlockEntityRendererProvider.Context context) {
        ModelRegistration.regModels(context);
    }


    @Override
    public BlockEntityRenderer<StatueBlockEntity> create(Context context) {
        return new StatueRenderer(context);
    }

    @Override
    public void render(StatueBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int j, Vec3 vec3) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        BlockState blockstate = blockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBaseBlock.ROTATION);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        AngelModel model = ModelRegistration.getModelFor(blockEntity.getVariant());
        model.animateTile(blockEntity);
        model.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(model.texture(blockEntity.getEmotion(), blockEntity.getVariant()))), packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}

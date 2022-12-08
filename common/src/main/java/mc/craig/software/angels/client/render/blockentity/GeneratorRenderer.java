package mc.craig.software.angels.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.blockentity.GeneratorModel;
import mc.craig.software.angels.client.models.blockentity.PortalModel;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.blocks.GeneratorBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class GeneratorRenderer implements BlockEntityRenderer<GeneratorBlockEntity>, BlockEntityRendererProvider<GeneratorBlockEntity> {


    private static final ResourceLocation GENERATOR_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entity/generator/vortex_generator.png");
    private static final ResourceLocation GENERATOR_ACTIVATED_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entity/generator/vortex_generator_armed.png");
    private static final ResourceLocation VORTEX_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entity/generator/vortex.png");
    private final PortalModel vortexModel;
    private final GeneratorModel generatorModel;

    public GeneratorRenderer(BlockEntityRendererProvider.Context context) {
        vortexModel = new PortalModel(context.bakeLayer(ModelRegistration.PORTAL));
        generatorModel = new GeneratorModel(context.bakeLayer(ModelRegistration.GENERATOR));
    }


    @Override
    public void render(GeneratorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        poseStack.pushPose();

        BlockState blockstate = blockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(GeneratorBlock.ROTATION);

        if (blockEntity.hasSpawned() && blockEntity.isActivated()) {
            poseStack.translate(0.5F, -0.3F, 0.5F);
            poseStack.scale(2, 0.3F, 2);
            poseStack.mulPose(Axis.YP.rotationDegrees(Minecraft.getInstance().player.tickCount * 20));
            vortexModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(VORTEX_TEX)), packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        } else {
            poseStack.translate(0.5F, 1.5F, 0.5F);
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
            generatorModel.animateTile(blockEntity);
            generatorModel.renderToBuffer(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(blockEntity.isActivated() ? GENERATOR_ACTIVATED_TEX : GENERATOR_TEX)), packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }

        poseStack.popPose();
    }

    @Override
    public BlockEntityRenderer<GeneratorBlockEntity> create(Context context) {
        return new GeneratorRenderer(context);
    }
}

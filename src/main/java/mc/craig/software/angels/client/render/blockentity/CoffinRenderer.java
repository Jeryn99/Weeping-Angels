package mc.craig.software.angels.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mc.craig.software.angels.client.models.ModelRegistration;
import mc.craig.software.angels.client.models.blockentity.CoffinModel;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class CoffinRenderer implements BlockEntityRenderer<CoffinBlockEntity>, BlockEntityRendererProvider<CoffinBlockEntity> {

    private static CoffinModel coffinModel;

    public CoffinRenderer(BlockEntityRendererProvider.Context context) {
        coffinModel = new CoffinModel(context.bakeLayer(ModelRegistration.COFFIN));
    }

    @Override
    public void render(CoffinBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.5F, 0.5F);
        BlockState blockstate = pBlockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(CoffinBlock.ROTATION);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        pPoseStack.translate(0F, -1F, 0F);
        coffinModel.animateTile(pBlockEntity);
        coffinModel.renderToBuffer(pPoseStack, pBufferSource.getBuffer(RenderType.entityCutout(pBlockEntity.getCoffinType().getTexture())), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        pPoseStack.popPose();

    }

    @Override
    public @NotNull BlockEntityRenderer<CoffinBlockEntity> create(@NotNull BlockEntityRendererProvider.Context context) {
        return new CoffinRenderer(context);
    }
}

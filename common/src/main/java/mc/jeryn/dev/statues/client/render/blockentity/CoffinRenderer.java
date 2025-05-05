package mc.jeryn.dev.statues.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import mc.jeryn.dev.statues.client.models.ModelRegistration;
import mc.jeryn.dev.statues.client.models.blockentity.CoffinModel;
import mc.jeryn.dev.statues.client.models.blockentity.TardisModel;
import mc.jeryn.dev.statues.common.blockentity.CoffinBlockEntity;
import mc.jeryn.dev.statues.common.blocks.CoffinBlock;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class CoffinRenderer implements BlockEntityRenderer<CoffinBlockEntity>, BlockEntityRendererProvider<CoffinBlockEntity> {

    private static CoffinModel coffinModel;
    private static TardisModel tardisModel;
    private float currentAlpha;

    public CoffinRenderer(BlockEntityRendererProvider.Context context) {
        coffinModel = new CoffinModel(context.bakeLayer(ModelRegistration.COFFIN));
        tardisModel = new TardisModel(context.bakeLayer(ModelRegistration.TARDIS));
    }

    public static int rgbaToInt(float red, float green, float blue, float alpha) {
        int r = Math.round(red * 255);
        int g = Math.round(green * 255);
        int b = Math.round(blue * 255);
        int a = Math.round(alpha * 255);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    @Override
    public @NotNull BlockEntityRenderer<CoffinBlockEntity> create(@NotNull BlockEntityRendererProvider.Context context) {
        return new CoffinRenderer(context);
    }

    @Override
    public void render(CoffinBlockEntity coffinBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource multiBufferSource, int pPackedLight, int j, Vec3 vec3) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.5F, 0.5F);
        BlockState blockstate = coffinBlockEntity.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(CoffinBlock.ROTATION);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(180F));
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotation));

        if (!coffinBlockEntity.getBlockState().getValue(CoffinBlock.UPRIGHT)) {
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-90F));
        } else {
            pPoseStack.translate(0F, -1F, 0F);
        }

        if (coffinBlockEntity.getCoffinType().isTardis()) {
            pPoseStack.translate(0, 0.5, 0);
            pPoseStack.scale(0.7F, 0.7F, 0.7F);

            tardisModel.root().getAllParts().forEach(ModelPart::resetPose);

            if(coffinBlockEntity.isDemat()) {
                coffinBlockEntity.TARDIS_TAKEOFF.start(coffinBlockEntity.animationTimer);
            } else {
                coffinBlockEntity.TARDIS_TAKEOFF.stop();
            }

            tardisModel.animate(coffinBlockEntity.TARDIS_TAKEOFF, coffinBlockEntity.animationTimer);
            currentAlpha = (coffinBlockEntity.isDemat()) ? (tardisModel.initAlpha - tardisModel.fadeValue().y) * 0.1f : 1;
            tardisModel.renderToBuffer(pPoseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(coffinBlockEntity.getCoffinType().getTexture())), pPackedLight, OverlayTexture.NO_OVERLAY, rgbaToInt(1, 1, 1, currentAlpha));
        } else {
            coffinModel.animateTile(coffinBlockEntity);
            coffinModel.renderToBuffer(pPoseStack, multiBufferSource.getBuffer(RenderType.entityCutout(coffinBlockEntity.getCoffinType().getTexture())), pPackedLight, OverlayTexture.NO_OVERLAY);
        }

        pPoseStack.popPose();
    }
}

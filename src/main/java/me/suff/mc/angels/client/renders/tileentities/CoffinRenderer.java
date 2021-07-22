package me.suff.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.block.CoffinModel;
import me.suff.mc.angels.client.models.block.PoliceBoxModel;
import me.suff.mc.angels.common.blocks.CoffinBlock;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.block.state.BlockState;

public class CoffinRenderer extends BlockEntityRenderer<CoffinTile> {

    private static final CoffinModel coffinModel = new CoffinModel();
    private static final PoliceBoxModel coffinModelPTB = new PoliceBoxModel();
    private static Skeleton skeletonEntity = null;

    public CoffinRenderer(BlockEntityRenderDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(CoffinTile tileEntityIn, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {

        if (skeletonEntity == null) {
            skeletonEntity = new Skeleton(EntityType.SKELETON, Minecraft.getInstance().level);
        }
        matrixStack.pushPose();
        matrixStack.translate(0.5F, 0.5F, 0.5F); //Translate to blockpos
        BlockState blockstate = tileEntityIn.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(CoffinBlock.ROTATION);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F)); // Make model not upside down
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));


        //Horizontal Placement
        if (!tileEntityIn.getBlockState().getValue(CoffinBlock.UPRIGHT)) {
            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-90F));

        } else {
            matrixStack.translate(0F, -1F, 0F);

        }
        renderSkeleton(tileEntityIn, matrixStack, bufferIn, combinedLightIn);

        //Handle actual rendering
        ResourceLocation texture = getTexture(tileEntityIn.getCoffin());
        if (!tileEntityIn.getCoffin().isPoliceBox()) {
            coffinModel.door.yRot = -(tileEntityIn.getOpenAmount() * ((float) Math.PI / 3F));
            coffinModel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityCutout(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        } else {
            matrixStack.translate(0, 0.5, 0);
            matrixStack.scale(0.7F, 0.7F, 0.7F);
            coffinModelPTB.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityTranslucent(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, tileEntityIn.getAlpha());
        }
        matrixStack.popPose();
    }

    private void renderSkeleton(CoffinTile tileEntityIn, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLightIn) {
        if (tileEntityIn.hasSkeleton()) {
            matrixStack.pushPose();
            matrixStack.mulPose(Vector3f.YP.rotationDegrees(-180F)); // Make model not upside down
            matrixStack.translate(0F, 1.5F, 0F);
            EntityRenderer<? super Skeleton> renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(skeletonEntity);
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(-180F)); // Make model not upside down
            renderer.render(skeletonEntity, 0, 0, matrixStack, bufferIn, combinedLightIn);
            matrixStack.popPose();
        }
    }

    public ResourceLocation getTexture(CoffinTile.Coffin coffin) {
        return new ResourceLocation(WeepingAngels.MODID, "textures/tiles/coffin/" + coffin.name().toLowerCase() + ".png");
    }
}

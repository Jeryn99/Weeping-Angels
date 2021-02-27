package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.entity.IAngelModel;
import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.blocks.PlinthBlock;
import me.swirtzly.minecraft.angels.common.blocks.StatueBlock;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import static me.swirtzly.minecraft.angels.common.blocks.PlinthBlock.CLASSIC;

public class PlinthTileRender extends TileEntityRenderer< PlinthTile > {

    public PlinthTileRender(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(PlinthTile plinthTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.push();
        matrixStack.translate(0.5F, 2.5F, 0.5F);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = plinthTile.getBlockState();
        float rotation = 22.5F * (float) blockstate.get(StatueBlock.ROTATION);
        matrixStack.rotate(Vector3f.YP.rotationDegrees(rotation));
        EntityModel< WeepingAngelEntity > angel = ClientUtil.getModelForAngel(plinthTile.getAngelType());
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkinLegacy();

        if(plinthTile.getBlockState().get(CLASSIC)){
            matrixStack.translate(0,0.5,0);
        }

        WeepingAngelPose pose = plinthTile.getPose();
        if (angel instanceof IAngelModel) {
            IAngelModel angelModel = (IAngelModel) angel;
            angelModel.setAngelPose(pose);
            texture = angelModel.getTextureForPose(plinthTile, pose);
        }
        angel.setRotationAngles(null, 0, 0, 0, 0, 0);
        angel.render(matrixStack, bufferIn.getBuffer(RenderType.getEntityCutout(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.pop();
    }
}

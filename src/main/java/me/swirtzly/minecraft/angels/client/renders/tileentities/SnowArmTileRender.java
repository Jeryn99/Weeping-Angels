package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.swirtzly.minecraft.angels.client.models.block.SnowArmModel;
import me.swirtzly.minecraft.angels.client.models.block.SnowBodyModel;
import me.swirtzly.minecraft.angels.client.models.block.SnowHeadModel;
import me.swirtzly.minecraft.angels.client.models.block.SnowWingsModels;
import me.swirtzly.minecraft.angels.client.models.entity.IAngelModel;
import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import me.swirtzly.minecraft.angels.utils.ClientUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class SnowArmTileRender extends TileEntityRenderer< SnowArmTile > {

    private final SnowArmModel armModel = new SnowArmModel();
    private final SnowBodyModel bodyModel = new SnowBodyModel();
    private final SnowHeadModel headModel = new SnowHeadModel();
    private final SnowWingsModels wingsModel = new SnowWingsModels();

    public SnowArmTileRender(TileEntityRendererDispatcher renderer) {
        super(renderer);
    }

    public static ResourceLocation getTexture(SnowArmTile snowArmTile) {
        IAngelModel iAngelModel = (IAngelModel) ClientUtil.getModelForAngel(AngelEnums.AngelType.ANGELA_MC);
        return iAngelModel.generateTex(WeepingAngelPose.APPROACH, snowArmTile.getAngelVariants());
    }

    @Override
    public void render(SnowArmTile snowArmTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        matrixStack.push();
        switch (snowArmTile.getSnowAngelStage()) {
            case ARM:
                matrixStack.translate(0.5F, -0.7F, 0.5F);
                matrixStack.rotate(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                this.armModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.getEntityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case HEAD:
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.rotate(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
                this.headModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.getEntityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case BODY:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.rotate(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.getEntityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case WINGS:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.rotate(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.render(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.getEntityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
        }

        matrixStack.pop();
    }
}

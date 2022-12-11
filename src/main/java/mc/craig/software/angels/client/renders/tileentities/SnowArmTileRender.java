package mc.craig.software.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import mc.craig.software.angels.client.models.block.SnowArmModel;
import mc.craig.software.angels.client.models.block.SnowBodyModel;
import mc.craig.software.angels.client.models.block.SnowHeadModel;
import mc.craig.software.angels.client.models.block.SnowWingsModel;
import mc.craig.software.angels.client.models.entity.IAngelModel;
import mc.craig.software.angels.client.poses.WeepingAngelPose;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.tileentities.SnowArmTile;
import mc.craig.software.angels.utils.ClientUtil;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class SnowArmTileRender extends TileEntityRenderer<SnowArmTile> {

    private final SnowArmModel armModel = new SnowArmModel();
    private final SnowBodyModel bodyModel = new SnowBodyModel();
    private final SnowHeadModel headModel = new SnowHeadModel();
    private final SnowWingsModel wingsModel = new SnowWingsModel();

    public SnowArmTileRender(TileEntityRendererDispatcher renderer) {
        super(renderer);
    }

    public static ResourceLocation getTexture(SnowArmTile snowArmTile) {
        IAngelModel iAngelModel = (IAngelModel) ClientUtil.getModelForAngel(AngelType.DISASTER_MC);
        return iAngelModel.generateTex(WeepingAngelPose.APPROACH, snowArmTile.getVariant());
    }

    @Override
    public void render(SnowArmTile snowArmTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer iRenderTypeBuffer, int i, int i1) {
        matrixStack.pushPose();
        switch (snowArmTile.getSnowAngelStage()) {
            case ARM:
                matrixStack.translate(0.5F, -0.7F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                this.armModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case HEAD:
                matrixStack.translate(0.5F, 1.6F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.headModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case BODY:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.bodyModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
            case WINGS:
                matrixStack.translate(0.5F, 1.5F, 0.5F);
                matrixStack.mulPose(Vector3f.YN.rotationDegrees(snowArmTile.getRotation()));
                matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180));
                this.wingsModel.renderToBuffer(matrixStack, iRenderTypeBuffer.getBuffer(RenderType.entityTranslucent(getTexture(snowArmTile))), i, i1, 1F, 1F, 1F, 1F);
                break;
        }

        matrixStack.popPose();
    }
}

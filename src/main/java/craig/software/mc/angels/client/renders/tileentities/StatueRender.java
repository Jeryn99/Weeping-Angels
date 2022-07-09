package craig.software.mc.angels.client.renders.tileentities;

import com.mojang.blaze3d.matrix.MatrixStack;
import craig.software.mc.angels.client.models.entity.IAngelModel;
import craig.software.mc.angels.client.poses.WeepingAngelPose;
import craig.software.mc.angels.client.renders.layers.SantaHatLayer;
import craig.software.mc.angels.common.blocks.StatueBlock;
import craig.software.mc.angels.common.entities.WeepingAngelEntity;
import craig.software.mc.angels.common.tileentities.StatueTile;
import craig.software.mc.angels.utils.ClientUtil;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

/**
 * Created by Craig on 17/02/2020 @ 12:18
 */
public class StatueRender extends TileEntityRenderer<StatueTile> {

    public StatueRender(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @Override
    public void render(StatueTile statueTile, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStack.pushPose();
        matrixStack.translate(0.5F, 1.5F, 0.5F);
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        BlockState blockstate = statueTile.getBlockState();
        float rotation = 22.5F * (float) blockstate.getValue(StatueBlock.ROTATION);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        EntityModel<WeepingAngelEntity> angel = ClientUtil.getModelForAngel(statueTile.getAngelType());
        ResourceLocation texture = DefaultPlayerSkin.getDefaultSkin();

        WeepingAngelPose pose = statueTile.getPose();
        if (angel instanceof IAngelModel) {
            IAngelModel angelModel = (IAngelModel) angel;
            angelModel.setAngelPose(pose);
            texture = angelModel.getTextureForPose(statueTile, pose);
            angel.setupAnim(null, 0, 0, 0, 0, 0);

            matrixStack.pushPose();
            SantaHatLayer.santaHat(matrixStack, bufferIn, combinedLightIn, SantaHatLayer.santaHat, angel, statueTile.getVariant());
            matrixStack.popPose();
        }
        angel.setupAnim(null, 0, 0, 0, 0, 0);
        angel.renderToBuffer(matrixStack, bufferIn.getBuffer(RenderType.entityCutout(texture)), combinedLightIn, combinedOverlayIn, 1, 1, 1, 1);
        matrixStack.popPose();
    }
}

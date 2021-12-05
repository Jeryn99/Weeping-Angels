package me.suff.mc.angels.client.renders.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.models.SantaHat;
import me.suff.mc.angels.client.models.entity.IAngelModel;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.DateChecker;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class SantaHatLayer extends LayerRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {

    public static final ResourceLocation SANTA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/santa_hat.png");
    public static SantaHat santaHat = new SantaHat();

    public SantaHatLayer(IEntityRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> iEntityRenderer) {
        super(iEntityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, WeepingAngelEntity weepingAngel, float p_225628_5_, float p_225628_6_, float p_225628_7_, float p_225628_8_, float p_225628_9_, float p_225628_10_) {
        if(!weepingAngel.isInvisible() && DateChecker.isXmas()) {
            santaHat(matrixStack, renderTypeBuffer, light, santaHat, getParentModel(), weepingAngel.getVariant());
        }
    }

    public static void santaHat(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, EntityModel santa, EntityModel model, AbstractVariant abstractVariant) {
        if(abstractVariant.isHeadless()) return;
        if (model instanceof IAngelModel) {
            IAngelModel angelModel = (IAngelModel) model;
            if (DateChecker.isXmas() && angelModel.getSantaAttachment(pMatrixStack, false) != null && WAConfig.CONFIG.showSantaHatsAtXmas.get()) {
                pMatrixStack.pushPose();
                angelModel.getSantaAttachment(pMatrixStack, false).translateAndRotate(pMatrixStack);
                angelModel.getSantaAttachment(pMatrixStack, true);
                santa.renderToBuffer(pMatrixStack, pBuffer.getBuffer(RenderType.entityTranslucent(SANTA)), pPackedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
                pMatrixStack.popPose();
            }
        }
    }

}

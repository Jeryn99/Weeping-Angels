package me.swirtzly.minecraft.angels.client.renders.entities;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrackLayer extends LayerRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> {
    private static final ResourceLocation CRACK_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
    private static final ResourceLocation CRACK_TEX_2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
    private static final ResourceLocation CRACK_ANGELA = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_angela_cracked.png");

    public CrackLayer(IEntityRenderer<WeepingAngelEntity, EntityModel<WeepingAngelEntity>> iEntityRenderer) {
        super(iEntityRenderer);
    }


    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, WeepingAngelEntity angelEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (angelEntity.hurtTime > 0 || angelEntity.getHealth() <= 10 && angelEntity.getHealth() > 0) {
            EntityModel<WeepingAngelEntity> entitymodel = this.getEntityModel();
            entitymodel.setLivingAnimations(angelEntity, limbSwing, limbSwingAmount, partialTicks);
            this.getEntityModel().copyModelAttributesTo(entitymodel);
            IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(getCrackTex(angelEntity)));
            entitymodel.setRotationAngles(angelEntity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            entitymodel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
        }
    }

    public static ResourceLocation getCrackTex(WeepingAngelEntity weepingAngelEntity) {
        AngelEnums.AngelType angelType = weepingAngelEntity.getAngelType();
        if (angelType == AngelEnums.AngelType.ED) {
            return CRACK_TEX;
        }

        if (angelType == AngelEnums.AngelType.ANGELA) {
            return CRACK_ANGELA;
        }

        return CRACK_TEX_2;
    }
}
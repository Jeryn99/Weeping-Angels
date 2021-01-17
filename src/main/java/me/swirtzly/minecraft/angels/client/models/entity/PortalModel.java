package me.swirtzly.minecraft.angels.client.models.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.common.entities.AnomalyEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PortalModel extends EntityModel<AnomalyEntity> {

    private final ModelRenderer PortalMain;
    private final ModelRenderer PortalFrontage;
    private final ModelRenderer PortalFrontage2;

    public PortalModel() {
        textureWidth = 64;
        textureHeight = 64;

        PortalMain = new ModelRenderer(this);
        PortalMain.setRotationPoint(0.0F, 8.0F, 0.0F);
        PortalMain.setTextureOffset(0, 0).addBox(-16.0F, -16.0F, 0.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage = new ModelRenderer(this);
        PortalFrontage.setRotationPoint(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage, 0.0F, 0.0F, 0.7854F);
        PortalFrontage.setTextureOffset(0, 32).addBox(-16.0F, -16.0F, -1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage2 = new ModelRenderer(this);
        PortalFrontage2.setRotationPoint(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage2, 0.0F, 0.0F, -0.7854F);
        PortalFrontage2.setTextureOffset(0, 32).addBox(-16.0F, -16.0F, 1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

    }

    @Override
    public void setRotationAngles(AnomalyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PortalMain.rotateAngleZ = -entityIn.ticksExisted * 2;
        PortalFrontage.rotateAngleZ = entityIn.ticksExisted / 2F;
        PortalFrontage2.rotateAngleZ = -entityIn.ticksExisted / 2F;
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        PortalMain.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}

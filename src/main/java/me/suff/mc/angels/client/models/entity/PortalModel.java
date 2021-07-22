package me.suff.mc.angels.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.common.entities.AnomalyEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class PortalModel extends EntityModel<AnomalyEntity> {

    private final ModelPart PortalMain;
    private final ModelPart PortalFrontage;
    private final ModelPart PortalFrontage2;

    public PortalModel() {
        texWidth = 64;
        texHeight = 64;

        PortalMain = new ModelPart(this);
        PortalMain.setPos(0.0F, 8.0F, 0.0F);
        PortalMain.texOffs(0, 0).addBox(-16.0F, -16.0F, 0.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage = new ModelPart(this);
        PortalFrontage.setPos(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage, 0.0F, 0.0F, 0.7854F);
        PortalFrontage.texOffs(0, 32).addBox(-16.0F, -16.0F, -1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage2 = new ModelPart(this);
        PortalFrontage2.setPos(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage2, 0.0F, 0.0F, -0.7854F);
        PortalFrontage2.texOffs(0, 32).addBox(-16.0F, -16.0F, 1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

    }

    @Override
    public void setupAnim(AnomalyEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PortalMain.zRot = -entityIn.tickCount * 2;
        PortalFrontage.zRot = entityIn.tickCount / 2F;
        PortalFrontage2.zRot = -entityIn.tickCount / 2F;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        PortalMain.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
        PortalFrontage2.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}

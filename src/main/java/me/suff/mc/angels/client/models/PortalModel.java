package me.suff.mc.angels.client.models;

import me.suff.mc.angels.common.entity.PortalEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class PortalModel extends EntityModel< PortalEntity > {
    private final ModelPart PortalMain;
    private final ModelPart PortalFrontage;
    private final ModelPart PortalFrontage2;

    public PortalModel() {
        textureWidth = 64;
        textureHeight = 64;
        PortalMain = new ModelPart(this);
        PortalMain.setPivot(0.0F, 8.0F, 0.0F);
        PortalMain.setTextureOffset(0, 0).addCuboid(-16.0F, -16.0F, 0.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage = new ModelPart(this);
        PortalFrontage.setPivot(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage, 0.0F, 0.0F, 0.7854F);
        PortalFrontage.setTextureOffset(0, 32).addCuboid(-16.0F, -16.0F, -1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);

        PortalFrontage2 = new ModelPart(this);
        PortalFrontage2.setPivot(0.0F, 8.0F, 0.0F);
        setRotationAngle(PortalFrontage2, 0.0F, 0.0F, -0.7854F);
        PortalFrontage2.setTextureOffset(0, 32).addCuboid(-16.0F, -16.0F, 1.0F, 32.0F, 32.0F, 0.0F, 0.0F, false);
    }

    @Override
    public void setAngles(PortalEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        PortalMain.roll = -entityIn.age * 2;
        PortalFrontage.roll = entityIn.age / 2F;
        PortalFrontage2.roll = -entityIn.age / 2F;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        PortalMain.render(matrixStack, buffer, packedLight, packedOverlay);
        PortalFrontage.render(matrixStack, buffer, packedLight, packedOverlay);
        PortalFrontage2.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }

}
package me.suff.mc.angels.client.models.block;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

public class SnowArmModel extends EntityModel<Entity> {
    private final ModelPart rightArm;

    public SnowArmModel() {
        texWidth = 128;
        texHeight = 128;

        rightArm = new ModelPart(this);
        rightArm.setPos(0.0F, 16.0F, -4.0F);
        setRotationAngle(rightArm, 0.3927F, 0.0F, 0.0F);
        rightArm.texOffs(10, 59).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        //previously the render function, render code was moved to a method below
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
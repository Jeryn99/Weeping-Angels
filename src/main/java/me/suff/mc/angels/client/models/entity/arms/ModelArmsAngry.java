package me.suff.mc.angels.client.models.entity.arms;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class ModelArmsAngry extends EntityModel<WeepingAngelEntity> {

    ModelPart RightArm2;
    ModelPart RightFinger1;
    ModelPart RightHand;
    ModelPart RightFinger2;
    ModelPart RightThumb;
    ModelPart RightPalm;
    ModelPart RightArm1;
    ModelPart RightArm3;
    ModelPart LeftArm1;
    ModelPart LeftArm3;
    ModelPart LeftArm2;
    ModelPart LeftPalm;
    ModelPart LeftFinger1;
    ModelPart LeftFinger2;
    ModelPart LeftHand;
    ModelPart LeftThumb;

    public ModelArmsAngry() {
        texWidth = 128;
        texHeight = 128;

        RightArm2 = new ModelPart(this, 85, 34);
        RightArm2.addBox(-1F, -0.5F, 0.5F, 2, 7, 1);
        RightArm2.setPos(-4.5F, 5.5F, -7F);
        RightArm2.setTexSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0.8726646F, 0F, 0F);
        RightFinger1 = new ModelPart(this, 85, 29);
        RightFinger1.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        RightFinger1.setPos(-3.5F, 3.5F, -7F);
        RightFinger1.setTexSize(128, 128);
        RightFinger1.mirror = true;
        setRotation(RightFinger1, -0.0349066F, 0F, 0.1047198F);
        RightHand = new ModelPart(this, 85, 25);
        RightHand.addBox(-2.5F, 3F, -4F, 2, 2, 1);
        RightHand.setPos(-3F, 0.5F, -3F);
        RightHand.setTexSize(128, 128);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0F);
        RightFinger2 = new ModelPart(this, 85, 29);
        RightFinger2.addBox(0F, -1.5F, 0F, 1, 3, 1);
        RightFinger2.setPos(-5.5F, 3.5F, -7F);
        RightFinger2.setTexSize(128, 128);
        RightFinger2.mirror = true;
        setRotation(RightFinger2, -0.0349066F, 0F, -0.1047198F);
        RightThumb = new ModelPart(this, 87, 38);
        RightThumb.addBox(-1F, -2F, 0F, 1, 2, 1);
        RightThumb.setPos(-3.5F, 5F, -7F);
        RightThumb.setTexSize(128, 128);
        RightThumb.mirror = true;
        setRotation(RightThumb, -0.0872665F, 0F, 0.8203047F);
        RightPalm = new ModelPart(this, 85, 34);
        RightPalm.addBox(-1F, -2F, 0F, 2, 2, 1);
        RightPalm.setPos(-4.5F, 5.5F, -7F);
        RightPalm.setTexSize(128, 128);
        RightPalm.mirror = true;
        setRotation(RightPalm, 0.122173F, 0F, 0F);
        RightArm1 = new ModelPart(this, 85, 34);
        RightArm1.addBox(-1F, -6.5F, 4.5F, 2, 8, 2);
        RightArm1.setPos(-4.5F, 5.5F, -7F);
        RightArm1.setTexSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, -0.2617994F, 0F, 0F);
        RightArm3 = new ModelPart(this, 85, 34);
        RightArm3.addBox(-1F, 0F, 0F, 2, 6, 1);
        RightArm3.setPos(-4.5F, 5.5F, -7F);
        RightArm3.setTexSize(128, 128);
        RightArm3.mirror = true;
        setRotation(RightArm3, 0.8726646F, 0F, 0F);
        LeftArm1 = new ModelPart(this, 85, 34);
        LeftArm1.addBox(-1F, -6.5F, 4.5F, 2, 8, 2);
        LeftArm1.setPos(4.5F, 5.5F, -7F);
        LeftArm1.setTexSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, -0.2617994F, 0F, 0F);
        LeftArm3 = new ModelPart(this, 85, 34);
        LeftArm3.addBox(-1F, 0F, 0F, 2, 6, 1);
        LeftArm3.setPos(4.5F, 5.5F, -7F);
        LeftArm3.setTexSize(128, 128);
        LeftArm3.mirror = true;
        setRotation(LeftArm3, 0.8726646F, 0F, 0F);
        LeftArm2 = new ModelPart(this, 85, 34);
        LeftArm2.addBox(-1F, -0.5F, 0.5F, 2, 7, 1);
        LeftArm2.setPos(4.5F, 5.5F, -7F);
        LeftArm2.setTexSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 0.8726646F, 0F, 0F);
        LeftPalm = new ModelPart(this, 85, 34);
        LeftPalm.addBox(-1F, -2F, 0F, 2, 2, 1);
        LeftPalm.setPos(4.5F, 5.5F, -7F);
        LeftPalm.setTexSize(128, 128);
        LeftPalm.mirror = true;
        setRotation(LeftPalm, 0.122173F, 0F, 0F);
        LeftFinger1 = new ModelPart(this, 85, 29);
        LeftFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
        LeftFinger1.setPos(3.5F, 3.5F, -7F);
        LeftFinger1.setTexSize(128, 128);
        LeftFinger1.mirror = true;
        setRotation(LeftFinger1, -0.0349066F, 0F, -0.1047198F);
        LeftFinger2 = new ModelPart(this, 85, 29);
        LeftFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        LeftFinger2.setPos(5.5F, 3.5F, -7F);
        LeftFinger2.setTexSize(128, 128);
        LeftFinger2.mirror = true;
        setRotation(LeftFinger2, -0.0349066F, 0F, 0.1047198F);
        LeftHand = new ModelPart(this, 85, 25);
        LeftHand.addBox(-2.5F, 3F, -4F, 2, 2, 1);
        LeftHand.setPos(6F, 0.5F, -3F);
        LeftHand.setTexSize(128, 128);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, 0F);
        LeftThumb = new ModelPart(this, 87, 38);
        LeftThumb.addBox(0F, -2F, 0F, 1, 2, 1);
        LeftThumb.setPos(3.5F, 5F, -7F);
        LeftThumb.setTexSize(128, 128);
        LeftThumb.mirror = true;
        setRotation(LeftThumb, -0.0872665F, 0F, -0.8203047F);
    }

    @Override
    public void setupAnim(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {

    }

    private void setRotation(ModelPart model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightHand.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        RightPalm.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm3.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm3.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftPalm.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftHand.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftThumb.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}

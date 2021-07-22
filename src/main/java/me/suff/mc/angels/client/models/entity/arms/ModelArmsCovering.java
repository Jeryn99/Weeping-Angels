package me.suff.mc.angels.client.models.entity.arms;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;

public class ModelArmsCovering extends EntityModel<WeepingAngelEntity> {

    ModelPart RightFinger2;
    ModelPart RightArm2;
    ModelPart RightFinger1;
    ModelPart RightThumb;
    ModelPart RightHand;
    ModelPart RightArm1;
    ModelPart RightArmBottom;
    ModelPart RightArmMain;
    ModelPart LeftHand;
    ModelPart LeftFinger2;
    ModelPart LeftFinger1;
    ModelPart LeftThumb;
    ModelPart LeftArm1;
    ModelPart LeftArm2;
    ModelPart LeftArmMain;
    ModelPart LeftArmBottom;

    public ModelArmsCovering() {
        texWidth = 128;
        texHeight = 128;

        RightFinger2 = new ModelPart(this, 85, 29);
        RightFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        RightFinger2.setPos(-0.5F, -2.5F, -4F);
        RightFinger2.setTexSize(128, 128);
        RightFinger2.mirror = true;
        setRotation(RightFinger2, 0.0349066F, 0F, 0.1047198F);
        RightArm2 = new ModelPart(this, 85, 25);
        RightArm2.addBox(-2F, 0F, 0.5F, 2, 6, 1);
        RightArm2.setPos(-0.5F, -0.5F, -4F);
        RightArm2.setTexSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, -0.1745329F, 0F, 0.1919862F);
        RightFinger1 = new ModelPart(this, 85, 29);
        RightFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
        RightFinger1.setPos(-2.5F, -2.5F, -4F);
        RightFinger1.setTexSize(128, 128);
        RightFinger1.mirror = true;
        setRotation(RightFinger1, 0.0349066F, 0F, -0.1047198F);
        RightThumb = new ModelPart(this, 87, 38);
        RightThumb.addBox(-1F, -2F, 0F, 1, 2, 1);
        RightThumb.setPos(-2.5F, -1F, -4F);
        RightThumb.setTexSize(128, 128);
        RightThumb.mirror = true;
        setRotation(RightThumb, -0.0872665F, 0.6108652F, -0.1745329F);
        RightHand = new ModelPart(this, 85, 25);
        RightHand.addBox(-1F, 0F, 0F, 2, 2, 1);
        RightHand.setPos(-1.5F, -2.5F, -4F);
        RightHand.setTexSize(128, 128);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0F);
        RightArm1 = new ModelPart(this, 85, 25);
        RightArm1.addBox(-2F, 0F, 0F, 2, 6, 1);
        RightArm1.setPos(-0.5F, -0.5F, -4F);
        RightArm1.setTexSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, -0.1745329F, 0F, 0.1919862F);
        RightArmBottom = new ModelPart(this, 78, 25);
        RightArmBottom.addBox(0F, 5.2F, -1F, 2, 1, 2);
        RightArmBottom.setPos(-4.5F, 0.5F, 0F);
        RightArmBottom.setTexSize(128, 128);
        RightArmBottom.mirror = true;
        setRotation(RightArmBottom, -0.715585F, -0.2268928F, 0F);
        RightArmMain = new ModelPart(this, 62, 25);
        RightArmMain.addBox(0F, 0.5F, -1F, 2, 5, 2);
        RightArmMain.setPos(-4.5F, 0.5F, 0F);
        RightArmMain.setTexSize(128, 128);
        RightArmMain.mirror = true;
        setRotation(RightArmMain, -0.715585F, -0.2268928F, 0F);
        LeftHand = new ModelPart(this, 85, 25);
        LeftHand.addBox(-1F, 0F, 0F, 2, 2, 1);
        LeftHand.setPos(1.5F, -2.5F, -4F);
        LeftHand.setTexSize(128, 128);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, 0F);
        LeftFinger2 = new ModelPart(this, 85, 29);
        LeftFinger2.addBox(0F, -1.5F, 0F, 1, 3, 1);
        LeftFinger2.setPos(0.5F, -2.5F, -4F);
        LeftFinger2.setTexSize(128, 128);
        LeftFinger2.mirror = true;
        setRotation(LeftFinger2, 0.0349066F, 0F, -0.1047198F);
        LeftFinger1 = new ModelPart(this, 85, 29);
        LeftFinger1.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        LeftFinger1.setPos(2.5F, -2.5F, -4F);
        LeftFinger1.setTexSize(128, 128);
        LeftFinger1.mirror = true;
        setRotation(LeftFinger1, 0.0349066F, 0F, 0.1047198F);
        LeftThumb = new ModelPart(this, 87, 38);
        LeftThumb.addBox(0F, -2F, 0F, 1, 2, 1);
        LeftThumb.setPos(2.5F, -1F, -4F);
        LeftThumb.setTexSize(128, 128);
        LeftThumb.mirror = true;
        setRotation(LeftThumb, -0.0872665F, -0.6108652F, 0.1745329F);
        LeftArm1 = new ModelPart(this, 85, 25);
        LeftArm1.addBox(0F, 0F, 0F, 2, 6, 1);
        LeftArm1.setPos(0.5F, -0.5F, -4F);
        LeftArm1.setTexSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, -0.1745329F, 0F, -0.1919862F);
        LeftArm2 = new ModelPart(this, 85, 25);
        LeftArm2.addBox(0F, 0F, 0.5F, 2, 6, 1);
        LeftArm2.setPos(0.5F, -0.5F, -4F);
        LeftArm2.setTexSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, -0.1745329F, 0F, -0.1919862F);
        LeftArmMain = new ModelPart(this, 62, 25);
        LeftArmMain.addBox(-2F, 0.5F, -1F, 2, 5, 2);
        LeftArmMain.setPos(4.5F, 0.5F, 0F);
        LeftArmMain.setTexSize(128, 128);
        LeftArmMain.mirror = true;
        setRotation(LeftArmMain, -0.715585F, 0.2268928F, 0F);
        LeftArmBottom = new ModelPart(this, 78, 25);
        LeftArmBottom.addBox(-2F, 5.2F, -1F, 2, 1, 2);
        LeftArmBottom.setPos(4.5F, 0.5F, 0F);
        LeftArmBottom.setTexSize(128, 128);
        LeftArmBottom.mirror = true;
        setRotation(LeftArmBottom, -0.715585F, 0.2268928F, 0F);
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
        RightFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        RightHand.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArmBottom.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArmMain.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftHand.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArmMain.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArmBottom.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}

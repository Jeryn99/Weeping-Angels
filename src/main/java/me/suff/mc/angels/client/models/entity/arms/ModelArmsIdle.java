package me.suff.mc.angels.client.models.entity.arms;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmsIdle extends EntityModel<Entity> {

    ModelRenderer RightPalm;
    ModelRenderer RightFinger2;
    ModelRenderer RightArm2;
    ModelRenderer RightFinger;
    ModelRenderer RightFinger1;
    ModelRenderer LeftArm1;
    ModelRenderer LeftFinger1;
    ModelRenderer RightHand;
    ModelRenderer LeftHand;
    ModelRenderer LeftThumb;
    ModelRenderer LeftFinger2;
    ModelRenderer LeftPalm;
    ModelRenderer LeftArm2;
    ModelRenderer RightArm1;

    public ModelArmsIdle() {
        texWidth = 128;
        texHeight = 128;

        RightPalm = new ModelRenderer(this, 22, 25);
        RightPalm.addBox(-0.5F, -2F, -1F, 1, 2, 2);
        RightPalm.setPos(-4.5F, 13.5F, 0F);
        RightPalm.setTexSize(128, 128);
        RightPalm.mirror = true;
        setRotation(RightPalm, 0F, 0F, -0.0872665F);
        RightFinger2 = new ModelRenderer(this, 20, 29);
        RightFinger2.addBox(0F, -1.5F, -1F, 1, 3, 1);
        RightFinger2.setPos(-5.5F, 13.5F, 1F);
        RightFinger2.setTexSize(128, 128);
        RightFinger2.mirror = true;
        setRotation(RightFinger2, 0.1047198F, 0F, 0.0349066F);
        RightArm2 = new ModelRenderer(this, 28, 25);
        RightArm2.addBox(0.5F, -11F, -1F, 1, 11, 2);
        RightArm2.setPos(-5.5F, 11.5F, 0F);
        RightArm2.setTexSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0F, 0F, 0.0698132F);
        RightFinger = new ModelRenderer(this, 24, 29);
        RightFinger.addBox(0F, 0F, 0F, 1, 2, 1);
        RightFinger.setPos(-5.5F, 12.5F, -1F);
        RightFinger.setTexSize(128, 128);
        RightFinger.mirror = true;
        setRotation(RightFinger, -0.7853982F, 0F, -0.7853982F);
        RightFinger1 = new ModelRenderer(this, 20, 29);
        RightFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
        RightFinger1.setPos(-5.5F, 13.5F, -1F);
        RightFinger1.setTexSize(128, 128);
        RightFinger1.mirror = true;
        setRotation(RightFinger1, -0.1047198F, 0F, 0.0349066F);
        LeftArm1 = new ModelRenderer(this, 62, 25);
        LeftArm1.addBox(-1F, -11F, -1F, 1, 11, 2);
        LeftArm1.setPos(5.5F, 11.5F, 0F);
        LeftArm1.setTexSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, 0F, 0F, -0.0698132F);
        LeftFinger1 = new ModelRenderer(this, 76, 29);
        LeftFinger1.addBox(-1F, -1.5F, -1F, 1, 3, 1);
        LeftFinger1.setPos(5.5F, 13.5F, 1F);
        LeftFinger1.setTexSize(128, 128);
        LeftFinger1.mirror = true;
        setRotation(LeftFinger1, 0.1047198F, 0F, -0.0349066F);
        RightHand = new ModelRenderer(this, 16, 25);
        RightHand.addBox(-1.5F, 9F, -1F, 1, 2, 2);
        RightHand.setPos(-4F, 2.5F, 0F);
        RightHand.setTexSize(128, 128);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0F);
        LeftHand = new ModelRenderer(this, 78, 25);
        LeftHand.addBox(0.5F, 9F, -1F, 1, 2, 2);
        LeftHand.setPos(4F, 2.5F, 0F);
        LeftHand.setTexSize(128, 128);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, 0F);
        LeftThumb = new ModelRenderer(this, 72, 29);
        LeftThumb.addBox(-1F, 0F, 0F, 1, 2, 1);
        LeftThumb.setPos(5.5F, 12.5F, -1F);
        LeftThumb.setTexSize(128, 128);
        LeftThumb.mirror = true;
        setRotation(LeftThumb, -0.7853982F, 0F, 0.7853982F);
        LeftFinger2 = new ModelRenderer(this, 76, 29);
        LeftFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        LeftFinger2.setPos(5.5F, 13.5F, -1F);
        LeftFinger2.setTexSize(128, 128);
        LeftFinger2.mirror = true;
        setRotation(LeftFinger2, -0.1047198F, 0F, -0.0349066F);
        LeftPalm = new ModelRenderer(this, 72, 25);
        LeftPalm.addBox(-0.5F, -2F, -1F, 1, 2, 2);
        LeftPalm.setPos(4.5F, 13.5F, 0F);
        LeftPalm.setTexSize(128, 128);
        LeftPalm.mirror = true;
        setRotation(LeftPalm, 0F, 0F, 0.0872665F);
        LeftArm2 = new ModelRenderer(this, 62, 25);
        LeftArm2.addBox(-1.5F, -11F, -1F, 1, 11, 2);
        LeftArm2.setPos(5.5F, 11.5F, 0F);
        LeftArm2.setTexSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 0F, 0F, -0.0698132F);
        RightArm1 = new ModelRenderer(this, 28, 25);
        RightArm1.addBox(0F, -11F, -1F, 1, 11, 2);
        RightArm1.setPos(-5.5F, 11.5F, 0F);
        RightArm1.setTexSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, 0F, 0F, 0.0698132F);
    }

    @Override
    public void setupAnim(Entity entity, float v, float v1, float v2, float v3, float v4) {

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightPalm.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightHand.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftHand.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftPalm.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm1.render(matrixStack, buffer, packedLight, packedOverlay);
    }
}

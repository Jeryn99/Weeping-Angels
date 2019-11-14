package me.swirtzly.angels.client.models.entity.arms;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmsIdle extends ModelBase {

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
        textureWidth = 128;
        textureHeight = 128;

        RightPalm = new ModelRenderer(this, 22, 25);
        RightPalm.addBox(-0.5F, -2F, -1F, 1, 2, 2);
        RightPalm.setRotationPoint(-4.5F, 13.5F, 0F);
        RightPalm.setTextureSize(128, 128);
        RightPalm.mirror = true;
        setRotation(RightPalm, 0F, 0F, -0.0872665F);
        RightFinger2 = new ModelRenderer(this, 20, 29);
        RightFinger2.addBox(0F, -1.5F, -1F, 1, 3, 1);
        RightFinger2.setRotationPoint(-5.5F, 13.5F, 1F);
        RightFinger2.setTextureSize(128, 128);
        RightFinger2.mirror = true;
        setRotation(RightFinger2, 0.1047198F, 0F, 0.0349066F);
        RightArm2 = new ModelRenderer(this, 28, 25);
        RightArm2.addBox(0.5F, -11F, -1F, 1, 11, 2);
        RightArm2.setRotationPoint(-5.5F, 11.5F, 0F);
        RightArm2.setTextureSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0F, 0F, 0.0698132F);
        RightFinger = new ModelRenderer(this, 24, 29);
        RightFinger.addBox(0F, 0F, 0F, 1, 2, 1);
        RightFinger.setRotationPoint(-5.5F, 12.5F, -1F);
        RightFinger.setTextureSize(128, 128);
        RightFinger.mirror = true;
        setRotation(RightFinger, -0.7853982F, 0F, -0.7853982F);
        RightFinger1 = new ModelRenderer(this, 20, 29);
        RightFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
        RightFinger1.setRotationPoint(-5.5F, 13.5F, -1F);
        RightFinger1.setTextureSize(128, 128);
        RightFinger1.mirror = true;
        setRotation(RightFinger1, -0.1047198F, 0F, 0.0349066F);
        LeftArm1 = new ModelRenderer(this, 62, 25);
        LeftArm1.addBox(-1F, -11F, -1F, 1, 11, 2);
        LeftArm1.setRotationPoint(5.5F, 11.5F, 0F);
        LeftArm1.setTextureSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, 0F, 0F, -0.0698132F);
        LeftFinger1 = new ModelRenderer(this, 76, 29);
        LeftFinger1.addBox(-1F, -1.5F, -1F, 1, 3, 1);
        LeftFinger1.setRotationPoint(5.5F, 13.5F, 1F);
        LeftFinger1.setTextureSize(128, 128);
        LeftFinger1.mirror = true;
        setRotation(LeftFinger1, 0.1047198F, 0F, -0.0349066F);
        RightHand = new ModelRenderer(this, 16, 25);
        RightHand.addBox(-1.5F, 9F, -1F, 1, 2, 2);
        RightHand.setRotationPoint(-4F, 2.5F, 0F);
        RightHand.setTextureSize(128, 128);
        RightHand.mirror = true;
        setRotation(RightHand, 0F, 0F, 0F);
        LeftHand = new ModelRenderer(this, 78, 25);
        LeftHand.addBox(0.5F, 9F, -1F, 1, 2, 2);
        LeftHand.setRotationPoint(4F, 2.5F, 0F);
        LeftHand.setTextureSize(128, 128);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, 0F);
        LeftThumb = new ModelRenderer(this, 72, 29);
        LeftThumb.addBox(-1F, 0F, 0F, 1, 2, 1);
        LeftThumb.setRotationPoint(5.5F, 12.5F, -1F);
        LeftThumb.setTextureSize(128, 128);
        LeftThumb.mirror = true;
        setRotation(LeftThumb, -0.7853982F, 0F, 0.7853982F);
        LeftFinger2 = new ModelRenderer(this, 76, 29);
        LeftFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        LeftFinger2.setRotationPoint(5.5F, 13.5F, -1F);
        LeftFinger2.setTextureSize(128, 128);
        LeftFinger2.mirror = true;
        setRotation(LeftFinger2, -0.1047198F, 0F, -0.0349066F);
        LeftPalm = new ModelRenderer(this, 72, 25);
        LeftPalm.addBox(-0.5F, -2F, -1F, 1, 2, 2);
        LeftPalm.setRotationPoint(4.5F, 13.5F, 0F);
        LeftPalm.setTextureSize(128, 128);
        LeftPalm.mirror = true;
        setRotation(LeftPalm, 0F, 0F, 0.0872665F);
        LeftArm2 = new ModelRenderer(this, 62, 25);
        LeftArm2.addBox(-1.5F, -11F, -1F, 1, 11, 2);
        LeftArm2.setRotationPoint(5.5F, 11.5F, 0F);
        LeftArm2.setTextureSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 0F, 0F, -0.0698132F);
        RightArm1 = new ModelRenderer(this, 28, 25);
        RightArm1.addBox(0F, -11F, -1F, 1, 11, 2);
        RightArm1.setRotationPoint(-5.5F, 11.5F, 0F);
        RightArm1.setTextureSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, 0F, 0F, 0.0698132F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        RightPalm.render(scale);
        RightFinger2.render(scale);
        RightArm2.render(scale);
        RightFinger.render(scale);
        RightFinger1.render(scale);
        LeftArm1.render(scale);
        LeftFinger1.render(scale);
        RightHand.render(scale);
        LeftHand.render(scale);
        LeftThumb.render(scale);
        LeftFinger2.render(scale);
        LeftPalm.render(scale);
        LeftArm2.render(scale);
        RightArm1.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}

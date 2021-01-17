package me.swirtzly.minecraft.angels.client.models.entity.arms;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmsPointing extends EntityModel<Entity> {

    ModelRenderer RightArm2;
    ModelRenderer RightFinger1;
    ModelRenderer LeftFinger2;
    ModelRenderer LeftHand;
    ModelRenderer LeftFinger1;
    ModelRenderer RightFinger2;
    ModelRenderer RightThumb;
    ModelRenderer LeftThumb;
    ModelRenderer LeftPalm;
    ModelRenderer RightHand;
    ModelRenderer RightArm1;
    ModelRenderer RightArm3;
    ModelRenderer LeftArm3;
    ModelRenderer LeftArm2;
    ModelRenderer LeftArm1;

    public ModelArmsPointing() {
        textureWidth = 128;
        textureHeight = 128;

        RightArm2 = new ModelRenderer(this, 85, 34);
        RightArm2.addBox(-1F, -0.5F, 0.5F, 2, 7, 1);
        RightArm2.setRotationPoint(-4.5F, 5.5F, -7F);
        RightArm2.setTextureSize(128, 128);
        RightArm2.mirror = true;
        setRotation(RightArm2, 0.8726646F, 0F, 0F);
        RightFinger1 = new ModelRenderer(this, 85, 29);
        RightFinger1.addBox(-1F, -1.5F, 0F, 1, 3, 1);
        RightFinger1.setRotationPoint(-3.5F, 3.5F, -7F);
        RightFinger1.setTextureSize(128, 128);
        RightFinger1.mirror = true;
        setRotation(RightFinger1, 0.1919862F, 0F, 0.1047198F);
        LeftFinger2 = new ModelRenderer(this, 85, 29);
        LeftFinger2.addBox(-1F, 0F, -2F, 1, 1, 2);
        LeftFinger2.setRotationPoint(5.5F, 3.5F, -11F);
        LeftFinger2.setTextureSize(128, 128);
        LeftFinger2.mirror = true;
        setRotation(LeftFinger2, 0.8203047F, 0F, 0F);
        LeftHand = new ModelRenderer(this, 85, 25);
        LeftHand.addBox(-2.5F, 7F, -6F, 2, 1, 2);
        LeftHand.setRotationPoint(6F, -3.5F, -5F);
        LeftHand.setTextureSize(128, 128);
        LeftHand.mirror = true;
        setRotation(LeftHand, 0F, 0F, 0F);
        LeftFinger1 = new ModelRenderer(this, 85, 29);
        LeftFinger1.addBox(0F, 0F, -1.5F, 1, 1, 3);
        LeftFinger1.setRotationPoint(3.5F, 3.5F, -11F);
        LeftFinger1.setTextureSize(128, 128);
        LeftFinger1.mirror = true;
        setRotation(LeftFinger1, -0.2617994F, 0.1047198F, 0F);
        RightFinger2 = new ModelRenderer(this, 85, 29);
        RightFinger2.addBox(0F, -1.5F, 0F, 1, 3, 1);
        RightFinger2.setRotationPoint(-5.5F, 3.5F, -7F);
        RightFinger2.setTextureSize(128, 128);
        RightFinger2.mirror = true;
        setRotation(RightFinger2, 0.1919862F, 0F, -0.1047198F);
        RightThumb = new ModelRenderer(this, 87, 38);
        RightThumb.addBox(-1F, -1.5F, 0F, 1, 2, 1);
        RightThumb.setRotationPoint(-3.5F, 5F, -7F);
        RightThumb.setTextureSize(128, 128);
        RightThumb.mirror = true;
        setRotation(RightThumb, 0.8203047F, 0F, 0.8203047F);
        LeftThumb = new ModelRenderer(this, 87, 38);
        LeftThumb.addBox(0F, 0F, 0F, 1, 2, 1);
        LeftThumb.setRotationPoint(3.5F, 3.5F, -10F);
        LeftThumb.setTextureSize(128, 128);
        LeftThumb.mirror = true;
        setRotation(LeftThumb, -0.7853982F, 0.0872665F, 0.7853982F);
        LeftPalm = new ModelRenderer(this, 85, 34);
        LeftPalm.addBox(-1F, -1F, 0F, 2, 1, 2);
        LeftPalm.setRotationPoint(4.5F, 5F, -11F);
        LeftPalm.setTextureSize(128, 128);
        LeftPalm.mirror = true;
        setRotation(LeftPalm, 0.0872665F, 0F, 0F);
        RightHand = new ModelRenderer(this, 85, 34);
        RightHand.addBox(-1F, -2F, 0F, 2, 2, 1);
        RightHand.setRotationPoint(-4.5F, 5.5F, -7F);
        RightHand.setTextureSize(128, 128);
        RightHand.mirror = true;
        setRotation(RightHand, 0.122173F, 0F, 0F);
        RightArm1 = new ModelRenderer(this, 85, 34);
        RightArm1.addBox(-1F, -6.5F, 4.5F, 2, 8, 2);
        RightArm1.setRotationPoint(-4.5F, 5.5F, -7F);
        RightArm1.setTextureSize(128, 128);
        RightArm1.mirror = true;
        setRotation(RightArm1, -0.2617994F, 0F, 0F);
        RightArm3 = new ModelRenderer(this, 85, 34);
        RightArm3.addBox(-1F, 0F, 0F, 2, 6, 1);
        RightArm3.setRotationPoint(-4.5F, 5.5F, -7F);
        RightArm3.setTextureSize(128, 128);
        RightArm3.mirror = true;
        setRotation(RightArm3, 0.8726646F, 0F, 0F);
        LeftArm3 = new ModelRenderer(this, 85, 34);
        LeftArm3.addBox(-1F, 0F, -1.5F, 2, 8, 1);
        LeftArm3.setRotationPoint(4.5F, 3.5F, -9F);
        LeftArm3.setTextureSize(128, 128);
        LeftArm3.mirror = true;
        setRotation(LeftArm3, 1.396263F, 0F, 0F);
        LeftArm2 = new ModelRenderer(this, 85, 34);
        LeftArm2.addBox(-1F, 0F, -1F, 2, 7, 1);
        LeftArm2.setRotationPoint(4.5F, 3.5F, -9F);
        LeftArm2.setTextureSize(128, 128);
        LeftArm2.mirror = true;
        setRotation(LeftArm2, 1.396263F, 0F, 0F);
        LeftArm1 = new ModelRenderer(this, 85, 34);
        LeftArm1.addBox(-1F, -6.5F, 6.5F, 2, 6, 2);
        LeftArm1.setRotationPoint(4.5F, 3.5F, -9F);
        LeftArm1.setTextureSize(128, 128);
        LeftArm1.mirror = true;
        setRotation(LeftArm1, -0.4363323F, 0F, 0F);
    }

    @Override
    public void setRotationAngles(Entity entity, float v, float v1, float v2, float v3, float v4) {

    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        RightArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftHand.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftFinger1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightFinger2.render(matrixStack, buffer, packedLight, packedOverlay);
        RightThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftThumb.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftPalm.render(matrixStack, buffer, packedLight, packedOverlay);
        RightHand.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm1.render(matrixStack, buffer, packedLight, packedOverlay);
        RightArm3.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm3.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm2.render(matrixStack, buffer, packedLight, packedOverlay);
        LeftArm1.render(matrixStack, buffer, packedLight, packedOverlay);
    }

}

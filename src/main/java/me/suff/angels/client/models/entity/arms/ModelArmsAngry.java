package me.suff.angels.client.models.entity.arms;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmsAngry extends ModelBase {
	
	ModelRenderer RightArm2;
	ModelRenderer RightFinger1;
	ModelRenderer RightHand;
	ModelRenderer RightFinger2;
	ModelRenderer RightThumb;
	ModelRenderer RightPalm;
	ModelRenderer RightArm1;
	ModelRenderer RightArm3;
	ModelRenderer LeftArm1;
	ModelRenderer LeftArm3;
	ModelRenderer LeftArm2;
	ModelRenderer LeftPalm;
	ModelRenderer LeftFinger1;
	ModelRenderer LeftFinger2;
	ModelRenderer LeftHand;
	ModelRenderer LeftThumb;
	
	public ModelArmsAngry() {
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
		setRotation(RightFinger1, -0.0349066F, 0F, 0.1047198F);
		RightHand = new ModelRenderer(this, 85, 25);
		RightHand.addBox(-2.5F, 3F, -4F, 2, 2, 1);
		RightHand.setRotationPoint(-3F, 0.5F, -3F);
		RightHand.setTextureSize(128, 128);
		RightHand.mirror = true;
		setRotation(RightHand, 0F, 0F, 0F);
		RightFinger2 = new ModelRenderer(this, 85, 29);
		RightFinger2.addBox(0F, -1.5F, 0F, 1, 3, 1);
		RightFinger2.setRotationPoint(-5.5F, 3.5F, -7F);
		RightFinger2.setTextureSize(128, 128);
		RightFinger2.mirror = true;
		setRotation(RightFinger2, -0.0349066F, 0F, -0.1047198F);
		RightThumb = new ModelRenderer(this, 87, 38);
		RightThumb.addBox(-1F, -2F, 0F, 1, 2, 1);
		RightThumb.setRotationPoint(-3.5F, 5F, -7F);
		RightThumb.setTextureSize(128, 128);
		RightThumb.mirror = true;
		setRotation(RightThumb, -0.0872665F, 0F, 0.8203047F);
		RightPalm = new ModelRenderer(this, 85, 34);
		RightPalm.addBox(-1F, -2F, 0F, 2, 2, 1);
		RightPalm.setRotationPoint(-4.5F, 5.5F, -7F);
		RightPalm.setTextureSize(128, 128);
		RightPalm.mirror = true;
		setRotation(RightPalm, 0.122173F, 0F, 0F);
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
		LeftArm1 = new ModelRenderer(this, 85, 34);
		LeftArm1.addBox(-1F, -6.5F, 4.5F, 2, 8, 2);
		LeftArm1.setRotationPoint(4.5F, 5.5F, -7F);
		LeftArm1.setTextureSize(128, 128);
		LeftArm1.mirror = true;
		setRotation(LeftArm1, -0.2617994F, 0F, 0F);
		LeftArm3 = new ModelRenderer(this, 85, 34);
		LeftArm3.addBox(-1F, 0F, 0F, 2, 6, 1);
		LeftArm3.setRotationPoint(4.5F, 5.5F, -7F);
		LeftArm3.setTextureSize(128, 128);
		LeftArm3.mirror = true;
		setRotation(LeftArm3, 0.8726646F, 0F, 0F);
		LeftArm2 = new ModelRenderer(this, 85, 34);
		LeftArm2.addBox(-1F, -0.5F, 0.5F, 2, 7, 1);
		LeftArm2.setRotationPoint(4.5F, 5.5F, -7F);
		LeftArm2.setTextureSize(128, 128);
		LeftArm2.mirror = true;
		setRotation(LeftArm2, 0.8726646F, 0F, 0F);
		LeftPalm = new ModelRenderer(this, 85, 34);
		LeftPalm.addBox(-1F, -2F, 0F, 2, 2, 1);
		LeftPalm.setRotationPoint(4.5F, 5.5F, -7F);
		LeftPalm.setTextureSize(128, 128);
		LeftPalm.mirror = true;
		setRotation(LeftPalm, 0.122173F, 0F, 0F);
		LeftFinger1 = new ModelRenderer(this, 85, 29);
		LeftFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
		LeftFinger1.setRotationPoint(3.5F, 3.5F, -7F);
		LeftFinger1.setTextureSize(128, 128);
		LeftFinger1.mirror = true;
		setRotation(LeftFinger1, -0.0349066F, 0F, -0.1047198F);
		LeftFinger2 = new ModelRenderer(this, 85, 29);
		LeftFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
		LeftFinger2.setRotationPoint(5.5F, 3.5F, -7F);
		LeftFinger2.setTextureSize(128, 128);
		LeftFinger2.mirror = true;
		setRotation(LeftFinger2, -0.0349066F, 0F, 0.1047198F);
		LeftHand = new ModelRenderer(this, 85, 25);
		LeftHand.addBox(-2.5F, 3F, -4F, 2, 2, 1);
		LeftHand.setRotationPoint(6F, 0.5F, -3F);
		LeftHand.setTextureSize(128, 128);
		LeftHand.mirror = true;
		setRotation(LeftHand, 0F, 0F, 0F);
		LeftThumb = new ModelRenderer(this, 87, 38);
		LeftThumb.addBox(0F, -2F, 0F, 1, 2, 1);
		LeftThumb.setRotationPoint(3.5F, 5F, -7F);
		LeftThumb.setTextureSize(128, 128);
		LeftThumb.mirror = true;
		setRotation(LeftThumb, -0.0872665F, 0F, -0.8203047F);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		RightArm2.render(scale);
		RightFinger1.render(scale);
		RightHand.render(scale);
		RightFinger2.render(scale);
		RightThumb.render(scale);
		RightPalm.render(scale);
		RightArm1.render(scale);
		RightArm3.render(scale);
		LeftArm1.render(scale);
		LeftArm3.render(scale);
		LeftArm2.render(scale);
		LeftPalm.render(scale);
		LeftFinger1.render(scale);
		LeftFinger2.render(scale);
		LeftHand.render(scale);
		LeftThumb.render(scale);
	}
}

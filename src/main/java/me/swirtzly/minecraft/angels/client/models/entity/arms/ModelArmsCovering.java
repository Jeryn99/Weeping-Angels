package me.swirtzly.minecraft.angels.client.models.entity.arms;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelArmsCovering extends EntityModel<WeepingAngelEntity> {
	
	ModelRenderer RightFinger2;
	ModelRenderer RightArm2;
	ModelRenderer RightFinger1;
	ModelRenderer RightThumb;
	ModelRenderer RightHand;
	ModelRenderer RightArm1;
	ModelRenderer RightArmBottom;
	ModelRenderer RightArmMain;
	ModelRenderer LeftHand;
	ModelRenderer LeftFinger2;
	ModelRenderer LeftFinger1;
	ModelRenderer LeftThumb;
	ModelRenderer LeftArm1;
	ModelRenderer LeftArm2;
	ModelRenderer LeftArmMain;
	ModelRenderer LeftArmBottom;
	
	public ModelArmsCovering() {
		textureWidth = 128;
		textureHeight = 128;
		
		RightFinger2 = new ModelRenderer(this, 85, 29);
		RightFinger2.addBox(-1F, -1.5F, 0F, 1, 3, 1);
		RightFinger2.setRotationPoint(-0.5F, -2.5F, -4F);
		RightFinger2.setTextureSize(128, 128);
		RightFinger2.mirror = true;
		setRotation(RightFinger2, 0.0349066F, 0F, 0.1047198F);
		RightArm2 = new ModelRenderer(this, 85, 25);
		RightArm2.addBox(-2F, 0F, 0.5F, 2, 6, 1);
		RightArm2.setRotationPoint(-0.5F, -0.5F, -4F);
		RightArm2.setTextureSize(128, 128);
		RightArm2.mirror = true;
		setRotation(RightArm2, -0.1745329F, 0F, 0.1919862F);
		RightFinger1 = new ModelRenderer(this, 85, 29);
		RightFinger1.addBox(0F, -1.5F, 0F, 1, 3, 1);
		RightFinger1.setRotationPoint(-2.5F, -2.5F, -4F);
		RightFinger1.setTextureSize(128, 128);
		RightFinger1.mirror = true;
		setRotation(RightFinger1, 0.0349066F, 0F, -0.1047198F);
		RightThumb = new ModelRenderer(this, 87, 38);
		RightThumb.addBox(-1F, -2F, 0F, 1, 2, 1);
		RightThumb.setRotationPoint(-2.5F, -1F, -4F);
		RightThumb.setTextureSize(128, 128);
		RightThumb.mirror = true;
		setRotation(RightThumb, -0.0872665F, 0.6108652F, -0.1745329F);
		RightHand = new ModelRenderer(this, 85, 25);
		RightHand.addBox(-1F, 0F, 0F, 2, 2, 1);
		RightHand.setRotationPoint(-1.5F, -2.5F, -4F);
		RightHand.setTextureSize(128, 128);
		RightHand.mirror = true;
		setRotation(RightHand, 0F, 0F, 0F);
		RightArm1 = new ModelRenderer(this, 85, 25);
		RightArm1.addBox(-2F, 0F, 0F, 2, 6, 1);
		RightArm1.setRotationPoint(-0.5F, -0.5F, -4F);
		RightArm1.setTextureSize(128, 128);
		RightArm1.mirror = true;
		setRotation(RightArm1, -0.1745329F, 0F, 0.1919862F);
		RightArmBottom = new ModelRenderer(this, 78, 25);
		RightArmBottom.addBox(0F, 5.2F, -1F, 2, 1, 2);
		RightArmBottom.setRotationPoint(-4.5F, 0.5F, 0F);
		RightArmBottom.setTextureSize(128, 128);
		RightArmBottom.mirror = true;
		setRotation(RightArmBottom, -0.715585F, -0.2268928F, 0F);
		RightArmMain = new ModelRenderer(this, 62, 25);
		RightArmMain.addBox(0F, 0.5F, -1F, 2, 5, 2);
		RightArmMain.setRotationPoint(-4.5F, 0.5F, 0F);
		RightArmMain.setTextureSize(128, 128);
		RightArmMain.mirror = true;
		setRotation(RightArmMain, -0.715585F, -0.2268928F, 0F);
		LeftHand = new ModelRenderer(this, 85, 25);
		LeftHand.addBox(-1F, 0F, 0F, 2, 2, 1);
		LeftHand.setRotationPoint(1.5F, -2.5F, -4F);
		LeftHand.setTextureSize(128, 128);
		LeftHand.mirror = true;
		setRotation(LeftHand, 0F, 0F, 0F);
		LeftFinger2 = new ModelRenderer(this, 85, 29);
		LeftFinger2.addBox(0F, -1.5F, 0F, 1, 3, 1);
		LeftFinger2.setRotationPoint(0.5F, -2.5F, -4F);
		LeftFinger2.setTextureSize(128, 128);
		LeftFinger2.mirror = true;
		setRotation(LeftFinger2, 0.0349066F, 0F, -0.1047198F);
		LeftFinger1 = new ModelRenderer(this, 85, 29);
		LeftFinger1.addBox(-1F, -1.5F, 0F, 1, 3, 1);
		LeftFinger1.setRotationPoint(2.5F, -2.5F, -4F);
		LeftFinger1.setTextureSize(128, 128);
		LeftFinger1.mirror = true;
		setRotation(LeftFinger1, 0.0349066F, 0F, 0.1047198F);
		LeftThumb = new ModelRenderer(this, 87, 38);
		LeftThumb.addBox(0F, -2F, 0F, 1, 2, 1);
		LeftThumb.setRotationPoint(2.5F, -1F, -4F);
		LeftThumb.setTextureSize(128, 128);
		LeftThumb.mirror = true;
		setRotation(LeftThumb, -0.0872665F, -0.6108652F, 0.1745329F);
		LeftArm1 = new ModelRenderer(this, 85, 25);
		LeftArm1.addBox(0F, 0F, 0F, 2, 6, 1);
		LeftArm1.setRotationPoint(0.5F, -0.5F, -4F);
		LeftArm1.setTextureSize(128, 128);
		LeftArm1.mirror = true;
		setRotation(LeftArm1, -0.1745329F, 0F, -0.1919862F);
		LeftArm2 = new ModelRenderer(this, 85, 25);
		LeftArm2.addBox(0F, 0F, 0.5F, 2, 6, 1);
		LeftArm2.setRotationPoint(0.5F, -0.5F, -4F);
		LeftArm2.setTextureSize(128, 128);
		LeftArm2.mirror = true;
		setRotation(LeftArm2, -0.1745329F, 0F, -0.1919862F);
		LeftArmMain = new ModelRenderer(this, 62, 25);
		LeftArmMain.addBox(-2F, 0.5F, -1F, 2, 5, 2);
		LeftArmMain.setRotationPoint(4.5F, 0.5F, 0F);
		LeftArmMain.setTextureSize(128, 128);
		LeftArmMain.mirror = true;
		setRotation(LeftArmMain, -0.715585F, 0.2268928F, 0F);
		LeftArmBottom = new ModelRenderer(this, 78, 25);
		LeftArmBottom.addBox(-2F, 5.2F, -1F, 2, 1, 2);
		LeftArmBottom.setRotationPoint(4.5F, 0.5F, 0F);
		LeftArmBottom.setTextureSize(128, 128);
		LeftArmBottom.mirror = true;
		setRotation(LeftArmBottom, -0.715585F, 0.2268928F, 0F);
	}

	@Override
	public void setRotationAngles(WeepingAngelEntity weepingAngelEntity, float v, float v1, float v2, float v3, float v4) {
		
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
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

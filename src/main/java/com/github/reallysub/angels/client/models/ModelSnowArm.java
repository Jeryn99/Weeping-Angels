package com.github.reallysub.angels.client.models;

import com.github.reallysub.angels.main.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelSnowArm extends ModelBase {
	
	ResourceLocation TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	
	ModelRenderer RightArm1;
	ModelRenderer RightArm2;
	
	public ModelSnowArm() {
		textureWidth = 128;
		textureHeight = 128;
		
		RightArm1 = new ModelRenderer(this, 58, 16);
		RightArm1.addBox(-3F, -3F, 0F, 3, 3, 6);
		RightArm1.setRotationPoint(-4F, 21F, -4F);
		RightArm1.setTextureSize(128, 128);
		RightArm1.mirror = true;
		setRotation(RightArm1, -1.059177F, 0.42869F, 0F);
		RightArm2 = new ModelRenderer(this, 58, 25);
		RightArm2.addBox(-3F, -3F, -6F, 3, 3, 6);
		RightArm2.setRotationPoint(-4F, 21F, -4F);
		RightArm2.setTextureSize(128, 128);
		RightArm2.mirror = true;
		setRotation(RightArm2, -1.601779F, 0.42869F, 0F);
	}
	
	public void render(float scale) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(TEX);
		RightArm1.render(scale);
		RightArm2.render(scale);
		GlStateManager.popMatrix();
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
}

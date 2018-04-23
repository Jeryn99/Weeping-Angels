package com.github.reallysub.angels.client.models.entities;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelAngelChild extends ModelPlayer {
	
	public ModelAngelChild() {
		super(1.0F, true);
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
		GlStateManager.pushMatrix();
		
		if (entityIn instanceof EntityAngel) {
			EntityAngel angel = (EntityAngel) entityIn;
			if (angel.isChild()) {
				GlStateManager.scale(0.75F, 0.75F, 0.75F);
				GlStateManager.translate(0.0F, 16.0F * scale, 0.0F);
				this.bipedHead.render(scale);
				GlStateManager.popMatrix();
				GlStateManager.pushMatrix();
				GlStateManager.scale(0.5F, 0.5F, 0.5F);
				GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
				this.bipedBody.render(scale);
				this.bipedRightArm.render(scale);
				this.bipedLeftArm.render(scale);
				this.bipedRightLeg.render(scale);
				this.bipedLeftLeg.render(scale);
				this.bipedHeadwear.render(scale);
			}
		}
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		if (entityIn instanceof EntityAngel) {
			EntityAngel angel = (EntityAngel) entityIn;
			if (angel.getSeenTime() == 1) {
				super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
			}
		}
	}
	
}

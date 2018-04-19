package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;

public abstract class PoseBase {
	
	float limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress;
	
	public PoseBase() {}
	
	public PoseBase(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		this.limbSwing = limbSwing;
		this.limbSwingAmount = limbSwingAmount;
		this.ageInTicks = ageInTicks;
		this.netheadYaw = netheadYaw;
		this.headPitch = headPitch;
		this.swingProgress = swingProgress;
	}
	
	public abstract void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right);
	
	public abstract void setHeadAngles(ModelRenderer head);
	
	public abstract boolean angryFace(EntityAngel angel);
	
	public abstract void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing);
	
	public float getLimbSwing() {
		return limbSwing;
	}
	
	public float getLimbSwingAmount() {
		return limbSwingAmount;
	}
	
	public float getAgeInTicks() {
		return ageInTicks;
	}
	
	public float getHeadPitch() {
		return headPitch;
	}
	
	public float getNetheadYaw() {
		return netheadYaw;
	}
	
	public float getSwingProgress() {
		return swingProgress;
	}
}

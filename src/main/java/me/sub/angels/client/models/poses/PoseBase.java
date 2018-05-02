package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public abstract class PoseBase {
	
	float limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress;
	Entity entity;
	
	public PoseBase() {}
	
	public PoseBase(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		this.limbSwing = limbSwing;
		this.limbSwingAmount = limbSwingAmount;
		this.ageInTicks = ageInTicks;
		this.netheadYaw = netheadYaw;
		this.headPitch = headPitch;
		this.swingProgress = swingProgress;
		this.entity = entity;
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
	
	public Entity getAngel() {
		return entity;
	}
}

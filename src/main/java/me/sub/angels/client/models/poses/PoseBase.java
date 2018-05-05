package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;

public abstract class PoseBase {
	
	float limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress;
	EntityAngel angel;
	
	public PoseBase() {}
	
	public PoseBase(EntityAngel angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		this.limbSwing = limbSwing;
		this.limbSwingAmount = limbSwingAmount;
		this.ageInTicks = ageInTicks;
		this.netheadYaw = netheadYaw;
		this.headPitch = headPitch;
		this.swingProgress = swingProgress;
		this.angel = angel;
	}
	
	/**
	 * Used to set the Models arm angles
	 */
	public abstract void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right);
	
	/**
	 * Used to set the Models head angles
	 */
	public abstract void setHeadAngles(ModelRenderer head);
	
	/**
	 * Determines angry face
	 */
	public abstract boolean angryFace(EntityAngel angel);
	
	/**
	 * Basically I never use this, it's there for the sake of it, used to set wing angles
	 */
	public abstract void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing);
	
	/**
	 * Returns the Entities Limb Swing
	 */
	public float getLimbSwing() {
		return limbSwing;
	}
	
	/**
	 * Returns the Entities Limb Swing amount
	 */
	public float getLimbSwingAmount() {
		return limbSwingAmount;
	}
	
	/**
	 * Returns the Entities age in ticks
	 */
	public float getAgeInTicks() {
		return ageInTicks;
	}
	
	/**
	 * Returns the Entities head pitch
	 */
	public float getHeadPitch() {
		return headPitch;
	}
	
	/**
	 * Returns the Entities head yaw
	 */
	public float getNetheadYaw() {
		return netheadYaw;
	}
	
	/**
	 * Returns the Entities Swing progress
	 */
	public float getSwingProgress() {
		return swingProgress;
	}
	
	/**
	 * Returns the Entities in use
	 */
	public EntityAngel getAngel() {
		return angel;
	}
	
	/**
	 * Converts degrees to radians, because fuck radians
	 */
	public float degreeToRadian(float degree) {
		return (float) (degree * Math.PI / 180);
	}
}

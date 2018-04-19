package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;

public class PoseHidingFace extends PoseBase {
	
	public PoseHidingFace() {}
	
	public PoseHidingFace(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		wrist_left.rotateAngleX = -0.52F;
		left_arm.rotateAngleX = -1.85F;
		left_arm.rotateAngleY = 0.61F;
		left_arm.rotateAngleZ = -0.087F;
		wrist_right.rotateAngleX = -0.52F;
		right_arm.rotateAngleX = -1.85F;
		right_arm.rotateAngleY = -0.61F;
		right_arm.rotateAngleZ = 0.087F;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = 0.11F;
		head.rotateAngleY = 0.0F;
		head.rotateAngleZ = 0.0F;
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
		
	}
}

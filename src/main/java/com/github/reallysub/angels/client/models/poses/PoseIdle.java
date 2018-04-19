package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;

public class PoseIdle extends PoseBase {
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		right_arm.rotateAngleX = 0;
		left_arm.rotateAngleX = 0;
		wrist_left.rotateAngleX = 0;
		wrist_right.rotateAngleX = 0;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = getHeadPitch() * 0.017453292F + head.rotateAngleZ * getNetheadYaw() * 0.005F + 0.112F;
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
		
	}
}

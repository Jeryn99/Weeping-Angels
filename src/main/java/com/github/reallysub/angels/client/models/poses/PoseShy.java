package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;

public class PoseShy extends PoseBase {
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		right_arm.rotateAngleX = -1.8029251F;
		right_arm.rotateAngleY = -0.01169371F;
		right_arm.rotateAngleZ = -0.8136725F;
		
		left_arm.rotateAngleX = 0;
		left_arm.rotateAngleY = 0.0122173F;
		left_arm.rotateAngleZ = -0.191986F;
		
		wrist_right.rotateAngleX = -0.174533F;
		wrist_right.rotateAngleY = 0;
		wrist_right.rotateAngleZ = 0;
		
		wrist_left.rotateAngleX = -0.46565384F;
		wrist_left.rotateAngleY = 0;
		wrist_left.rotateAngleZ = 0;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = 0.31084314F;
		head.rotateAngleY = 0.52604224F;
		head.rotateAngleZ = 0;
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
		// TODO Auto-generated method stub
		
	}
	
}

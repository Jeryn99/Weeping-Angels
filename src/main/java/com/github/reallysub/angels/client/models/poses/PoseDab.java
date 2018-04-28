package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;

public class PoseDab extends PoseBase{

	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left,
			ModelRenderer wrist_right) {
		// TODO Auto-generated method stub
		right_arm.rotateAngleY=0F;
		right_arm.rotateAngleX=-2.1F;
		right_arm.rotateAngleZ=1F;
		wrist_right.rotateAngleX=-0.4F;
		left_arm.rotateAngleY=0.8F;
		left_arm.rotateAngleX=-0F;
		left_arm.rotateAngleZ=-2F;
		left_arm.rotationPointY=0;
		
	}

	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX=0.6F;
		head.rotateAngleY=0.7F;
		
	}

	@Override
	public boolean angryFace(EntityAngel angel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
		// TODO Auto-generated method stub
		
	}

}

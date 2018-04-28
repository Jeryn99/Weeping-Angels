package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;

public class PoseThinking extends PoseBase{

	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left,
			ModelRenderer wrist_right) {
		left_arm.rotateAngleX=0;
		left_arm.rotateAngleY=0.2F;
		left_arm.rotateAngleZ=-0.3F;
		wrist_left.rotateAngleX=-0.4F;
		
		right_arm.rotateAngleX=-1.3F;
		right_arm.rotateAngleY=-0.9F;
		wrist_right.rotateAngleX=-0.9F;
		
		
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

	@Override
	public void setHeadAngles(ModelRenderer head) {
		// TODO Auto-generated method stub
		
		
	}


}

package com.github.reallysub.angels.client.models.poses;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;

public class PoseOpenArms extends PoseBase {

	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		
	}

	@Override
	public void setHeadAngles(ModelRenderer head) {
	
	}

	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}

	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {	
	}

}

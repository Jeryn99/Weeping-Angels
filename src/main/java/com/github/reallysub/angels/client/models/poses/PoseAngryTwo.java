package com.github.reallysub.angels.client.models.poses;

import java.util.Random;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PoseAngryTwo extends PoseBase {
	
	Random rand = new Random();
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		wrist_left.rotateAngleX = -0.52F;
		left_arm.rotateAngleX = -1.5F;
		left_arm.rotateAngleY = 0.31F;
		left_arm.rotateAngleZ = -0.087F;
		wrist_right.rotateAngleX = -0.52F;
		right_arm.rotateAngleX = -1.5F;
		right_arm.rotateAngleY = -0.31F;
		right_arm.rotateAngleZ = 0.087F;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return true;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {}
}

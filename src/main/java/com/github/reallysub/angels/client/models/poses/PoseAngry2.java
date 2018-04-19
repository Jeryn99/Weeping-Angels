package com.github.reallysub.angels.client.models.poses;

import java.util.Random;

import com.github.reallysub.angels.common.entities.EntityAngel;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PoseAngry2 extends PoseBase {
	
	Random rand = new Random();
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		float swing = MathHelper.sin(getSwingProgress() * (float) Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - getSwingProgress()) * (1.0F - getSwingProgress())) * (float) Math.PI);
		right_arm.rotateAngleZ = 0.0F;
		left_arm.rotateAngleZ = 0.0F;
		right_arm.rotateAngleY = -(0.1F - swing * 0.6F);
		left_arm.rotateAngleY = 0.1F - swing * 0.6F;
		right_arm.rotateAngleX = -1.5F;
		left_arm.rotateAngleX = -1.5F;
		right_arm.rotateAngleX += swing * 1.2F - f1 * 0.4F;
		left_arm.rotateAngleX += swing * 1.2F - f1 * 0.4F;
		right_arm.rotateAngleZ += MathHelper.cos(getAgeInTicks() * 0.09F) * 0.05F + 0.05F;
		left_arm.rotateAngleZ -= MathHelper.cos(getAgeInTicks() * 0.09F) * 0.05F + 0.05F;
		right_arm.rotateAngleX += MathHelper.sin(getAgeInTicks() * 0.067F) * 0.05F;
		left_arm.rotateAngleX -= MathHelper.sin(getAgeInTicks() * 0.067F) * 0.05F;
		wrist_right.rotateAngleX = 0;
		wrist_left.rotateAngleX = 0.0F;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = getHeadPitch() * 0.017453292F;
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return true;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
		
	}
}

package me.suff.angels.client.models.poses;

import me.suff.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelRenderer;

public class PoseThinking extends PoseBase {
	
	public PoseThinking(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseThinking() {
	}
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		left_arm.rotateAngleX = 0;
		left_arm.rotateAngleY = 0.2F;
		left_arm.rotateAngleZ = -0.3F;
		wrist_left.rotateAngleX = -0.4F;
		right_arm.rotateAngleX = -1.3F;
		right_arm.rotateAngleY = -0.9F;
		wrist_right.rotateAngleX = -0.9F;
	}
	
	@Override
	public boolean angryFace() {
		return false;
	}
	
	@Override
	public void setBodyAngles(ModelRenderer body) {
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = 0;
		head.rotateAngleY = 0;
		head.rotateAngleZ = 0;
	}
	
}

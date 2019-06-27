package me.suff.angels.client.models.poses;

import me.suff.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseDab extends PoseBase {
	
	public PoseDab(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseDab() {
	}
	
	@Override
	public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right) {
		
		right_arm.rotateAngleY = 0F;
		right_arm.rotateAngleX = -2.1F;
		right_arm.rotateAngleZ = 1F;
		wrist_right.rotateAngleX = -0.4F;
		wrist_left.rotateAngleX = 0.0F;
		left_arm.rotateAngleY = 0.8F;
		left_arm.rotateAngleX = -0F;
		left_arm.rotateAngleZ = -2F;
		left_arm.rotationPointY = 0;
		
	}
	
	@Override
	public void setHeadAngles(RendererModel head) {
		head.rotateAngleX = 0.6F;
		head.rotateAngleY = 0.7F;
	}
	
	@Override
	public void setBodyAngles(RendererModel body) {
	}
	
	@Override
	public boolean angryFace() {
		return false;
	}
	
	@Override
	public void setWingAngles(RendererModel left_wing, RendererModel right_wing) {
	}
	
}

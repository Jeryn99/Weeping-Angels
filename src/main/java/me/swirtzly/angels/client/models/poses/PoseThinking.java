package me.swirtzly.angels.client.models.poses;

import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseThinking extends PoseBase {

    public PoseThinking(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseThinking() {
	}
	
	@Override
	public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right) {
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
	public void setBodyAngles(RendererModel body) {
	}
	
	@Override
	public void setWingAngles(RendererModel left_wing, RendererModel right_wing) {
	}
	
	@Override
	public void setHeadAngles(RendererModel head) {
		head.rotateAngleX = 0;
		head.rotateAngleY = 0;
		head.rotateAngleZ = 0;
	}
	
}

package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseAngryTwo extends PoseBase {
	
	public PoseAngryTwo(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}

	public PoseAngryTwo(String name){
		super(name);
	}

	@Override
	public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right, boolean hasWrists) {
		left_arm.rotateAngleX = -1.5F;
		left_arm.rotateAngleY = 0.31F;
		left_arm.rotateAngleZ = -0.087F;
		right_arm.rotateAngleX = -1.5F;
		right_arm.rotateAngleY = -0.31F;
		right_arm.rotateAngleZ = 0.087F;

		if (hasWrists) {
			wrist_right.rotateAngleX = -0.52F;
			wrist_left.rotateAngleX = -0.52F;
		}
	}
	
	@Override
	public void setHeadAngles(RendererModel head) {
		
	}
	
	@Override
	public void setBodyAngles(RendererModel body) {}
	
	@Override
	public boolean angryFace() {
		return true;
	}
	
	@Override
	public void setWingAngles(RendererModel left_wing, RendererModel right_wing) {}
}

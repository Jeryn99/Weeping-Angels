package me.swirtzly.minecraft.angels.client.models.poses;

import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseNew extends PoseBase {
	
	@Override
	public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right) {
		
	}
	
	@Override
	public void setHeadAngles(RendererModel head) {
		resetAngles(head);
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
	
}

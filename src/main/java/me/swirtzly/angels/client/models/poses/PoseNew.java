package me.swirtzly.angels.client.models.poses;

import net.minecraft.client.model.ModelRenderer;

public class PoseNew extends PoseBase {
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		resetAngles(head);
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
	
}

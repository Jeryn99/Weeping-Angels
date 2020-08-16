package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PoseHidingFace extends PoseBase {

	public PoseHidingFace(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}

	public PoseHidingFace(String name){
		super(name);
	}

	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right, boolean hasWrists) {
		left_arm.rotateAngleX = -1.85F;
		left_arm.rotateAngleY = 0.61F;
		left_arm.rotateAngleZ = -0.087F;
		right_arm.rotateAngleX = -1.85F;
		right_arm.rotateAngleY = -0.61F;
		right_arm.rotateAngleZ = 0.087F;

		if (hasWrists) {
			wrist_right.rotateAngleX = -0.52F;
			wrist_left.rotateAngleX = -0.52F;
		}
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = 0.11F;
		head.rotateAngleY = 0.0F;
		head.rotateAngleZ = 0.0F;
	}
	
	@Override
	public boolean isAngry() {
		return false;
	}
	
	@Override
	public void setBodyAngles(ModelRenderer body) {}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {}

}

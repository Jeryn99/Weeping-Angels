package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PoseAngryTwo extends PoseBase {
	
	public PoseAngryTwo(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}

	public PoseAngryTwo(String name){
		super(name);
	}

	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right, boolean hasWrists) {
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
	public void setHeadAngles(ModelRenderer head) {
		
	}
	
	@Override
	public void setBodyAngles(ModelRenderer body) {}
	
	@Override
	public boolean isAngry() {
		return true;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {}
}

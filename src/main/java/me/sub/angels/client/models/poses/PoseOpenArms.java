package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PoseOpenArms extends PoseBase {
	
	public PoseOpenArms(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {}
	
}

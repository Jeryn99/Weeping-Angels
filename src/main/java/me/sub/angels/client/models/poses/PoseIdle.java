package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PoseIdle extends PoseBase {
	
	public PoseIdle(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseIdle() {}
	
	@Override
	public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
		right_arm.rotateAngleX = 0;
		left_arm.rotateAngleX = 0;
		wrist_left.rotateAngleX = 0;
		wrist_right.rotateAngleX = 0;
	}
	
	@Override
	public void setHeadAngles(ModelRenderer head) {
		head.rotateAngleX = getHeadPitch() * 0.017453292F + head.rotateAngleZ * getNetheadYaw() * 0.005F + 0.112F;
	}
	
	@Override
	public boolean angryFace(EntityAngel angel) {
		return false;
	}
	
	@Override
	public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {}
}

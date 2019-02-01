package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;

public class PoseAngry extends PoseBase {

    public PoseAngry(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseAngry() {}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
        float swing = MathHelper.sin(getSwingProgress() * (float) Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - getSwingProgress()) * (1.0F - getSwingProgress())) * (float) Math.PI);
		right_arm.roll = 0.0F;
		left_arm.roll = 0.0F;
		right_arm.yaw = -(0.1F - swing * 0.6F);
		left_arm.yaw = 0.1F - swing * 0.6F;
		right_arm.pitch = -1.5F;
		left_arm.pitch = -1.5F;
		right_arm.pitch += swing * 1.2F - f1 * 0.4F;
		left_arm.pitch += swing * 1.2F - f1 * 0.4F;
		right_arm.roll += MathHelper.cos(getAgeInTicks() * 0.09F) * 0.05F + 0.05F;
		left_arm.roll -= MathHelper.cos(getAgeInTicks() * 0.09F) * 0.05F + 0.05F;
		right_arm.pitch += MathHelper.sin(getAgeInTicks() * 0.067F) * 0.05F;
		left_arm.pitch -= MathHelper.sin(getAgeInTicks() * 0.067F) * 0.05F;
	}
	
	@Override
	public void setHeadAngles(Cuboid head) {
		head.pitch = getHeadPitch() * 0.017453292F;
	}
	
	@Override
    public boolean angryFace() {
		return true;
	}

    @Override
    public void setBodyAngles(Cuboid body) {
    }
	
	@Override
	public void setWingAngles(Cuboid left_wing, Cuboid right_wing) {}

}

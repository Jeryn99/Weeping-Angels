package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;

public class PoseHidingFace extends PoseBase {
	
	public PoseHidingFace() {}

    public PoseHidingFace(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
		wrist_left.pitch = -0.52F;
		left_arm.pitch = -1.85F;
		left_arm.yaw = 0.61F;
		left_arm.roll = -0.087F;
		wrist_right.pitch = -0.52F;
		right_arm.pitch = -1.85F;
		right_arm.yaw = -0.61F;
		right_arm.roll = 0.087F;
	}
	
	@Override
	public void setHeadAngles(Cuboid head) {
		head.pitch = 0.11F;
		head.yaw = 0.0F;
		head.roll = 0.0F;
	}
	
	@Override
    public boolean angryFace() {
		return false;
	}

    @Override
    public void setBodyAngles(Cuboid body) {
    }
	
	@Override
	public void setWingAngles(Cuboid left_wing, Cuboid right_wing) {}
	
}

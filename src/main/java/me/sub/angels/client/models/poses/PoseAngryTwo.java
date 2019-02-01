package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;

public class PoseAngryTwo extends PoseBase {

    public PoseAngryTwo(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseAngryTwo() {}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
		wrist_left.pitch = -0.52F;
		left_arm.pitch = -1.5F;
		left_arm.yaw = 0.31F;
		left_arm.roll = -0.087F;
		wrist_right.pitch = -0.52F;
		right_arm.pitch = -1.5F;
		right_arm.yaw = -0.31F;
		right_arm.roll = 0.087F;
	}
	
	@Override
	public void setHeadAngles(Cuboid head) {
		
	}

    @Override
    public void setBodyAngles(Cuboid body) {
    }

    @Override
    public boolean angryFace() {
		return true;
	}
	
	@Override
	public void setWingAngles(Cuboid left_wing, Cuboid right_wing) {}
}

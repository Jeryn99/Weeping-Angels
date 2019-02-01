package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;

public class PoseDab extends PoseBase {

    public PoseDab(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseDab() {}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
		
		right_arm.yaw = 0F;
		right_arm.pitch = -2.1F;
		right_arm.roll = 1F;
		wrist_right.pitch = -0.4F;
		wrist_left.pitch = 0.0F;
		left_arm.yaw = 0.8F;
		left_arm.pitch = -0F;
		left_arm.roll = -2F;
		left_arm.rotationPointY = 0;
		
	}
	
	@Override
	public void setHeadAngles(Cuboid head) {
		head.pitch = 0.6F;
		head.yaw = 0.7F;
    }

    @Override
    public void setBodyAngles(Cuboid body) {
    }

    @Override
    public boolean angryFace() {
		return false;
	}
	
	@Override
	public void setWingAngles(Cuboid left_wing, Cuboid right_wing) {}
	
}

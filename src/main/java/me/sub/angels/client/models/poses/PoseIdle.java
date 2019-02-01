package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;

public class PoseIdle extends PoseBase {

    public PoseIdle(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	public PoseIdle() {}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
		right_arm.pitch = 0;
		left_arm.pitch = 0;
		wrist_left.pitch = 0;
		wrist_right.pitch = 0;
	}
	
	@Override
	public void setHeadAngles(Cuboid head) {
		head.pitch = getHeadPitch() * 0.017453292F + head.roll * getNetheadYaw() * 0.005F + 0.112F;
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

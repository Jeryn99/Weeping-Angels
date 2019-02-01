package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.Cuboid;

public class PoseOpenArms extends PoseBase {

    public PoseOpenArms() {
    }

    public PoseOpenArms(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
		super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
	}
	
	@Override
	public void setArmAngles(Cuboid left_arm, Cuboid right_arm, Cuboid wrist_left, Cuboid wrist_right) {
        left_arm.pitch = degreeToRadian(-90);
        right_arm.pitch = degreeToRadian(-90);

        left_arm.yaw = degreeToRadian(30);
        right_arm.yaw = degreeToRadian(-30);

        left_arm.roll = degreeToRadian(-30);
        right_arm.roll = degreeToRadian(30);

        wrist_left.pitch = degreeToRadian(-45);
        wrist_right.pitch = degreeToRadian(-45);
    }
	
	@Override
	public void setHeadAngles(Cuboid head) {
        head.pitch = degreeToRadian(15);
    }
	
	@Override
    public boolean angryFace() {
        return true;
	}
	
	@Override
    public void setWingAngles(Cuboid left_wing, Cuboid right_wing) {

    }

    @Override
    public void setBodyAngles(Cuboid body) {
        body.pitch = degreeToRadian(0);
    }
	
}

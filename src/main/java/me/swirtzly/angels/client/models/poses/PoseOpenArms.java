package me.swirtzly.angels.client.models.poses;

import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.model.ModelRenderer;

public class PoseOpenArms extends PoseBase {

    public PoseOpenArms() {
    }

    public PoseOpenArms(EntityWeepingAngel entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
        super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
    }

    @Override
    public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right) {
        left_arm.rotateAngleX = degreeToRadian(-90);
        right_arm.rotateAngleX = degreeToRadian(-90);

        left_arm.rotateAngleY = degreeToRadian(30);
        right_arm.rotateAngleY = degreeToRadian(-30);

        left_arm.rotateAngleZ = degreeToRadian(-30);
        right_arm.rotateAngleZ = degreeToRadian(30);

        wrist_left.rotateAngleX = degreeToRadian(-45);
        wrist_right.rotateAngleX = degreeToRadian(-45);
    }

    @Override
    public void setHeadAngles(ModelRenderer head) {
        head.rotateAngleX = degreeToRadian(15);
    }

    @Override
    public boolean angryFace() {
        return true;
    }

    @Override
    public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {

    }

    @Override
    public void setBodyAngles(ModelRenderer body) {
        body.rotateAngleX = degreeToRadian(0);
    }

}

package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseShy extends PoseBase {

    public PoseShy(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
        super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
    }

    public PoseShy(String name) {
        super(name);
    }

    @Override
    public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right, boolean hasWrists) {
        left_arm.rotateAngleX = 0;
        left_arm.rotateAngleY = 0.4F;
        left_arm.rotateAngleZ = -0.3F;
        right_arm.rotateAngleX = -1.3F;
        right_arm.rotateAngleY = -0.9F;

        if (hasWrists) {
            wrist_right.rotateAngleX = -0.9F;
            wrist_left.rotateAngleX = -0.4F;
        }
    }

    @Override
    public void setHeadAngles(RendererModel head) {
        head.rotateAngleX = degreeToRadian(30);
        head.rotateAngleY = degreeToRadian(19);
        head.rotateAngleZ = 0;
    }

    @Override
    public void setBodyAngles(RendererModel body) {
    }

    @Override
    public boolean isAngry() {
        return false;
    }

    @Override
    public void setWingAngles(RendererModel left_wing, RendererModel right_wing) {
    }

}

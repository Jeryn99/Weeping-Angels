package me.suff.mc.angels.client.poses;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class PoseIdle extends PoseBase {

    public PoseIdle(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
        super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
    }

    public PoseIdle(String name) {
        super(name);
    }

    @Override
    public void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right, boolean hasWrists) {
        right_arm.rotateAngleX = 0;
        left_arm.rotateAngleX = 0;
        if (hasWrists) {
            wrist_left.rotateAngleX = 0;
            wrist_right.rotateAngleX = 0;
        }
    }

    @Override
    public void setHeadAngles(RendererModel head) {
        head.rotateAngleX = getHeadPitch() * 0.017453292F + head.rotateAngleZ * getNetheadYaw() * 0.005F + 0.112F;
    }

    @Override
    public boolean isAngry() {
        return false;
    }

    @Override
    public void setBodyAngles(RendererModel body) {
    }

    @Override
    public void setWingAngles(RendererModel left_wing, RendererModel right_wing) {
    }
}

package me.swirtzly.minecraft.angels.client.poses;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

public class PoseShy extends PoseBase {

    public PoseShy(WeepingAngelEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float swingProgress) {
        super(entity, limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, swingProgress);
    }

    public PoseShy(String name) {
        super(name);
    }

    @Override
    public void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right, boolean hasWrists) {
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
    public void setHeadAngles(ModelRenderer head) {
        head.rotateAngleX = degreeToRadian(30);
        head.rotateAngleY = degreeToRadian(19);
        head.rotateAngleZ = 0;
    }

    @Override
    public void setBodyAngles(ModelRenderer body) {
    }

    @Override
    public boolean isAngry() {
        return false;
    }

    @Override
    public void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing) {
    }

}

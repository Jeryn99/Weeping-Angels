package me.swirtzly.minecraft.angels.client.poses;

import net.minecraft.client.renderer.model.ModelRenderer;

public interface IPoseAngles {
    /**
     * Determines body angles, I wouldn't really reccomend messing with this, it's not pretty
     */
    void setBodyAngles(ModelRenderer body);

    /**
     * Basically I never use this, it's there for the sake of it, used to set wing angles
     */
    void setWingAngles(ModelRenderer left_wing, ModelRenderer right_wing);

    /**
     * Used to set the Models arm angles
     */
    void setArmAngles(ModelRenderer left_arm, ModelRenderer right_arm, ModelRenderer wrist_left, ModelRenderer wrist_right, boolean hasWrist);

    /**
     * Used to set the Models head angles
     */
    void setHeadAngles(ModelRenderer head);
}

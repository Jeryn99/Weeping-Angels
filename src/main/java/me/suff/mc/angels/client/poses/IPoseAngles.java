package me.suff.mc.angels.client.poses;

import net.minecraft.client.renderer.entity.model.RendererModel;

public interface IPoseAngles {
    /**
     * Determines body angles, I wouldn't really reccomend messing with this, it's not pretty
     */
    void setBodyAngles(RendererModel body);

    /**
     * Basically I never use this, it's there for the sake of it, used to set wing angles
     */
    void setWingAngles(RendererModel left_wing, RendererModel right_wing);

    /**
     * Used to set the Models arm angles
     */
    void setArmAngles(RendererModel left_arm, RendererModel right_arm, RendererModel wrist_left, RendererModel wrist_right, boolean hasWrist);

    /**
     * Used to set the Models head angles
     */
    void setHeadAngles(RendererModel head);

    default void resetAngles(RendererModel model) {
        model.rotateAngleX = 0;
        model.rotateAngleY = 0;
        model.rotateAngleY = 0;
    }
}

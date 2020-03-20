package me.swirtzly.angels.client.models.entity;

import me.swirtzly.angels.client.models.poses.PoseManager;

/**
 * Created by Swirtzly
 * on 20/03/2020 @ 09:26
 */
public interface IAngelModel {
    void setupAngles(float swingProgress, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, PoseManager.AngelPoses pose);

    void renderQuickly(PoseManager.AngelPoses pose, float scale);
}

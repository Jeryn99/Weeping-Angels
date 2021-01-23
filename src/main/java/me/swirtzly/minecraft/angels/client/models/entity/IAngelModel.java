package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation getTextureForPose(Object weepingAngelEntity, AngelPoses pose);

    AngelPoses getAngelPose();

    void setAngelPose(AngelPoses angelPose);
}

package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation getTextureForPose(Object weepingAngelEntity, WeepingAngelPose pose);

    WeepingAngelPose getAngelPose();

    void setAngelPose(WeepingAngelPose angelPose);

    ResourceLocation generateTex(WeepingAngelPose pose, WeepingAngelEntity.AngelVariants angelVariants);
}

package me.suff.mc.angels.client.models.entity;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import me.suff.mc.angels.common.variants.AbstractVariant;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation generateTex(WeepingAngelPose pose, AbstractVariant angelVariants);

    ResourceLocation getTextureForPose(Object weepingAngelEntity, WeepingAngelPose pose);

    WeepingAngelPose getAngelPose();

    void setAngelPose(WeepingAngelPose angelPose);
}

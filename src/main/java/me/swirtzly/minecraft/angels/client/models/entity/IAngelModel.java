package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation getTextureForPose(WeepingAngelEntity angel);
}

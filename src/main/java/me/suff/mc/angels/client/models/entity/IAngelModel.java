package me.suff.mc.angels.client.models.entity;

import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {

    ResourceLocation getTextureForPose(WeepingAngelEntity angel);
}

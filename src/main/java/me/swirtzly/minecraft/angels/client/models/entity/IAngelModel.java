package me.swirtzly.minecraft.angels.client.models.entity;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.entities.AngelEnums;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import net.minecraft.util.ResourceLocation;

public interface IAngelModel {
	
	ResourceLocation getTextureForPose(AngelPoses pose);
	AngelPoses getAngelPose();
	void setAngelPose(AngelPoses angelPose);
}

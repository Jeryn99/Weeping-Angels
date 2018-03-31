package com.github.reallysub.angels.client;

import com.github.reallysub.angels.WeepingAngels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderAngel<T extends EntityLiving> extends RenderLiving<T> {
	
	ResourceLocation TEXTURE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	
	public RenderAngel(RenderManager manager, ModelBase model, float shadowSize) {
		super(manager, model, shadowSize);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}
	
}

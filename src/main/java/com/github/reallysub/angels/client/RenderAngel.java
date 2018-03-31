package com.github.reallysub.angels.client;

import com.github.reallysub.angels.WeepingAngels;

import com.github.reallysub.angels.client.models.ModelAngel;
import com.github.reallysub.angels.client.models.ModelAngelEd;
import com.github.reallysub.angels.common.entities.EntityAngel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderAngel<T extends EntityLiving> extends RenderLiving<T> {
	
	ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	
	ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	ModelBase modelOne = new ModelAngel();
	ModelBase modelTwo = new ModelAngelEd();
	
	protected static final ResourceLocation[] DESTROY_STAGES = new ResourceLocation[] { new ResourceLocation("textures/blocks/destroy_stage_0.png"), new ResourceLocation("textures/blocks/destroy_stage_1.png"), new ResourceLocation("textures/blocks/destroy_stage_2.png"), new ResourceLocation("textures/blocks/destroy_stage_3.png"), new ResourceLocation("textures/blocks/destroy_stage_4.png"), new ResourceLocation("textures/blocks/destroy_stage_5.png"), new ResourceLocation("textures/blocks/destroy_stage_6.png"), new ResourceLocation("textures/blocks/destroy_stage_7.png"), new ResourceLocation("textures/blocks/destroy_stage_8.png"), new ResourceLocation("textures/blocks/destroy_stage_9.png") };
	
	public RenderAngel(RenderManager manager, ModelBase model, float shadowSize) {
		super(manager, model, shadowSize);
		this.mainModel = modelTwo;
	}
	
	/**
	 * Renders the model in RenderLiving
	 */
	@Override
	protected void renderModel(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		EntityAngel angel = null;
		
		int i = -1;
		
		if (entity instanceof EntityAngel) {
			angel = (EntityAngel) entity;
		}
		
		if (entity.getHealth() <= 9) {
			i = (int) entity.getHealth();
		}
		
		System.out.println(angel.getType());
		GlStateManager.pushMatrix();
		if (angel.getType() == 0) {
			if (i >= 0) {
				Minecraft.getMinecraft().renderEngine.bindTexture(DESTROY_STAGES[i]);
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_ONE);
			modelOne.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		}
		
		if (angel.getType() == 1) {
			if (i >= 0) {
				Minecraft.getMinecraft().renderEngine.bindTexture(DESTROY_STAGES[i]);
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_TWO);
			modelTwo.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
		}
		GlStateManager.popMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return TEXTURE_ONE;
	}
	
}

package me.sub.angels.client.render.entity;

import me.sub.angels.client.models.entity.ModelAngel;
import me.sub.angels.client.models.entity.ModelAngelChild;
import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.client.render.layers.LayerCrack;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.main.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.util.ResourceLocation;

public class RenderAngel extends RenderLiving<EntityAngel> {
	
	private ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	
	private ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	private ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");
	
	private ModelBase modelOne = new ModelAngel();
	private ModelBase modelTwo = new ModelAngelEd();
	private ModelAngelChild modelChild = new ModelAngelChild();

	public RenderAngel(ModelBase model) {
		super(Minecraft.getMinecraft().getRenderManager(), model, 0.0F);
		mainModel = modelTwo;
		addLayer(new LayerCrack(this));
		addLayer(new LayerHeldItem(this));
	}
	
	/**
	 * Renders the model in RenderLiving
	 */
	@Override
	protected void renderModel(EntityAngel angel, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		
		GlStateManager.pushMatrix();
		RenderHelper.enableStandardItemLighting();
		
		if (angel.getHealth() > 0.0F) {
			
			if (angel.isChild()) {
				Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_CHILD);
				modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
			} else {
				
				if (angel.getType() == 0) {
					Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_ONE);
					modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					
				}
				
				if (angel.getType() == 1) {
					Minecraft.getMinecraft().renderEngine.bindTexture(TEXTURE_TWO);
					modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
				}
			}
		}
		
		RenderHelper.disableStandardItemLighting();
		GlStateManager.popMatrix();
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityAngel entity) {
		return null;
	}
	
	@Override
	protected boolean setBrightness(EntityAngel angel, float partialTicks, boolean combineTextures) {
		return true;
	}
	
}

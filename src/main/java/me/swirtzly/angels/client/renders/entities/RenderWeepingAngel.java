package me.swirtzly.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.ModelAngel;
import me.swirtzly.angels.client.models.entity.ModelAngelChild;
import me.swirtzly.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.angels.client.models.entity.ModelAngelMel;
import me.swirtzly.angels.client.models.entity.ModelClassicAngel;
import me.swirtzly.angels.client.renders.entities.layers.LayerCrack;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderWeepingAngel extends MobRenderer {
	
	public static ResourceLocation TEXTURE_FOUR = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_4.png");
	private final EntityModel mainModel;
	private ResourceLocation TEXTURE_ONE = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel.png");
	private ResourceLocation TEXTURE_TWO = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	private ResourceLocation TEXTURE_CLASSIC = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_3.png");
	
	private ResourceLocation TEXTURE_CHILD = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_child.png");
	
	private EntityModel modelOne = new ModelAngel();
	private EntityModel modelTwo = new ModelAngelEd<EntityWeepingAngel>();
	private EntityModel modelChild = new ModelAngelChild<EntityWeepingAngel>();
	private EntityModel modelClassic = new ModelClassicAngel<EntityWeepingAngel>();
	private EntityModel modelMel = new ModelAngelMel();
	
	@SuppressWarnings("unchecked")
	public RenderWeepingAngel(EntityRendererManager manager) {
		super(manager, new ModelAngelEd(), 0.0F);
		mainModel = modelTwo;
		addLayer(new LayerCrack(this));
		addLayer(new HeldItemLayer(this));
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
	
	
	@Override
	protected boolean setBrightness(LivingEntity entitylivingbaseIn, float partialTicks, boolean combineTextures) {
		return true;
	}
	
	@Override
	protected void renderModel(LivingEntity living, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		if (!living.isAlive()) return;
		
		if (living instanceof EntityWeepingAngel) {
			GlStateManager.pushMatrix();
			RenderHelper.enableStandardItemLighting();
			EntityWeepingAngel angel = (EntityWeepingAngel) living;
			
			switch (angel.getAngelType()) {
				case -1:
					bindTexture(TEXTURE_CHILD);
					modelChild.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 0:
					bindTexture(TEXTURE_ONE);
					modelOne.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 1:
					bindTexture(TEXTURE_TWO);
					modelTwo.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 2:
					bindTexture(TEXTURE_CLASSIC);
					modelClassic.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
				case 3:
					bindTexture(TEXTURE_FOUR);
					modelMel.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
					break;
			}
			
			RenderHelper.disableStandardItemLighting();
			GlStateManager.popMatrix();
		}
	}
	
}

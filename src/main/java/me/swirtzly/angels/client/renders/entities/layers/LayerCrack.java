package me.swirtzly.angels.client.renders.entities.layers;

import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.*;
import me.swirtzly.angels.client.renders.entities.RenderWeepingAngel;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LayerCrack extends LayerRenderer {
	
	private static final ResourceLocation CRACK_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
	private static final ResourceLocation CRACK_TEX_2 = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2_cracked.png");
	private final RenderWeepingAngel angelRenderer;
	
	private EntityModel modelOne = new ModelAngel();
	private EntityModel modelTwo = new ModelAngelEd();
	private EntityModel modelChild = new ModelAngelChild();
	private EntityModel modelClassic = new ModelClassicAngel();
	private EntityModel modelMel = new ModelAngelMel();
	private EntityModel modelMain = modelMel;
	
	
	public LayerCrack(RenderWeepingAngel angelRendererIn) {
		super(angelRendererIn);
		angelRenderer = angelRendererIn;
	}
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if(entityIn instanceof EntityWeepingAngel) {
			
			EntityWeepingAngel angel = (EntityWeepingAngel) entityIn;
			
			switch (angel.getAngelType()) {
				case -1:
					modelMain = modelChild;
					break;
				case 0:
					modelMain = modelOne;
					break;
				case 1:
					modelMain = modelTwo;
					break;
				case 2:
					modelMain = modelClassic;
					break;
				case 3:
					modelMain = modelMel;
					break;
			}
			
			
			if (angel.getHealth() <= 5 && angel.getHealth() > 0 || angel.hurtTime > 0) {
				if (!angel.isChild()) {
					if (angel.getAngelType() == 1) {
						angelRenderer.bindTexture(CRACK_TEX_2);
					} else {
						angelRenderer.bindTexture(CRACK_TEX);
					}
					modelMain.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				}
			}
		}
	}
	
	
	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
}

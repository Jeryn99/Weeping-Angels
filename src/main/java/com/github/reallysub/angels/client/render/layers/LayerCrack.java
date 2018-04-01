package com.github.reallysub.angels.client.render.layers;

import com.github.reallysub.angels.client.models.ModelAngel;
import com.github.reallysub.angels.client.models.ModelAngelEd;
import com.github.reallysub.angels.client.render.RenderAngel;
import com.github.reallysub.angels.common.entities.EntityAngel;
import com.github.reallysub.angels.main.WeepingAngels;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCrack implements LayerRenderer<EntityAngel> {
	protected static final ResourceLocation DESTROY_STAGES = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_crack.png");
	private final RenderAngel angelRenderer;
	
	ModelBase modelOne = new ModelAngel();
	ModelBase modelTwo = new ModelAngelEd();
	ModelBase modelMain = modelOne;
	
	public LayerCrack(RenderAngel angelRendererIn) {
		this.angelRenderer = angelRendererIn;
	}
	
	public void doRenderLayer(EntityAngel angel, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if (angel.getType() == 0) {
			modelMain = modelOne;
		}
		
		if (angel.getType() == 1) {
			modelMain = modelTwo;
		}
		
		if (angel.getHealth() <= 5) {
			GlStateManager.pushMatrix();
			boolean flag = angel.isInvisible();
			GlStateManager.depthMask(!flag);
			this.angelRenderer.bindTexture(DESTROY_STAGES);
			this.modelMain.render(angel, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.popMatrix();
		}
	}
	
	public boolean shouldCombineTextures() {
		return false;
	}
}

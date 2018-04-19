package com.github.reallysub.angels.client.models.items;

import com.github.reallysub.angels.client.models.ModelAngel;
import com.github.reallysub.angels.client.models.ModelSnowArm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;

public class RenderTimeyWimeyDetector extends TileEntityItemStackRenderer {
	
	ModelSnowArm angel = new ModelSnowArm();
	
	@Override
	public void renderByItem(ItemStack theStack, float partialTicks) {
		
		GlStateManager.pushMatrix();
		angel.render(0.0625f);
		GlStateManager.popMatrix();
	}
}

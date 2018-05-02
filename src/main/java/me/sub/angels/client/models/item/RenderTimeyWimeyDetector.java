package me.sub.angels.client.models.item;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class RenderTimeyWimeyDetector extends TileEntityItemStackRenderer {
	
	ModelDetector model = new ModelDetector();
	
	@Override
	public void renderByItem(ItemStack theStack, float partialTicks) {
		GlStateManager.pushMatrix();
		model.render(0.0625f);
		GlStateManager.popMatrix();
	}
}

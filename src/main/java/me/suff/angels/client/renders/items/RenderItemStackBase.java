package me.suff.angels.client.renders.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemStackBase extends TileEntityItemStackRenderer {
	
	private ModelBase modelBase;
	
	public RenderItemStackBase(ModelBase model) {
		modelBase = model;
	}
	
	@Override
	public void renderByItem(ItemStack theStack) {
		GlStateManager.pushMatrix();
		modelBase.render(null, 0, 0, 0, 0, 0, 0.0625f);
		GlStateManager.popMatrix();
	}
}

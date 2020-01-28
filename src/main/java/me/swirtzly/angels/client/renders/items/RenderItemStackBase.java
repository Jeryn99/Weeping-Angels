package me.swirtzly.angels.client.renders.items;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class RenderItemStackBase extends ItemStackTileEntityRenderer {
	
	private EntityModel<?> modelBase;
	
	public RenderItemStackBase(EntityModel model) {
		modelBase = model;
	}
	
	@Override
	public void renderByItem(ItemStack theStack) {
		GlStateManager.pushMatrix();
//		modelBase.render(null, 0, 0, 0, 0, 0, 0.0625f);
		GlStateManager.popMatrix();
	}
}

package me.sub.angels.client.render.tiles;

import me.sub.angels.client.models.entity.ModelCG;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTileCG extends TileEntitySpecialRenderer {
	
	private ModelCG model = new ModelCG();
	
	// TODO - Figure out why it looks so weird
	
	@Override
	public void render(TileEntity tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
	
}

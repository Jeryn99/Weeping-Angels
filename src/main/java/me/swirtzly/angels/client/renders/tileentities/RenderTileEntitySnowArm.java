package me.swirtzly.angels.client.renders.tileentities;

import me.swirtzly.angels.client.models.block.ModelSnowArm;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileEntitySnowArm extends TileEntitySpecialRenderer<TileEntitySnowArm> {
	
	private ModelSnowArm arm = new ModelSnowArm();
	
	@Override
	public void render(TileEntitySnowArm tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
		arm.render(0.0625f);
		GlStateManager.popMatrix();
	}
	
}

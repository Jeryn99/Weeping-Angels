package me.suff.angels.client.renders.tileentities;

import me.suff.angels.client.models.block.ModelSnowArm;
import me.suff.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class RenderTileEntitySnowArm extends TileEntityRenderer<TileEntitySnowArm> {
	
	private ModelSnowArm arm = new ModelSnowArm();
	
	
	@Override
	public void render(TileEntitySnowArm tile, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);
		arm.render(0.0625f);
		GlStateManager.popMatrix();
	}
	
}

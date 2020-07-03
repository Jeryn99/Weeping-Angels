package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.minecraft.angels.client.models.block.SnowArmModel;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class SnowArmTileRender extends TileEntityRenderer<SnowArmTile> {
	
	private SnowArmModel arm = new SnowArmModel();
	
	@Override
	public void render(SnowArmTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);
		arm.render(0.0625f);
		GlStateManager.popMatrix();
	}
	
}

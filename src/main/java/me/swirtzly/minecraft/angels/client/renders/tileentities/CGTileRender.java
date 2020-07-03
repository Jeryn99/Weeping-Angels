package me.swirtzly.minecraft.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.minecraft.angels.client.models.entity.ModelCG;
import me.swirtzly.minecraft.angels.common.misc.WAConstants;
import me.swirtzly.minecraft.angels.common.tileentities.ChronodyneGeneratorTile;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class CGTileRender extends TileEntityRenderer<ChronodyneGeneratorTile> {
	
	private ModelCG model = new ModelCG();
	
	@Override
	public void render(ChronodyneGeneratorTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		if (tile.getTileData().contains(WAConstants.ABS_X) && tile.getTileData().contains(WAConstants.ABS_Z)) {
			double presX = tile.getPos().getX() - tile.getTileData().getDouble(WAConstants.ABS_X);
			double presZ = tile.getPos().getZ() - tile.getTileData().getDouble(WAConstants.ABS_Z);
			GlStateManager.translated(presX, 0, presZ);
		}
		GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}

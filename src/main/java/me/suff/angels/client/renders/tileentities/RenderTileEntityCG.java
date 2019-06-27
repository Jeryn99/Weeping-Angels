package me.suff.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.suff.angels.client.models.entity.ModelCG;
import me.suff.angels.common.misc.WAConstants;
import me.suff.angels.common.tileentities.TileEntityChronodyneGenerator;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;

public class RenderTileEntityCG extends TileEntityRenderer<TileEntityChronodyneGenerator> {
	
	private ModelCG model = new ModelCG();
	
	@Override
	public void render(TileEntityChronodyneGenerator tile, double x, double y, double z, float partialTicks, int destroyStage) {
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

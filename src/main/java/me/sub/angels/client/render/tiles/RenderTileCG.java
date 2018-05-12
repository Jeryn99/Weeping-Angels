package me.sub.angels.client.render.tiles;

import me.sub.angels.client.models.entity.ModelCG;
import me.sub.angels.common.tiles.TileCG;
import me.sub.angels.main.NBTKeys;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class RenderTileCG extends TileEntitySpecialRenderer<TileCG> {
	
	private ModelCG model = new ModelCG();
	
	@Override
	public void render(TileCG tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		if (tile.getTileData().hasKey(NBTKeys.ABS_X) && tile.getTileData().hasKey(NBTKeys.ABS_Z)) {
			double presX = tile.getPos().getX() - tile.getTileData().getDouble(NBTKeys.ABS_X);
			double presZ = tile.getPos().getZ() - tile.getTileData().getDouble(NBTKeys.ABS_Z);
			GlStateManager.translate(presX, 0, presZ);
		}
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);
		GlStateManager.popMatrix();
	}
}

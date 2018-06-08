package me.sub.angels.client.render.tiles;

import me.sub.angels.client.models.entity.ModelAngelEd;
import me.sub.angels.common.tiles.TileEntityPlinth;
import me.sub.angels.main.WeepingAngels;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTilePlinth extends TileEntitySpecialRenderer<TileEntityPlinth> {

	ModelAngelEd ed = new ModelAngelEd();
	ResourceLocation TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");

	@Override
	public void render(TileEntityPlinth tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate(0, -1, 0);

		GlStateManager.rotate(-tile.getRotation(), 0, 1, 0);

		if (!tile.getHasSpawned()) {
			Minecraft.getMinecraft().renderEngine.bindTexture(TEX);
			ed.quickRender(0.0625f, tile);
		}

		GlStateManager.popMatrix();
	}

}

package me.suff.angels.client.renders.tileentities;

import me.suff.angels.WeepingAngels;
import me.suff.angels.client.models.entity.ModelAngelEd;
import me.suff.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityPlinth extends TileEntitySpecialRenderer<TileEntityPlinth> {
	
	private ModelAngelEd ed = new ModelAngelEd();
	private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	@Override
	public void render(TileEntityPlinth tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translate(0, -1, 0);
		
		GlStateManager.rotate(-tile.getRotation(), 0, 1, 0);
		
		if (!tile.getHasSpawned()) {
			
			// GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
			// GlStateManager.color(0.2f, 0.2f, 1, 0.5f);
			
			// if (getWorld().rand.nextInt(5) == 1) {
			// GlStateManager.translate(0, this.getWorld().rand.nextInt(3) / 100.0f, 0);
			// GlStateManager.translate(this.getWorld().rand.nextInt(3) / 100.0f, 0, 0);
			// }
			// if (getWorld().rand.nextInt(10) == 1) {
			// GlStateManager.scale(1, 1 + this.getWorld().rand.nextInt(5) / 100.0f, 1);
			// }
			
			Minecraft.getMinecraft().renderEngine.bindTexture(ARM_TEX);
			ed.quickRender(0.0625f, tile);
			GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}
		
		GlStateManager.popMatrix();
	}
	
}

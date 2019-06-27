package me.suff.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.suff.angels.WeepingAngels;
import me.suff.angels.client.models.entity.ModelAngelEd;
import me.suff.angels.common.tileentities.TileEntityPlinth;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderTileEntityPlinth extends TileEntityRenderer<TileEntityPlinth> {
	
	private ModelAngelEd ed = new ModelAngelEd();
	private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	@Override
	public void render(TileEntityPlinth tile, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);
		
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.translatef(0, -1, 0);
		
		GlStateManager.rotatef(-tile.getRotation(), 0, 1, 0);
		
		if (!tile.getHasSpawned()) {
			Minecraft.getInstance().getTextureManager().bindTexture(ARM_TEX);
			ed.quickRender(0.0625f, tile);
			GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}
		
		GlStateManager.popMatrix();
	}
	
}

package me.swirtzly.angels.client.renders.tileentities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.swirtzly.angels.WeepingAngels;
import me.swirtzly.angels.client.models.entity.ModelAngelEd;
import me.swirtzly.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Swirtzly on 17/02/2020 @ 12:18
 */
public class StatueRender extends TileEntityRenderer<StatueTile> {
	
	private ModelAngelEd ed = new ModelAngelEd();
	private ResourceLocation ARM_TEX = new ResourceLocation(WeepingAngels.MODID, "textures/entities/angel_2.png");
	
	@Override
	public void render(StatueTile tile, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		
		GlStateManager.translatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.rotatef(180, 0.0F, 0.0F, 1.0F);
		
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GlStateManager.rotatef(tile.getRotation(), 0, 1, 0);
		
		Minecraft.getInstance().getTextureManager().bindTexture(ARM_TEX);
		ed.quickRender(0.0625f, tile.getPose());
		
		GlStateManager.popMatrix();
	}
}

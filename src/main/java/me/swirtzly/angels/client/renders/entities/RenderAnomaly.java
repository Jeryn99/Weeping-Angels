package me.swirtzly.angels.client.renders.entities;

import me.swirtzly.angels.common.entities.EntityAnomaly;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;

public class RenderAnomaly extends RenderEntity {
	
	private Random random;
	
	public RenderAnomaly(RenderManager manager) {
		super(manager);
		random = new Random(432L);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
		if (!(entity instanceof EntityAnomaly)) return;
		
		EntityAnomaly anom = (EntityAnomaly) entity;
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + anom.getEntityEyeHeight(), z + 0.2F);
		float scale = 0.1F;
		GlStateManager.scale(scale, scale, scale);
		
		int timer = ((EntityAnomaly) entity).ticksExisted;
		
		if (timer > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			RenderHelper.disableStandardItemLighting();
			float f = ((float) timer + partialTicks) / 100.0F;
			float f1 = 0.0F;
			
			if (f > 0.8F) {
				f1 = (f - 0.8F) / 0.2F;
			}
			
			GlStateManager.disableTexture2D();
			GlStateManager.shadeModel(7425);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
			GlStateManager.disableAlpha();
			GlStateManager.enableCull();
			GlStateManager.depthMask(false);
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, -1.0F, -2.0F);
			
			for (int i = 0; (float) i < (f + f * f) / 2.0F * 60.0F; ++i) {
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
				float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
				float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
				bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);
				
				Color color_1 = getRandomColor();
				Color color_2 = getRandomColor();
				Color color_3 = getRandomColor();
				Color color_4 = getRandomColor();
				Color color_5 = getRandomColor();
				
				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(color_5.getRed(), color_5.getBlue(), color_5.getGreen(), color_5.getAlpha()).endVertex();
				
				bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_1.getRed(), color_1.getBlue(), color_1.getGreen(), color_1.getAlpha()).endVertex();
				
				bufferbuilder.pos(0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_2.getRed(), color_2.getBlue(), color_2.getGreen(), color_2.getAlpha()).endVertex();
				
				bufferbuilder.pos(0.0D, (double) f2, (double) (1.0F * f3)).color(color_3.getRed(), color_3.getBlue(), color_3.getGreen(), color_3.getAlpha()).endVertex();
				
				bufferbuilder.pos(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_4.getRed(), color_4.getBlue(), color_4.getGreen(), color_4.getAlpha()).endVertex();
				tessellator.draw();
			}
			
			GlStateManager.popMatrix();
			GlStateManager.depthMask(true);
			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.shadeModel(7424);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableTexture2D();
			GlStateManager.enableAlpha();
			RenderHelper.enableStandardItemLighting();
		}
		
		GlStateManager.popMatrix();
	}
	
	private Color getRandomColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color color = new Color(r, g, b);
		final float hue = random.nextFloat();
		final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
		final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black
		color = Color.getHSBColor(hue, saturation, luminance);
		return color;
	}
	
	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}

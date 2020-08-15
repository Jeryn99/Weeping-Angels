package me.swirtzly.minecraft.angels.client.renders.entities;

import java.awt.Color;
import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.blaze3d.platform.GlStateManager;

import me.swirtzly.minecraft.angels.common.entities.AnomalyEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.DefaultRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class AnomalyRender extends DefaultRenderer {

	private final Random random;

	public AnomalyRender(EntityRendererManager manager) {
		super(manager);
		random = new Random(432L);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {

		if (!(entity instanceof AnomalyEntity)) return;

		AnomalyEntity anom = (AnomalyEntity) entity;

		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y + anom.getEntityEyeHeight(), z + 0.2F);
		float scale = 0.1F;
		GlStateManager.scaled(scale, scale, scale);

		int timer = ((AnomalyEntity) entity).ticksExisted;

		if (timer > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBuffer();
			RenderHelper.disableStandardItemLighting();
			float f = ((float) timer + partialTicks) / 100.0F;
			float f1 = 0.0F;

			if (f > 0.8F) {
				f1 = (f - 0.8F) / 0.2F;
			}
			
			GlStateManager.disableTexture();
			GlStateManager.shadeModel(7425);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
			GlStateManager.disableAlphaTest();
			GlStateManager.enableCull();
			GlStateManager.depthMask(false);
			GlStateManager.pushMatrix();
			GlStateManager.translatef(0.0F, -1.0F, -2.0F);
			
			for (int i = 0; (float) i < (f + f * f) / 2.0F * 60.0F; ++i) {
				GlStateManager.rotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotatef(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotatef(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotatef(random.nextFloat() * 360.0F + f * 90.0F, 0.0F, 0.0F, 1.0F);
				float f2 = random.nextFloat() * 20.0F + 5.0F + f1 * 10.0F;
				float f3 = random.nextFloat() * 2.0F + 1.0F + f1 * 2.0F;
				bufferbuilder.begin(6, DefaultVertexFormats.POSITION_COLOR);

				Color color_1 = getRandomColor();
				Color color_2 = getRandomColor();
				Color color_3 = getRandomColor();
				Color color_4 = getRandomColor();
				Color color_5 = getRandomColor();

				bufferbuilder.pos(0.0D, 0.0D, 0.0D).color(color_5.getRed(), color_5.getBlue(), color_5.getGreen(), color_5.getAlpha()).endVertex();

				bufferbuilder.pos(-0.866D * (double) f3, f2, -0.5F * f3).color(color_1.getRed(), color_1.getBlue(), color_1.getGreen(), color_1.getAlpha()).endVertex();

				bufferbuilder.pos(0.866D * (double) f3, f2, -0.5F * f3).color(color_2.getRed(), color_2.getBlue(), color_2.getGreen(), color_2.getAlpha()).endVertex();

				bufferbuilder.pos(0.0D, f2, 1.0F * f3).color(color_3.getRed(), color_3.getBlue(), color_3.getGreen(), color_3.getAlpha()).endVertex();

				bufferbuilder.pos(-0.866D * (double) f3, f2, -0.5F * f3).color(color_4.getRed(), color_4.getBlue(), color_4.getGreen(), color_4.getAlpha()).endVertex();
				tessellator.draw();
			}
			
			GlStateManager.popMatrix();
			GlStateManager.depthMask(true);
			GlStateManager.disableCull();
			GlStateManager.disableBlend();
			GlStateManager.shadeModel(7424);
			GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableTexture();
			GlStateManager.enableAlphaTest();
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

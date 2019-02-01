package me.sub.angels.client.renders.entities;

import com.mojang.blaze3d.platform.GlStateManager;
import me.sub.angels.common.entities.EntityAnomaly;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.Random;

public class RenderAnomaly extends EntityRenderer<EntityAnomaly>
{

	private Random random;

	public RenderAnomaly(EntityRenderDispatcher manager) {
		super(manager);
		random = new Random(432L);
	}

	
	@Override
	public void render(EntityAnomaly entity, double x, double y, double z, float entityYaw, float partialTicks) {

		if (!(entity instanceof EntityAnomaly)) return;

		EntityAnomaly anom = (EntityAnomaly) entity;

		GlStateManager.pushMatrix();
		GlStateManager.translated(x, y + anom.getEyeHeight(), z + 0.2F);
		float scale = 0.1F;
		GlStateManager.scalef(scale, scale, scale);

		int timer = entity.age;

		if (timer > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder bufferbuilder = tessellator.getBufferBuilder();
			//TODO render helper
//			RenderHelper.disableStandardItemLighting();
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
				bufferbuilder.begin(6, VertexFormats.POSITION_COLOR);

				Color color_1 = getRandomColor();
				Color color_2 = getRandomColor();
				Color color_3 = getRandomColor();
				Color color_4 = getRandomColor();
				Color color_5 = getRandomColor();

				bufferbuilder.vertex(0.0D, 0.0D, 0.0D).color(color_5.getRed(), color_5.getBlue(), color_5.getGreen(), color_5.getAlpha()).next();

				bufferbuilder.vertex(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_1.getRed(), color_1.getBlue(), color_1.getGreen(), color_1.getAlpha()).next();

				bufferbuilder.vertex(0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_2.getRed(), color_2.getBlue(), color_2.getGreen(), color_2.getAlpha()).next();

				bufferbuilder.vertex(0.0D, (double) f2, (double) (1.0F * f3)).color(color_3.getRed(), color_3.getBlue(), color_3.getGreen(), color_3.getAlpha()).next();

				bufferbuilder.vertex(-0.866D * (double) f3, (double) f2, (double) (-0.5F * f3)).color(color_4.getRed(), color_4.getBlue(), color_4.getGreen(), color_4.getAlpha()).next();
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
			//TODO
//			RenderHelper.enableStandardItemLighting();
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
	
	@Override protected Identifier getTexture(EntityAnomaly entityAnomaly)
	{
		return null;
	}
}

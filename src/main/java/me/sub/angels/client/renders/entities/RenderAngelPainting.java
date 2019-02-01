//package me.sub.angels.client.renders.entities;
//
//import com.mojang.blaze3d.platform.GlStateManager;
//import me.sub.angels.WeepingAngels;
//import me.sub.angels.common.entities.EntityAngelPainting;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.render.BufferBuilder;
//import net.minecraft.client.render.Tessellator;
//import net.minecraft.client.render.VertexFormats;
//import net.minecraft.client.render.entity.EntityRenderDispatcher;
//import net.minecraft.client.render.entity.EntityRenderer;
//import net.minecraft.util.Identifier;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
//import net.minecraft.util.math.MathHelper;
//
//@Environment(EnvType.CLIENT)
//public class RenderAngelPainting extends EntityRenderer<EntityAngelPainting>
//{
//	private static final Identifier PAINTING_TEXTURE = new Identifier(WeepingAngels.MODID, "textures/entities/paintings_angels.png");
//
//	public RenderAngelPainting(EntityRenderDispatcher manager) {
//		super(manager);
//	}
//
//	@Override public void render(EntityAngelPainting entity, double x, double y, double z, float entityYaw, float partialTicks)
//	{
//		GlStateManager.pushMatrix();
//		GlStateManager.translated(x, y, z);
//		GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
//		GlStateManager.enableRescaleNormal();
//		MinecraftClient.getInstance().getTextureManager().bindTexture(getTexture(entity));
//		EntityAngelPainting.EnumAngelArt art = entity.art;
//		GlStateManager.scalef(0.0625F, 0.0625F, 0.0625F);
//
//		if (this.renderOutlines) {
//			GlStateManager.enableColorMaterial();
//			//TODO
//			//			GlStateManager.enableOutlineMode(this.getTeamColor(entity));
//		}
//
//		this.renderPainting(entity, art.sizeX, art.sizeY, art.offsetX, art.offsetY);
//
//		if (this.renderOutlines) {
//			//			GlStateManager.disableOutlineMode();
//			GlStateManager.disableColorMaterial();
//		}
//
//		GlStateManager.disableRescaleNormal();
//		GlStateManager.popMatrix();
//		super.render(entity, x, y, z, entityYaw, partialTicks);
//	}
//
//	private void renderPainting(EntityAngelPainting painting, int width, int height, int textureU, int textureV) {
//		float paintingWidth = (float) (-width) / 2.0F;
//		float paintingHeight = (float) (-height) / 2.0F;
//
//		for (int i = 0; i < width / 16; ++i) {
//			for (int j = 0; j < height / 16; ++j) {
//				float f15 = paintingWidth + (float) ((i + 1) * 16);
//				float f16 = paintingWidth + (float) (i * 16);
//				float f17 = paintingHeight + (float) ((j + 1) * 16);
//				float f18 = paintingHeight + (float) (j * 16);
//				this.setLightmap(painting, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
//				float f19 = (float) (textureU + width - i * 16) / 256.0F;
//				float f20 = (float) (textureU + width - (i + 1) * 16) / 256.0F;
//				float f21 = (float) (textureV + height - j * 16) / 256.0F;
//				float f22 = (float) (textureV + height - (j + 1) * 16) / 256.0F;
//				Tessellator tessellator = Tessellator.getInstance();
//				BufferBuilder bufferbuilder = tessellator.getBufferBuilder();
//				bufferbuilder.begin(7, VertexFormats.POSITION_UV_NORMAL);
//				bufferbuilder.vertex((double) f15, (double) f18, -0.5D).texture((double) f20, (double) f21).normal(0.0F, 0.0F, -1.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, -0.5D).texture((double) f19, (double) f21).normal(0.0F, 0.0F, -1.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, -0.5D).texture((double) f19, (double) f22).normal(0.0F, 0.0F, -1.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, -0.5D).texture((double) f20, (double) f22).normal(0.0F, 0.0F, -1.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, 0.5D).texture(0.75D, 0.0D).normal(0.0F, 0.0F, 1.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, 0.5D).texture(0.8125D, 0.0D).normal(0.0F, 0.0F, 1.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, 0.5D).texture(0.8125D, 0.0625D).normal(0.0F, 0.0F, 1.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f18, 0.5D).texture(0.75D, 0.0625D).normal(0.0F, 0.0F, 1.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, -0.5D).texture(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, -0.5D).texture(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, 0.5D).texture(0.8125D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, 0.5D).texture(0.75D, 0.001953125D).normal(0.0F, 1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f18, 0.5D).texture(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, 0.5D).texture(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, -0.5D).texture(0.8125D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f18, -0.5D).texture(0.75D, 0.001953125D).normal(0.0F, -1.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, 0.5D).texture(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f18, 0.5D).texture(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f18, -0.5D).texture(0.751953125D, 0.0625D).normal(-1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f15, (double) f17, -0.5D).texture(0.751953125D, 0.0D).normal(-1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, -0.5D).texture(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, -0.5D).texture(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f18, 0.5D).texture(0.751953125D, 0.0625D).normal(1.0F, 0.0F, 0.0F).end();
//				bufferbuilder.vertex((double) f16, (double) f17, 0.5D).texture(0.751953125D, 0.0D).normal(1.0F, 0.0F, 0.0F).end();
//				tessellator.draw();
//			}
//		}
//	}
//
//	private void setLightmap(EntityAngelPainting painting, float p_77008_2_, float p_77008_3_) {
//		int x = MathHelper.floor(painting.x);
//		int y = MathHelper.floor(painting.y + (double) (p_77008_3_ / 16.0F));
//		int z = MathHelper.floor(painting.z);
//		Direction enumfacing = painting.facing;
//
//		if (enumfacing == Direction.NORTH) {
//			x = MathHelper.floor(painting.x + (double) (p_77008_2_ / 16.0F));
//		}
//
//		if (enumfacing == Direction.WEST) {
//			z = MathHelper.floor(painting.z - (double) (p_77008_2_ / 16.0F));
//		}
//
//		if (enumfacing == Direction.SOUTH) {
//			z = MathHelper.floor(painting.x - (double) (p_77008_2_ / 16.0F));
//		}
//
//		if (enumfacing == Direction.EAST) {
//			z = MathHelper.floor(painting.z + (double) (p_77008_2_ / 16.0F));
//		}
//
//
//		int l = this.renderManager.world.getLightLevel(new BlockPos(x, y, z), 0);
//		int i1 = l % 65536;
//		int j1 = l / 65536;
//		//TODO
////		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i1, (float) j1);
//		GlStateManager.color3f(1.0F, 1.0F, 1.0F);
//	}
//
//	@Override protected Identifier getTexture(EntityAngelPainting entityAngelPainting)
//	{
//		return PAINTING_TEXTURE;
//	}
//}

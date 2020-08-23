package me.swirtzly.minecraft.angels.utils;

import java.util.function.Function;
import java.util.function.Supplier;

import me.swirtzly.minecraft.angels.client.PlayerMovingSound;
import me.swirtzly.minecraft.angels.client.renders.entities.AngelRender;
import me.swirtzly.minecraft.angels.client.renders.entities.AnomalyRender;
import me.swirtzly.minecraft.angels.client.renders.entities.CGRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.CGTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.PlinthTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.SnowArmTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.StatueRender;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.AnomalyEntity;
import me.swirtzly.minecraft.angels.common.entities.ChronodyneGeneratorEntity;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.common.tileentities.ChronodyneGeneratorTile;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientUtil {
	
	public static <T extends TileEntity> void bindTESR(TileEntityType<T> tileEntityType, Function<? super TileEntityRendererDispatcher, ? extends TileEntityRenderer<? super T>> rendererFactory) {
		ClientRegistry.bindTileEntityRenderer(tileEntityType, rendererFactory);
	}
	
	public static <T extends Entity> void bindEntityRender(EntityType<T> entityClass, IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void playSound(Object object, SoundEvent soundIn, SoundCategory categoryIn, boolean repeat, Supplier<Boolean> stopCondition, float volumeSfx) {
		Minecraft.getInstance().getSoundHandler().play(new PlayerMovingSound(object, soundIn, categoryIn, repeat, stopCondition, volumeSfx));
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void playSound(SoundEvent soundIn, float volumeSfx) {
		Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(soundIn, volumeSfx));
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void playSound(PlayerMovingSound playerMovingSound) {
		Minecraft.getInstance().getSoundHandler().play(playerMovingSound);
	}
	
	public static void doClientStuff() {
		ClientUtil.bindTESR(WAObjects.Tiles.ARM.get(), new SnowArmTileRender());
		ClientUtil.bindTESR(WAObjects.Tiles.CG.get(), new CGTileRender());
		ClientUtil.bindTESR(WAObjects.Tiles.PLINTH.get(), new PlinthTileRender());
		ClientUtil.bindTESR(WAObjects.Tiles.STATUE.get(), new StatueRender());
		
		ClientUtil.bindEntityRender(WeepingAngelEntity.class, AngelRender::new);
		ClientUtil.bindEntityRender(AnomalyEntity.class, AnomalyRender::new);
		ClientUtil.bindEntityRender(ChronodyneGeneratorEntity.class, (EntityRendererManager p_i50956_1_) -> new CGRender(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));
	}
	
	public static void drawSelectionBoxMask(AxisAlignedBB box, float red, float green, float blue, float alpha) {
		drawMask(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ, red, green, blue, alpha);
	}
	
	public static void drawMask(double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		drawMask(bufferbuilder, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
		tessellator.draw();
	}
	
	public static void drawMask(BufferBuilder b, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
		// up
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		
		// down
		b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		
		// north
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		
		// south
		b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		
		// east
		b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(maxX, minY, minZ).color(red, green, blue, alpha).endVertex();
		
		// west
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, maxZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, maxY, minZ).color(red, green, blue, alpha).endVertex();
		b.pos(minX, minY, minZ).color(red, green, blue, alpha).endVertex();
	}
}

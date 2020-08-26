package me.swirtzly.minecraft.angels.utils;

import java.util.function.Supplier;

import me.swirtzly.minecraft.angels.client.PlayerMovingSound;
import me.swirtzly.minecraft.angels.client.renders.entities.AngelRender;
import me.swirtzly.minecraft.angels.client.renders.entities.AnomalyRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.CGTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.PlinthTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.SnowArmTileRender;
import me.swirtzly.minecraft.angels.client.renders.tileentities.StatueRender;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientUtil {
	
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
		ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.ARM.get(), SnowArmTileRender::new);
		ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.CG.get(), CGTileRender::new);
		ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.PLINTH.get(), PlinthTileRender::new);
		ClientRegistry.bindTileEntityRenderer(WAObjects.Tiles.STATUE.get(), StatueRender::new);

		RenderingRegistry.registerEntityRenderingHandler(WAObjects.EntityEntries.WEEPING_ANGEL.get(), AngelRender::new);
		RenderingRegistry.registerEntityRenderingHandler(WAObjects.EntityEntries.ANOMALY.get(), AnomalyRender::new);
//TODO		RenderingRegistry.registerEntityRenderingHandler(ChronodyneGeneratorEntity.class, (EntityRendererManager p_i50956_1_) -> new CGRender(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));

		RenderTypeLookup.setRenderLayer(WAObjects.Blocks.ARM.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(WAObjects.Blocks.PLINTH.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(WAObjects.Blocks.STATUE.get(), RenderType.getCutout());
		RenderTypeLookup.setRenderLayer(WAObjects.Blocks.KONTRON_ORE.get(), RenderType.getCutout());
		
	
	}

	private static void bindTESR(TileEntityType<?> tileEntityType, SnowArmTileRender snowArmTileRender) {
		
	}

}

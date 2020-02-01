package me.swirtzly.angels;

import me.swirtzly.angels.client.renders.entities.RenderAnomaly;
import me.swirtzly.angels.client.renders.entities.RenderCG;
import me.swirtzly.angels.client.renders.entities.RenderWeepingAngel;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityCG;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntityPlinth;
import me.swirtzly.angels.client.renders.tileentities.RenderTileEntitySnowArm;
import me.swirtzly.angels.common.entities.EntityAngelPainting;
import me.swirtzly.angels.common.entities.EntityAnomaly;
import me.swirtzly.angels.common.entities.EntityChronodyneGenerator;
import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.events.EventHandler;
import me.swirtzly.angels.common.tileentities.TileEntityChronodyneGenerator;
import me.swirtzly.angels.common.tileentities.TileEntityPlinth;
import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import me.swirtzly.angels.common.world.WorldGen;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("weeping_angels")
public class WeepingAngels {
	
	public static final String MODID = "weeping_angels";
	public static final String NAME = "Weeping Angels";
	
	public static Logger LOGGER = LogManager.getLogger(NAME);
	
	public WeepingAngels() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		//	MinecraftForge.EVENT_BUS.register(new WAObjects());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WAConfig.CONFIG_SPEC);
	}
	
	private void commonSetup(final FMLCommonSetupEvent event) {
		WorldGen.applyFeatures();
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderUtil.bindTESR(TileEntitySnowArm.class, new RenderTileEntitySnowArm());
		RenderUtil.bindTESR(TileEntityChronodyneGenerator.class, new RenderTileEntityCG());
		RenderUtil.bindTESR(TileEntityPlinth.class, new RenderTileEntityPlinth());
		
		RenderUtil.bindEntityRender(EntityWeepingAngel.class, RenderWeepingAngel::new);
		//RenderUtil.bindEntityRender(EntityAngelPainting.class, RenderAngelPainting::new);
		RenderUtil.bindEntityRender(EntityAnomaly.class, RenderAnomaly::new);
		RenderUtil.bindEntityRender(EntityChronodyneGenerator.class, (EntityRendererManager p_i50956_1_) -> new RenderCG(p_i50956_1_, Minecraft.getInstance().getItemRenderer(), 12));
	}
	
	
}

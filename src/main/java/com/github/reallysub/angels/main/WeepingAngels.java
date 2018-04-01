package com.github.reallysub.angels.main;

import org.apache.logging.log4j.Logger;

import com.github.reallysub.angels.common.InitEvents;
import com.github.reallysub.angels.common.events.CommonEvents;
import com.github.reallysub.angels.main.config.Config;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION)
@Mod.EventBusSubscriber
public class WeepingAngels {
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "3.0";
	
	private static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(new Configuration(event.getSuggestedConfigurationFile()));
		logger = event.getModLog();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		
		InitEvents.setUpSpawns();
	}
	
	public static boolean isServer() {
		return FMLCommonHandler.instance().getSide().isServer();
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		ModelResourceLocation loc = new ModelResourceLocation(InitEvents.angelPainting.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(InitEvents.angelPainting, 0, loc);
	}
	
}

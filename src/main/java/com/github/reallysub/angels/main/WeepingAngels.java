package com.github.reallysub.angels.main;

import org.apache.logging.log4j.Logger;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.events.CommonEvents;
import com.github.reallysub.angels.main.config.Config;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, updateJSON = "https://www.github.com/ReallySub/Weeping-Angels-Mod/raw/master/update.json")
@Mod.EventBusSubscriber
public class WeepingAngels {
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "5.0";
	
	private static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(new Configuration(event.getSuggestedConfigurationFile()));
		logger = event.getModLog();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		
		WAObjects.setUpSpawns();
	}
	
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		// I'll fix it later
		ModelResourceLocation loc = new ModelResourceLocation(WAObjects.angelPainting.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(WAObjects.angelPainting, 0, loc);
		
		ModelResourceLocation loc2 = new ModelResourceLocation(WAObjects.angelArmItem.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(WAObjects.angelArmItem, 0, loc2);
	}
	
}

package com.github.reallysub.angels;

import com.github.reallysub.angels.common.events.CommonEvents;
import org.apache.logging.log4j.Logger;

import com.github.reallysub.angels.common.InitEvents;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION)
public class WeepingAngels {
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "3.0";
	
	private static Logger logger;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
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
	
}

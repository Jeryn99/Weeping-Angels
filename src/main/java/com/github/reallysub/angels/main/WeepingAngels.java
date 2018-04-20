package com.github.reallysub.angels.main;

import com.github.reallysub.angels.common.PoseRegistry;
import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.events.CommonEvents;
import com.github.reallysub.angels.common.structures.WorldGenCatacombs;
import com.github.reallysub.angels.common.tiles.TileSnowArm;
import com.github.reallysub.angels.main.config.Config;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, updateJSON = "https://raw.githubusercontent.com/ReallySub/Weeping-Angels-Mod/master/update.json")
@Mod.EventBusSubscriber
public class WeepingAngels {
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "9.0";
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.init(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		WAObjects.setUpSpawns();
		
		GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 8);
		
		GameRegistry.registerTileEntity(TileSnowArm.class, ":snowarm");
		
		PoseRegistry.init();
		
		if (event.getSide() == Side.CLIENT) {
			WAObjects.setUpRenders();
		}
	}
	
}

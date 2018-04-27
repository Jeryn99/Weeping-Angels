package com.github.reallysub.angels.main;

import com.github.reallysub.angels.client.AnalyticsTracking;
import com.github.reallysub.angels.common.PoseRegistry;
import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.events.CommonEvents;
import com.github.reallysub.angels.common.structures.WorldGenCatacombs;
import com.github.reallysub.angels.common.tiles.TileSnowArm;
import com.github.reallysub.angels.main.config.Config;
import com.github.reallysub.angels.utils.WAUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, dependencies = WeepingAngels.DEPENDENCIES, version = WeepingAngels.VERSION, updateJSON = "https://raw.githubusercontent.com/ReallySub/Weeping-Angels-Mod/master/update.json")
@Mod.EventBusSubscriber
public class WeepingAngels {
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "9.7";
	public static final String DEPENDENCIES = "required:forge@[14.23.2.2638,)";
	
	@EventHandler
	public void pre(FMLPreInitializationEvent event) {
		Config.init(new Configuration(event.getSuggestedConfigurationFile()));
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		WAObjects.setUpSpawns();
		
		GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 8);
		
		GameRegistry.registerTileEntity(TileSnowArm.class, WeepingAngels.MODID + ":snowarm");
		
		PoseRegistry.init();
		
		if (event.getSide() == Side.CLIENT) {
			WAObjects.setUpRenders();
		}
	}
	
	@EventHandler
	public void post(FMLPostInitializationEvent event) {
		WAUtils.setupLightItems();
	}
	
}

package me.sub.angels.main;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.structures.WorldGenCatacombs;
import me.sub.angels.common.tiles.TileCG;
import me.sub.angels.common.tiles.TileSnowArm;
import me.sub.angels.utils.WAUtils;
import net.minecraftforge.fml.common.Mod;
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
	public static final String VERSION = "12";
	public static final String DEPENDENCIES = "required-after:forge@[14.23.2.2638,)";
	
	@EventHandler
	public void pre(FMLPreInitializationEvent event) {}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		WAObjects.setUpSpawns();
		
		GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 8);
		
		GameRegistry.registerTileEntity(TileSnowArm.class, WeepingAngels.MODID + ":snowarm");
		GameRegistry.registerTileEntity(TileCG.class, WeepingAngels.MODID + ":cg");
		
		PoseManager.init();
		
		if (event.getSide() == Side.CLIENT) {
			WAObjects.setUpRenders();
		}
	}
	
	@EventHandler
	public void post(FMLPostInitializationEvent event) {
		WAUtils.setupLightItems();
	}
	
}

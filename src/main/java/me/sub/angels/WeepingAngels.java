package me.sub.angels;

import me.sub.angels.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, dependencies = WeepingAngels.DEPENDENCIES, updateJSON = WeepingAngels.VERSION_CHECK)
public class WeepingAngels {

	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "17";
	public static final String DEPENDENCIES = "required-after:forge@[14.23.4.2706,)";
	public static final String VERSION_CHECK = "https://raw.githubusercontent.com/ReallySub/Weeping-Angels-Mod/master/update.json";

	@Mod.Instance(MODID)
	public static WeepingAngels INSTANCE;

	@SidedProxy(clientSide = "me.sub.angels.proxy.ClientProxy", serverSide = "me.sub.angels.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
}

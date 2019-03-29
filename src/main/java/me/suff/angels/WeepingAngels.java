package me.suff.angels;

import me.suff.angels.compat.tardis.TardisMod;
import me.suff.angels.proxy.CommonProxy;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, dependencies = WeepingAngels.DEPENDENCIES, updateJSON = WeepingAngels.VERSION_CHECK)
public class WeepingAngels {
	
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "1.12.2-36";
	public static final String DEPENDENCIES = "required-after:forge@[14.23.2.2638,)";
	public static final String VERSION_CHECK = "https://raw.githubusercontent.com/Suffril/Weeping-Angels-Mod/master/update.json";
	
	public static Logger LOGGER = LogManager.getLogger(NAME);
	
	@Mod.Instance(MODID)
	public static WeepingAngels INSTANCE;
	
	@SidedProxy(clientSide = "me.suff.angels.proxy.ClientProxy", serverSide = "me.suff.angels.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
		
		if (Loader.isModLoaded("tardis")) {
			TardisMod.register();
		}
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

package me.sub.angels;

import me.sub.angels.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, dependencies = WeepingAngels.DEPENDENCIES, updateJSON = WeepingAngels.VERSION_CHECK)
public class WeepingAngels {
	
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "30";
	public static final String DEPENDENCIES = "required-after:forge@[14.23.2.2638,)";
	public static final String VERSION_CHECK = "https://raw.githubusercontent.com/SandedShoes/Weeping-Angels-Mod/master/update.json";
	
	public static Logger LOGGER = LogManager.getLogger(NAME);

	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

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

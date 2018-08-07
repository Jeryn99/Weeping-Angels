package me.sub.angels;

import me.sub.angels.proxy.CommonProxy;
import me.sub.angels.utils.WorldJsonUtils;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION, dependencies = WeepingAngels.DEPENDENCIES, updateJSON = WeepingAngels.VERSION_CHECK)
public class WeepingAngels {
	
	public static final String MODID = "weeping-angels";
	public static final String NAME = "Weeping Angels";
	public static final String VERSION = "18.5";
	public static final String DEPENDENCIES = "required-after:forge@[14.23.4.2706,)";
	public static final String VERSION_CHECK = "https://raw.githubusercontent.com/ReallySub/Weeping-Angels-Mod/master/update.json";

	@Mod.Instance(MODID)
	public static WeepingAngels INSTANCE;
	
	@SidedProxy(clientSide = "me.sub.angels.proxy.ClientProxy", serverSide = "me.sub.angels.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Logger LOG = LogManager.getLogger(NAME);

	public static boolean isDevEnv() {
		return (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOG.info("Enter Pre-Init");
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		LOG.info("Enter Init");
		proxy.init();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LOG.info("Enter Post-Init");
		proxy.postInit();
	}

	@Mod.EventHandler
	public void serverStart(FMLServerStartingEvent e) {
		if (isDevEnv()) {
			LOG.warn("This is a dev Enviroment, registering world commands");
			e.registerServerCommand(new WorldJsonUtils.BuildJsonCommand());
			e.registerServerCommand(new WorldJsonUtils.GenerateJsonCommand());
		}
	}

}

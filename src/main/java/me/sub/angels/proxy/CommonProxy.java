package me.sub.angels.proxy;

import me.sub.angels.combat.vivecraft.ServerReflector;
import me.sub.angels.utils.AngelUtils;
import org.apache.logging.log4j.core.jmx.Server;

public class CommonProxy {

	public static final ServerReflector reflector = new ServerReflector();

	public void preInit() {}
	
	public void init() {
		reflector.init();
	}
	
	public void postInit() {
		AngelUtils.setupLightItems();
	}
	
}

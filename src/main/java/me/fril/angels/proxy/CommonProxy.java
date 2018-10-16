package me.fril.angels.proxy;

import me.fril.angels.combat.vivecraft.ServerReflector;
import me.fril.angels.utils.AngelUtils;

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

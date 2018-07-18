package me.sub.angels.proxy;

import me.sub.angels.utils.AngelUtils;

public class CommonProxy {

	public void preInit() {
	}

	public void init() {
	}
	
	public void postInit() {
		AngelUtils.setupLightItems();
	}
	
}

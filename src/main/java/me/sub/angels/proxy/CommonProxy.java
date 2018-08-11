package me.sub.angels.proxy;

import me.sub.angels.utils.AngelUtils;

public class CommonProxy {
	
	public void preInit() {}

    public void init() {
        //CapabilityManager.INSTANCE.register(IAngelSickness.class, new CapabilitySickness.SicknessStorage(), CapabilitySickness.class);
    }
	
	public void postInit() {
		AngelUtils.setupLightItems();
	}
	
}

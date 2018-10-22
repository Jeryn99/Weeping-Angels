package me.fril.angels.proxy;

import me.fril.angels.combat.vivecraft.ServerReflector;
import me.fril.angels.common.world.generation.WorldGenCatacombs;
import me.fril.angels.utils.AngelUtils;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public static final ServerReflector reflector = new ServerReflector();

	public void preInit() {
		GameRegistry.registerWorldGenerator(new WorldGenCatacombs(),0);
	}
	
	public void init() {
		reflector.init();
	}
	
	public void postInit() {
		AngelUtils.setupLightItems();
	}
	
}

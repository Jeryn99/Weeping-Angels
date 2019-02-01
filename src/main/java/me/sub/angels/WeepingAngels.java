package me.sub.angels;

import me.sub.angels.utils.AngelUtils;
import net.fabricmc.api.ModInitializer;

public class WeepingAngels implements ModInitializer
{

	public static final String MODID = "weeping-angels";

	@Override public void onInitialize()
	{
		AngelUtils.setupLightItems();
	}
}

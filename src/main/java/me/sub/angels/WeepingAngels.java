package me.sub.angels;

import me.sub.angels.common.WAObjects;
import me.sub.angels.utils.AngelUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = WeepingAngels.MODID, name = WeepingAngels.NAME, version = WeepingAngels.VERSION)
public class WeepingAngels {

    public static final String MODID = "weeping-angels";
    public static final String NAME = "Weeping Angels";
    public static final String VERSION = "17";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.4.2706,)";


    @Mod.Instance(MODID)
    public static WeepingAngels INSTANCE;

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }


    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        AngelUtils.setupLightItems();

        if (event.getSide() == Side.SERVER) return;

        WAObjects.setUpRenders();
    }

}

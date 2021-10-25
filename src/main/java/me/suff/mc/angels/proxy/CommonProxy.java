package me.suff.mc.angels.proxy;

import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.common.entities.EntityWeepingAngel;
import me.suff.mc.angels.common.world.generation.WorldGenCatacombs;
import me.suff.mc.angels.common.world.generation.generators.WorldGenOres;
import me.suff.mc.angels.compat.vivecraft.ServerReflector;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public static final ServerReflector reflector = new ServerReflector();

    public void preInit() {
        GameRegistry.registerWorldGenerator(new WorldGenCatacombs(), 0);
        GameRegistry.registerWorldGenerator(new WorldGenOres(), 0);
    }

    public void init() {
        reflector.init();
        LootTableList.register(EntityWeepingAngel.LOOT_TABLE);
    }

    public void postInit() {
        AngelUtils.setupLightItems();
    }

}

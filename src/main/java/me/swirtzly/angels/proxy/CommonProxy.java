package me.swirtzly.angels.proxy;

import me.swirtzly.angels.common.entities.EntityWeepingAngel;
import me.swirtzly.angels.common.world.generation.WorldGenCatacombs;
import me.swirtzly.angels.common.world.generation.generators.WorldGenOres;
import me.swirtzly.angels.compat.vivecraft.ServerReflector;
import me.swirtzly.angels.utils.AngelUtils;
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

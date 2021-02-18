package me.suff.mc.angels.common.entity;

import me.suff.mc.angels.WeepingAngels;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.entity.SpawnGroup;

/* Created by Craig on 18/02/2021 */
public class EntitySpawns {
    //TODO Config
    private static final String[] biomeKeys = new String[]{"minecraft:taiga_hills", "minecraft:taiga", "minecraft:desert", "minecraft:desert_hills", "minecraft:plains", "minecraft:swamp", "minecraft:beach", "minecraft:snowy_taiga"};

    public static void init() {
        BiomeModifications.addSpawn(EntitySpawns::canSpawn, SpawnGroup.CREATURE, WeepingAngels.WEEPING_ANGEL, 100, 1, 4);
    }

    public static boolean canSpawn(BiomeSelectionContext biome) {
        String regName = biome.getBiomeKey().getValue().toString();
        for (String key : biomeKeys) {
            if (key.matches(regName)) {
                return true;
            }
        }
        return false;
    }
}

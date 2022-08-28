package mc.craig.software.angels;

import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.util.WATags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.entity.MobCategory;

public class EntitySpawns {

    public static void init() {
        BiomeModifications.addSpawn(EntitySpawns::canSpawn, MobCategory.CREATURE, WAEntities.WEEPING_ANGEL.get(), 10, 1, 4);
    }

    public static boolean canSpawn(BiomeSelectionContext biome) {
        return biome.hasTag(WATags.ANGEL_SPAWNS);
    }
}
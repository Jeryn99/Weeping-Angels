package dev.jeryn.angels;

import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.util.WATags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class EntitySpawns {

    public static void init() {
        BiomeModifications.addSpawn(EntitySpawns::canSpawn, WAConfiguration.SPAWNS.spawnType.get(), WAEntities.WEEPING_ANGEL.get(), WAConfiguration.SPAWNS.spawnWeight.get(), WAConfiguration.SPAWNS.minCount.get(), WAConfiguration.SPAWNS.maxCount.get());
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    public static boolean canSpawn(BiomeSelectionContext biome) {
        return biome.hasTag(WATags.ANGEL_SPAWNS);
    }
}
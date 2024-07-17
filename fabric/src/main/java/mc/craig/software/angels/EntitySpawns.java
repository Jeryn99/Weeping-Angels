package mc.craig.software.angels;

import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.util.WATags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

import static net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND;

public class EntitySpawns {

    public static void init() {
        BiomeModifications.addSpawn(EntitySpawns::canSpawn, WAConfiguration.SPAWNS.spawnType.get(), WAEntities.WEEPING_ANGEL.get(), WAConfiguration.SPAWNS.spawnWeight.get(), WAConfiguration.SPAWNS.minCount.get(), WAConfiguration.SPAWNS.maxCount.get());
        SpawnPlacements.register(WAEntities.WEEPING_ANGEL.get(), ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
    }

    public static boolean canSpawn(BiomeSelectionContext biome) {
        return biome.hasTag(WATags.ANGEL_SPAWNS);
    }
}
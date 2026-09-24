package dev.jeryn.angels;

import dev.jeryn.angels.common.WAEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class FabricSpawnHelper {

    public static void init() {
        BiomeModifications.create(new ResourceLocation(WeepingAngels.MODID, "weeping_angel_spawns"))
                .add(ModificationPhase.ADDITIONS, context -> getConfig(context).shouldSpawn(), (selectionContext, modificationContext) -> {
                    WAEntitySpawns.BiomeSpawnConfig config = getConfig(selectionContext);
                    modificationContext.getSpawnSettings().addSpawn(config.getMobCategory(), new MobSpawnSettings.SpawnerData(WAEntities.WEEPING_ANGEL.get(), config.spawnWeight, config.minCount, config.maxCount));
                });
    }

    private static WAEntitySpawns.BiomeSpawnConfig getConfig(BiomeSelectionContext context) {
        return WAEntitySpawns.getConfig(context.getBiomeKey().location(), context.hasTag(BiomeTags.IS_OVERWORLD), context.hasTag(BiomeTags.IS_NETHER));
    }

}

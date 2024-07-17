package mc.craig.software.angels.data.neoforge.biome;

import com.mojang.serialization.MapCodec;
import mc.craig.software.angels.WAConfiguration;
import mc.craig.software.angels.common.WAEntities;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.common.world.MobSpawnSettingsBuilder;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

import java.util.List;

public record AddAngelSpawns(HolderSet<Biome> biomes) implements BiomeModifier {
    /**
     * Convenience method for using a single {@linkplain MobSpawnSettings.SpawnerData}s.
     *
     * @param biomes  Biomes to add mob spawns to.
     * @param spawner SpawnerData specifying EntityTYpe, weight, and pack size.
     * @return AddSpawnsBiomeModifier that adds a single spawn entry to the specified biomes.
     */
    public static BiomeModifiers.AddSpawnsBiomeModifier singleSpawn(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawner) {
        return new BiomeModifiers.AddSpawnsBiomeModifier(biomes, List.of(spawner));
    }

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && this.biomes.contains(biome)) {
            MobSpawnSettingsBuilder spawns = builder.getMobSpawnSettings();
            spawns.addSpawn(WAConfiguration.SPAWNS.spawnType.get(), new MobSpawnSettings.SpawnerData(WAEntities.WEEPING_ANGEL.get(), WAConfiguration.SPAWNS.spawnWeight.get(), WAConfiguration.SPAWNS.minCount.get(), WAConfiguration.SPAWNS.maxCount.get()));
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return NeoForgeMod.ADD_SPAWNS_BIOME_MODIFIER_TYPE.get();
    }
}
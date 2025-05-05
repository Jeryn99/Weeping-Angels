package mc.jeryn.dev.statues.data.neoforge;

import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.data.neoforge.level.WABiomeModifiersProvider;
import mc.jeryn.dev.statues.data.neoforge.level.WAConfiguredFeaturesProvider;
import mc.jeryn.dev.statues.data.neoforge.level.WAPlacedFeaturesProvider;
import mc.jeryn.dev.statues.data.neoforge.level.WAStructureProvider;
import mc.jeryn.dev.statues.util.WADamageSources;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, WAConfiguredFeaturesProvider::bootstrap)
            .add(Registries.PLACED_FEATURE, WAPlacedFeaturesProvider::bootstrap)
            .add(Registries.STRUCTURE, WAStructureProvider::bootstrap)
            .add(Registries.DAMAGE_TYPE, arg -> {
                arg.register(WADamageSources.GENERATOR, new DamageType("generator", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
                arg.register(WADamageSources.PUNCH_STONE, new DamageType("punch_stone", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
                arg.register(WADamageSources.SNAPPED_NECK, new DamageType("snapped_neck", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
            })
            .add(Registries.JUKEBOX_SONG, WAJukeBoxSongProvider::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, WABiomeModifiersProvider::bootstrap);


    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BUILDER, Set.of(WeepingAngels.MODID));
    }



}
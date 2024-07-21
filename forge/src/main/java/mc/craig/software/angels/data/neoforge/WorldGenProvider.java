package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.data.neoforge.level.WABiomeModifiersProvider;
import mc.craig.software.angels.data.neoforge.level.WAConfiguredFeaturesProvider;
import mc.craig.software.angels.data.neoforge.level.WAPlacedFeaturesProvider;
import mc.craig.software.angels.util.WADamageSources;
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
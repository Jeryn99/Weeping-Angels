package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.data.forge.level.ProviderBiomeModifiers;
import dev.jeryn.angels.data.forge.level.ProviderConfiguredFeatures;
import dev.jeryn.angels.data.forge.level.ProviderPlacedFeatures;
import dev.jeryn.angels.util.WADamageSources;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ProviderConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ProviderPlacedFeatures::bootstrap)
            .add(Registries.DAMAGE_TYPE, arg -> {
                arg.register(WADamageSources.GENERATOR, new DamageType("generator", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
                arg.register(WADamageSources.PUNCH_STONE, new DamageType("punch_stone", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
                arg.register(WADamageSources.SNAPPED_NECK, new DamageType("snapped_neck", DamageScaling.WHEN_CAUSED_BY_LIVING_NON_PLAYER, 1));
            })
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ProviderBiomeModifiers::bootstrap);


    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BUILDER, Set.of(WeepingAngels.MODID));
    }

    public static HolderLookup.Provider createLookup() {
        return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
    }


}
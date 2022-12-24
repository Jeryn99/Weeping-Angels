package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.data.forge.biome.AddAngelSpawns;
import mc.craig.software.angels.data.forge.level.ProviderConfiguredFeatures;
import mc.craig.software.angels.data.forge.level.ProviderPlacedFeatures;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class WorldGenProvider extends DatapackBuiltinEntriesProvider {

    private static final ResourceKey<BiomeModifier> ADD_ANGELS_SPAWNS = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "weeping_angel_spawns"));
    private static final ResourceKey<BiomeModifier> KONTRON = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "kontron"));
    private static final ResourceKey<BiomeModifier> KONTRON_SMALL = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "kontron_small"));
    private static final ResourceKey<BiomeModifier> SNOW_ANGEL = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "snow_angel"));

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ProviderConfiguredFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {

                var spawnTags = context.lookup(Registries.BIOME).getOrThrow(WATags.ANGEL_SPAWNS);
                var overworldTags = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
                var snowTags = context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SNOWY);

                HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

                ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModifer = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(spawnTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_KONTRON)), GenerationStep.Decoration.UNDERGROUND_ORES);
                ForgeBiomeModifiers.AddFeaturesBiomeModifier oreModiferSmall = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(overworldTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_KONTRON_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES);
                ForgeBiomeModifiers.AddFeaturesBiomeModifier snowAngel = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(snowTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.SNOW_ANGEL)), GenerationStep.Decoration.RAW_GENERATION);

                context.register(ADD_ANGELS_SPAWNS, new AddAngelSpawns(spawnTags));
                context.register(KONTRON, oreModifer);
                context.register(KONTRON_SMALL, oreModiferSmall);
                context.register(SNOW_ANGEL, snowAngel);

            })
            .add(Registries.PLACED_FEATURE, ProviderPlacedFeatures::bootstrap);

    public WorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BUILDER, Set.of(WeepingAngels.MODID));
    }

    public static HolderLookup.Provider createLookup() {
        return BUILDER.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());
    }


}
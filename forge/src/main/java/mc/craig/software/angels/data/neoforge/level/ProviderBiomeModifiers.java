package mc.craig.software.angels.data.neoforge.level;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.data.neoforge.biome.AddAngelSpawns;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ProviderBiomeModifiers {

    private static final ResourceKey<BiomeModifier> ADD_ANGELS_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "weeping_angel_spawns"));
    private static final ResourceKey<BiomeModifier> KONTRON = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "kontron"));
    private static final ResourceKey<BiomeModifier> KONTRON_SMALL = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "kontron_small"));
    private static final ResourceKey<BiomeModifier> SNOW_ANGEL = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "snow_angel"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var spawnTags = context.lookup(Registries.BIOME).getOrThrow(WATags.ANGEL_SPAWNS);
        var overworldTags = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
        var snowTags = context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SNOWY);

        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        BiomeModifiers.AddFeaturesBiomeModifier oreModifer = new BiomeModifiers.AddFeaturesBiomeModifier(spawnTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_KONTRON)), GenerationStep.Decoration.UNDERGROUND_ORES);
        BiomeModifiers.AddFeaturesBiomeModifier oreModiferSmall = new BiomeModifiers.AddFeaturesBiomeModifier(overworldTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.ORE_KONTRON_SMALL)), GenerationStep.Decoration.UNDERGROUND_ORES);
        BiomeModifiers.AddFeaturesBiomeModifier snowAngel = new BiomeModifiers.AddFeaturesBiomeModifier(snowTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.SNOW_ANGEL)), GenerationStep.Decoration.RAW_GENERATION);

       //TODO context.register(ADD_ANGELS_SPAWNS, new AddAngelSpawns(spawnTags));
        context.register(KONTRON, oreModifer);
        context.register(KONTRON_SMALL, oreModiferSmall);
        context.register(SNOW_ANGEL, snowAngel);
    }


}

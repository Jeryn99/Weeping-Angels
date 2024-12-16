package mc.craig.software.angels.data.forge.level;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.data.forge.biome.AddAngelSpawns;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ProviderBiomeModifiers {

    private static final ResourceKey<BiomeModifier> ADD_ANGELS_SPAWNS = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "weeping_angel_spawns"));
    private static final ResourceKey<BiomeModifier> SNOW_ANGEL = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(WeepingAngels.MODID, "snow_angel"));
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var spawnTags = context.lookup(Registries.BIOME).getOrThrow(WATags.ANGEL_SPAWNS);
        var overworldTags = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
        var snowTags = context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SNOWY);

        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);

        ForgeBiomeModifiers.AddFeaturesBiomeModifier snowAngel = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(snowTags, HolderSet.direct(placed.getOrThrow(ProviderPlacedFeatures.SNOW_ANGEL)), GenerationStep.Decoration.RAW_GENERATION);

        context.register(ADD_ANGELS_SPAWNS, new AddAngelSpawns(spawnTags));
        context.register(SNOW_ANGEL, snowAngel);
    }
}

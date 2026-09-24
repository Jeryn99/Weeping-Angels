package mc.craig.software.angels.data.neoforge.level;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.data.neoforge.biome.AddAngelSpawns;
import mc.craig.software.angels.data.neoforge.biome.AddSnowAngels;
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
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class WABiomeModifiersProvider {

    private static final ResourceKey<BiomeModifier> SNOW_ANGEL = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "snow_angel"));
    private static final ResourceKey<BiomeModifier> ANGEL_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.tryBuild(WeepingAngels.MODID, "angel_spawns"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var snowTags = context.lookup(Registries.BIOME).getOrThrow(Tags.Biomes.IS_SNOWY);
        var spawnTags = context.lookup(Registries.BIOME).getOrThrow(WATags.ANGEL_SPAWNS);
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        AddSnowAngels snowAngel = new AddSnowAngels(snowTags, HolderSet.direct(placed.getOrThrow(WAPlacedFeaturesProvider.SNOW_ANGEL)), GenerationStep.Decoration.RAW_GENERATION);
        context.register(ANGEL_SPAWNS, new AddAngelSpawns(spawnTags));
        context.register(SNOW_ANGEL, snowAngel);
    }


}

package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BiomeTagsProvider extends net.minecraft.data.tags.BiomeTagsProvider {

    public BiomeTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Biome>, Biome> biomesEntry : ForgeRegistries.BIOMES.getEntries()) {
            if(biomesEntry.getValue().getPrecipitation() == Biome.Precipitation.SNOW) {
                tag(WATags.ANGEL_SPAWNS).add(biomesEntry.getValue());
            }
        }

        tag(WATags.ANGEL_SPAWNS).addTags(BiomeTags.IS_NETHER, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_JUNGLE);
        tag(WATags.CATACOMB_STRUCTURE_BIOMES).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_JUNGLE);
    }
}
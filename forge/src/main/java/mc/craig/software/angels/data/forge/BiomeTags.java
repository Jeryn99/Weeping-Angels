package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BiomeTags extends net.minecraft.data.tags.BiomeTagsProvider {

    public BiomeTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {

        for (Map.Entry<ResourceKey<Biome>, Biome> biomesEntry : ForgeRegistries.BIOMES.getEntries()) {
            if(biomesEntry.getValue().getPrecipitation() == Biome.Precipitation.SNOW) {
                tag(WATags.ANGEL_SPAWNS).add(biomesEntry.getValue());
            }
        }

        tag(WATags.ANGEL_SPAWNS).addTags(net.minecraft.tags.BiomeTags.IS_FOREST, net.minecraft.tags.BiomeTags.IS_TAIGA);
        tag(WATags.CATACOMB_STRUCTURE_BIOMES).addTags(net.minecraft.tags.BiomeTags.IS_FOREST, net.minecraft.tags.BiomeTags.IS_TAIGA);
    }
}

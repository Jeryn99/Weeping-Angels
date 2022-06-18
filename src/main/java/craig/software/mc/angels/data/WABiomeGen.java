package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

public class WABiomeGen extends TagsProvider<Biome> {

    public WABiomeGen(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, WeepingAngels.MODID, helper);
    }

    @Override
    protected void addTags() {
        this.tag(AngelUtil.CATACOMB_STRUCTURE_BIOMES).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_HILL, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_TAIGA);
        this.tag(AngelUtil.GRAVEYARD_STRUCTURE_BIOMES).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_HILL, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_TAIGA);
        this.tag(AngelUtil.ANGEL_SPAWNS).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_HILL, BiomeTags.IS_MOUNTAIN, BiomeTags.IS_TAIGA);
    }


    public void add(TagKey<Biome> branch, Biome biome) {
        this.tag(branch).add(biome);
    }

    public void add(TagKey<Biome> branch, Biome... biome) {
        this.tag(branch).add(biome);
    }

    public @NotNull String getName() {
        return "Angel Structure - Biome Tags";
    }
}
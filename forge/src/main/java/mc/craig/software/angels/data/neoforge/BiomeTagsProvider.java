package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BiomeTagsProvider extends net.minecraft.data.tags.BiomeTagsProvider {


    public BiomeTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(WATags.ANGEL_SPAWNS).addTags(BiomeTags.HAS_VILLAGE_SNOWY, BiomeTags.SNOW_GOLEM_MELTS);
        tag(WATags.ANGEL_SPAWNS).addTags(BiomeTags.IS_NETHER, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_JUNGLE);
        tag(WATags.CATACOMB_STRUCTURE_BIOMES).addTags(BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA, BiomeTags.IS_JUNGLE);

    }
}

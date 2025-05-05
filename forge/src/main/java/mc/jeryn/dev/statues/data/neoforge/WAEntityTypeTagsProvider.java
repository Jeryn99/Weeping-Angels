package mc.jeryn.dev.statues.data.neoforge;

import mc.jeryn.dev.statues.WeepingAngels;
import mc.jeryn.dev.statues.common.WAEntities;
import mc.jeryn.dev.statues.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WAEntityTypeTagsProvider extends net.minecraft.data.tags.EntityTypeTagsProvider {

    public WAEntityTypeTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(WATags.ANOMALYS).add(WAEntities.WEEPING_ANGEL.get());
    }
}

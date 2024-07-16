package mc.craig.software.angels.data.neoforge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTypeTags extends net.minecraft.data.tags.EntityTypeTagsProvider {

    public EntityTypeTags(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, completableFuture, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(WATags.ANOMALYS).add(WAEntities.WEEPING_ANGEL.get());
    }
}

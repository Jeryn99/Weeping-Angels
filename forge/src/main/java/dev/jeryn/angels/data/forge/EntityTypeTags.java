package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.util.WATags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
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

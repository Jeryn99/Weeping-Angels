package dev.jeryn.angels.data.forge;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WAEntities;
import dev.jeryn.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class EntityTypeTags extends net.minecraft.data.tags.EntityTypeTagsProvider {
    public EntityTypeTags(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(WATags.ANOMALYS).add(WAEntities.WEEPING_ANGEL.get());
    }
}
package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.util.WATags;
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

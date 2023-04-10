package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class StructureTagsProvider extends net.minecraft.data.tags.StructureTagsProvider {

    public StructureTagsProvider(DataGenerator arg, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(arg, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(WATags.CATACOMBS).addOptional(new ResourceLocation(WeepingAngels.MODID, "catacombs"));
    }
}

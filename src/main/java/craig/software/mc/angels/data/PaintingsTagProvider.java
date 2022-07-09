package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.WAPaintings;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.entity.decoration.PaintingVariants;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class PaintingsTagProvider extends PaintingVariantTagsProvider {

    public PaintingsTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(PaintingVariantTags.PLACEABLE).add(WAPaintings.ANGEL.get(), WAPaintings.ANGEL_NETHER.get(), WAPaintings.HAVE_THE_PHONE_BOX.get());

    }
}

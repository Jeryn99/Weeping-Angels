package mc.craig.software.angels.data.forge;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.util.WATags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ItemTags extends ItemTagsProvider {

    public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(WATags.STEALABLE_ITEMS).add(Items.CLOCK, Items.TORCH, Items.COMPASS, Items.RECOVERY_COMPASS);
        tag(WATags.STEALABLE_ITEMS).addOptionalTag(Tags.Items.TOOLS_PICKAXES.location());
        tag(WATags.ATTACK_OVERRIDES).addOptionalTag(Tags.Items.TOOLS_PICKAXES.location());
    }
}

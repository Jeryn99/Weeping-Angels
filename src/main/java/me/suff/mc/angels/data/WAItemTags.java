package me.suff.mc.angels.data;

import me.suff.mc.angels.utils.AngelUtils;
import me.suff.mc.angels.WeepingAngels;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class WAItemTags extends ItemTagsProvider {

    public WAItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void registerTags() {
        ForgeRegistries.BLOCKS.getValues().forEach(block -> {
            if(!block.getRegistryName().getNamespace().contains("tardis")) {
                WeepingAngels.LOGGER.info("Light Value: " + block.getRegistryName() + " || " + AngelUtils.getLightValue(block));
                if (AngelUtils.getLightValue(block) > 7 && block.asItem() != Items.AIR) {
                    add(AngelUtils.HELD_LIGHT_ITEMS, block.asItem());
                }
            }
        });

        add(AngelUtils.THEFT, Items.CLOCK);

    }

    public void add(ITag.INamedTag< Item > branch, Item block) {
        this.getOrCreateBuilder(branch).add(block);
    }
}

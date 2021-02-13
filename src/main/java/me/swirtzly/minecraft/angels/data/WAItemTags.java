package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
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

        add(AngelUtils.KEYS, Items.CLOCK);

    }

    public void add(ITag.INamedTag< Item > branch, Item block) {
        this.getOrCreateBuilder(branch).add(block);
    }
}

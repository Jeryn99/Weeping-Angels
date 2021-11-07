package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeItemTagsProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class WAItemTags extends ItemTagsProvider {

    public WAItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ForgeRegistries.BLOCKS.getValues().forEach(block -> {
            if (!Objects.requireNonNull(block.getRegistryName()).getNamespace().contains("tardis")) {
                WeepingAngels.LOGGER.info("Light Value: " + block.getRegistryName() + " || " + AngelUtil.getLightValue(block));
                if (AngelUtil.getLightValue(block) > 7 && block.asItem() != Items.AIR) {
                    add(AngelUtil.HELD_LIGHT_ITEMS, block.asItem());
                }
            }
        });

        add(AngelUtil.THEFT, Items.CLOCK);

    }

    public void add(Tag.Named<Item> branch, Item block) {
        this.tag(branch).add(block);
    }
}

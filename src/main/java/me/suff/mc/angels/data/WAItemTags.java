package me.suff.mc.angels.data;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blocks.ChronodyneGeneratorBlock;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static me.suff.mc.angels.utils.AngelUtil.THEFT;

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

        add(THEFT, Items.CLOCK);
        for (Item item : ForgeRegistries.ITEMS) {
            if(item instanceof PickaxeItem || item == WAObjects.Items.CHRONODYNE_GENERATOR.get()) {
                add(THEFT, item);
            }
        }

    }

    public void add(Tag.Named<Item> branch, Item block) {
        this.tag(branch).add(block);
    }
}

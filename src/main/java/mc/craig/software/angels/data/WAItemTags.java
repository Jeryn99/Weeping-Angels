package mc.craig.software.angels.data;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.utils.AngelUtil;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ITag;
import net.minecraftforge.common.data.ExistingFileHelper;
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

    public void add(ITag.INamedTag<Item> branch, Item block) {
        this.tag(branch).add(block);
    }
}

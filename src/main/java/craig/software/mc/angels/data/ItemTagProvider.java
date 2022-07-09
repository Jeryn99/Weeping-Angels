package craig.software.mc.angels.data;

import craig.software.mc.angels.WeepingAngels;
import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static craig.software.mc.angels.utils.AngelUtil.THEFT;

public class ItemTagProvider extends ItemTagsProvider {

    public ItemTagProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagProvider, WeepingAngels.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        ForgeRegistries.BLOCKS.getValues().forEach(block -> {
            if (!Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block).getNamespace().contains("tardis"))) {
                WeepingAngels.LOGGER.info("Light Value: " + ForgeRegistries.BLOCKS.getKey(block) + " || " + AngelUtil.getLightValue(block));
                if (AngelUtil.getLightValue(block) > 7 && block.asItem() != Items.AIR) {
                    add(AngelUtil.HELD_LIGHT_ITEMS, block.asItem());
                }
            }
        });

        add(THEFT, Items.CLOCK);
        for (Item item : ForgeRegistries.ITEMS) {
            if (item instanceof PickaxeItem || item == WAObjects.Items.CHRONODYNE_GENERATOR.get()) {
                add(THEFT, item);
            }
        }

    }

    public void add(TagKey<Item> branch, Item block) {
        this.tag(branch).add(block);
    }
}

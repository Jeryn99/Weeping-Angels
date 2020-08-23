package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class WAItemTags extends ItemTagsProvider {

    public static final ITag.INamedTag<Item> KEYS = makeItem(WeepingAngels.MODID, "angel_theft");
    public static final ITag.INamedTag<Item> HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");

    public WAItemTags(DataGenerator p_i232552_1_, BlockTagsProvider p_i232552_2_) {
        super(p_i232552_1_, p_i232552_2_);
    }

    public static ITag.INamedTag<Item> makeItem(String domain, String path) {
        return new ItemTags.Wrapper(new ResourceLocation(domain, path));
    }

    @Override
    protected void registerTags() {
    	  ForgeRegistries.BLOCKS.getValues().forEach(block -> {
              if (AngelUtils.getLightValue(block) > 7) {
                  add(HELD_LIGHT_ITEMS, block.asItem());
              }
          });
    }

    public void add(ITag.INamedTag<Item> branch, Item block) {
        this.getOrCreateBuilder(branch).add(block);
    }
}

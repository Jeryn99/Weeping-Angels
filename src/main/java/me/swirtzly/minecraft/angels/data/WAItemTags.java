package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.items.KeyItem;

public class WAItemTags extends ItemTagsProvider {

    public static final Tag<Item> KEYS = makeItem(WeepingAngels.MODID, "angel_theft");
    public static final Tag<Item> HELD_LIGHT_ITEMS = makeItem(WeepingAngels.MODID, "held_light_items");


    public WAItemTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public static Tag<Item> makeItem(String domain, String path) {
        return new ItemTags.Wrapper(new ResourceLocation(domain, path));
    }

    @Override
    protected void registerTags() {
    	  ForgeRegistries.BLOCKS.getValues().forEach(block -> {
              if (AngelUtils.getLightValue(block) > 7) {
                  add(HELD_LIGHT_ITEMS, block.asItem());
              }
          });
    	
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            
        	if(item instanceof KeyItem){
                add(KEYS, item);
            }
            
            
        }
    }

    public void add(Tag<Item> branch, Item block) {
        this.getBuilder(branch).add(block);
    }
}

package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.items.KeyItem;

public class WAItemTags extends ItemTagsProvider {

    public WAItemTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }


    @Override
    protected void registerTags() {
    	  ForgeRegistries.BLOCKS.getValues().forEach(block -> {
              if (AngelUtils.getLightValue(block) > 7) {
                  add(AngelUtils.HELD_LIGHT_ITEMS, block.asItem());
              }
          });
    	
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            
        	if(item instanceof KeyItem){
                add(AngelUtils.KEYS, item);
            }
            
            
        }
    }

    public void add(Tag<Item> branch, Item block) {
        this.getBuilder(branch).add(block);
    }
}

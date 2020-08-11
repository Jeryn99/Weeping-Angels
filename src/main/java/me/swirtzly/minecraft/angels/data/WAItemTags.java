package me.swirtzly.minecraft.angels.data;

import me.swirtzly.minecraft.angels.WeepingAngels;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class WAItemTags extends ItemTagsProvider {

    public static final Tag<Item> KEYS = makeItem(WeepingAngels.MODID, "angel_theft");


    public WAItemTags(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    public static Tag<Item> makeItem(String domain, String path) {
        return new ItemTags.Wrapper(new ResourceLocation(domain, path));
    }

    @Override
    protected void registerTags() {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
          /*  if(item instanceof KeyItem){
                add(KEYS, item);
            }*/
        }
    }

    public void add(Tag<Item> branch, Item block) {
        this.getBuilder(branch).add(block);
    }
}

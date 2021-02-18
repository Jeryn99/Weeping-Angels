package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.common.item.DetectorItem;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

/* Created by Craig on 18/02/2021 */
public class WAItems {

    private static HashMap<Identifier, Item> ITEMS = new HashMap();

    public static final Item DETECTOR = makeItem(new DetectorItem(new FabricItemSettings().group(ItemGroup.MISC)), "timey_wimey_detector");

    public static Item makeItem(Item item, String name){
        ITEMS.put(new Identifier(Constants.MODID, name), item);
        return item;
    }

    public static void init(){
        ITEMS.forEach((identifier, item) -> Registry.register(Registry.ITEM, identifier, item));
    }

}

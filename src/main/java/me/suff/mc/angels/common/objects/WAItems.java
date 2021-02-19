package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.common.item.ChiselItem;
import me.suff.mc.angels.common.item.DetectorItem;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/* Created by Craig on 18/02/2021 */
public class WAItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Constants.MODID, "main"), () -> new ItemStack(WAItems.DETECTOR));

    public static final Item DETECTOR = makeItem(new DetectorItem(new FabricItemSettings().group(ITEM_GROUP)), "timey_wimey_detector");
    public static final Item CHISEL = makeItem(new ChiselItem(new FabricItemSettings().group(ITEM_GROUP)), "chisel");
    public static final Item KONTRON_INGOT = makeItem(new Item(new FabricItemSettings().group(ITEM_GROUP)), "kontron_ingot");
    public static final Item CHRONODYNE_GENERATOR = makeItem(new Item(new FabricItemSettings().group(ITEM_GROUP)), "chronodyne_generator");


    public static Item makeItem(Item item, String name) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MODID, name), item);
    }

}

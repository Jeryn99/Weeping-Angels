package me.suff.mc.angels.common.objects;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.item.ChiselItem;
import me.suff.mc.angels.common.item.ChronoItem;
import me.suff.mc.angels.common.item.DetectorItem;
import me.suff.mc.angels.common.item.WAMusicDiscItem;
import me.suff.mc.angels.util.Constants;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.awt.*;

/* Created by Craig on 18/02/2021 */
public class WAItems {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Constants.MODID, "main"), () -> new ItemStack(WAItems.DETECTOR));

    public static final Item DETECTOR = makeItem(new DetectorItem(new FabricItemSettings().group(ITEM_GROUP)), "timey_wimey_detector");
    public static final Item CHISEL = makeItem(new ChiselItem(new FabricItemSettings().group(ITEM_GROUP)), "chisel");
    public static final Item KONTRON_INGOT = makeItem(new Item(new FabricItemSettings().group(ITEM_GROUP)), "kontron_ingot");
    public static final Item CHRONODYNE_GENERATOR = makeItem(new ChronoItem(new FabricItemSettings().group(ITEM_GROUP)), "chronodyne_generator");
    public static final Item MUSIC_SALLY = makeItem(new WAMusicDiscItem(6, WASounds.DISC_SALLY, new FabricItemSettings().group(ITEM_GROUP)), "music_disc_sally");
    public static final Item MUSIC_TIME_PREVAILS = makeItem(new WAMusicDiscItem(6, WASounds.DISC_TIME_PREVAILS, new FabricItemSettings().group(ITEM_GROUP)), "music_disc_time_prevails");
    public static final Item SPAWN_EGG = makeItem(new SpawnEggItem(WeepingAngels.WEEPING_ANGEL, Color.BLACK.getRGB(), Color.GRAY.getRGB(), new FabricItemSettings().group(ITEM_GROUP)), "spawn_egg");


    public static Item makeItem(Item item, String name) {
        return Registry.register(Registry.ITEM, new Identifier(Constants.MODID, name), item);
    }

}

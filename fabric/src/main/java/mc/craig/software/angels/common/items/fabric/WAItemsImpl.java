package dev.jeryn.angels.common.items.fabric;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.items.WAItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WAItemsImpl {

    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(
            new ResourceLocation(WeepingAngels.MODID, WeepingAngels.MODID),
            () -> new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get()));

    public static CreativeModeTab getCreativeTab() {
        return TAB;
    }


}

package mc.craig.software.angels.common.items.fabric;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.items.WAItems;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class WAItemsImpl {

    public static final CreativeModeTab TAB = FabricItemGroupBuilder.build(
            new ResourceLocation(WeepingAngels.MODID, WeepingAngels.MODID),
            () -> new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get()));

    public static CreativeModeTab getCreativeTab() {
        return TAB;
    }


}

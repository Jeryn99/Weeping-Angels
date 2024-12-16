package dev.jeryn.angels.common.forge;

import dev.jeryn.angels.common.WAConstants;
import dev.jeryn.angels.common.items.WAItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WATabsImpl {
    public static CreativeModeTab createTab() {
        return CreativeModeTab.builder().title(Component.translatable(WAConstants.CREATIVE_TAB)).icon(() -> new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get())).build();
    }
}

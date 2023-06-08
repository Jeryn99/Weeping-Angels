package mc.craig.software.angels.common.forge;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.items.WAItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WATabsImpl {
    public static CreativeModeTab createTab() {
        return CreativeModeTab.builder().title(Component.translatable(WAConstants.CREATIVE_TAB)).icon(() -> new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get())).build();
    }
}

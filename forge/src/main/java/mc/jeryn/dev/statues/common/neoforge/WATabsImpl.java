package mc.jeryn.dev.statues.common.neoforge;

import mc.jeryn.dev.statues.common.WAConstants;
import mc.jeryn.dev.statues.common.items.WAItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WATabsImpl {
    public static CreativeModeTab createTab() {
        return CreativeModeTab.builder().title(Component.translatable(WAConstants.CREATIVE_TAB)).icon(() -> new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get())).build();
    }
}

package me.swirtzly.angels.common.misc;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class WATabs {

    public static CreativeTabs MAIN_TAB = new CreativeTabs("angels") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(WAObjects.Items.TIMEY_WIMEY_DETECTOR);
        }
    };

}

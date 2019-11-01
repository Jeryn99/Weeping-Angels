package me.swirtzly.angels.common.misc;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WATabs {
	
	public static ItemGroup MAIN_TAB = new ItemGroup("angels") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(WAObjects.Items.TIMEY_WIMEY_DETECTOR);
		}
	};
	
}

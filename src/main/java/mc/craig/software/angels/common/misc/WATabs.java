package mc.craig.software.angels.common.misc;

import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.items.AngelSpawnerItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class WATabs {

    public static CreativeModeTab MAIN_TAB = new CreativeModeTab("angels") {
        @Override
        public ItemStack makeIcon() {
            return AngelSpawnerItem.setType(new ItemStack(WAObjects.Items.ANGEL_SPAWNER.get()), AngelType.DISASTER_MC);
        }
    }.hideScroll();

}

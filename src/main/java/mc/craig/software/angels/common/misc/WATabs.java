package mc.craig.software.angels.common.misc;

import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.common.entities.AngelType;
import mc.craig.software.angels.common.items.AngelSpawnerItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class WATabs {

    public static ItemGroup MAIN_TAB = new ItemGroup("angels") {
        @Override
        public ItemStack makeIcon() {
            return AngelSpawnerItem.setType(new ItemStack(WAObjects.Items.ANGEL_SPAWNER.get()), AngelType.DISASTER_MC);
        }
    };

}

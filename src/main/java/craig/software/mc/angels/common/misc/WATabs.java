package craig.software.mc.angels.common.misc;

import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.items.AngelSpawnerItem;
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

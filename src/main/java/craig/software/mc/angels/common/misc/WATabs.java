package craig.software.mc.angels.common.misc;

import craig.software.mc.angels.common.WAObjects;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.items.AngelSpawnerItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WATabs {

    public static CreativeModeTab MAIN_TAB = new CreativeModeTab("angels") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return AngelSpawnerItem.setType(new ItemStack(WAObjects.Items.ANGEL_SPAWNER.get()), AngelType.DISASTER_MC);
        }
    }.hideScroll();

}

package mc.craig.software.angels.common.items;

import mc.craig.software.angels.WeepingAngels;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class WAItems {

    public static CreativeModeTab MAIN_TAB = new CreativeModeTab(WeepingAngels.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get());
        }
    }.hideScroll();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WeepingAngels.MODID);

    public static final RegistryObject<Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", () -> new DetectorItem(new Item.Properties().stacksTo(1).tab(MAIN_TAB)));


}

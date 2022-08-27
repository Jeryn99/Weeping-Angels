package mc.craig.software.angels.common.items;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class WAItems {

    public static CreativeModeTab MAIN_TAB = new CreativeModeTab(CreativeModeTab.TABS.length - 1, WeepingAngels.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(WAItems.TIMEY_WIMEY_DETECTOR.get());
        }
    }.hideScroll();

    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(WeepingAngels.MODID, Registry.ITEM_REGISTRY);

    public static final RegistrySupplier<Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", () -> new DetectorItem(new Item.Properties().stacksTo(1).tab(MAIN_TAB)));
    public static final RegistrySupplier<Item> DISC_TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> new DiscItem(88, WASounds.DISC_TIME_PREVAILS.get(), (new Item.Properties()).stacksTo(1).tab(MAIN_TAB).rarity(Rarity.RARE), 320));
    public static final RegistrySupplier<Item> DISC_SALLY = ITEMS.register("music_disc_sally", () -> new DiscItem(89, WASounds.DISC_SALLY.get(), (new Item.Properties()).stacksTo(1).tab(MAIN_TAB).rarity(Rarity.RARE), 1300));
    public static final RegistrySupplier<Item> KONTRON_INGOT = ITEMS.register("kontron_ingot", () -> new Item(new Item.Properties().tab(WAItems.MAIN_TAB)));
    public static final RegistrySupplier<Item> ANGEL_SPAWNER = ITEMS.register("angel_spawner", () -> new SpawnerItem(WAEntities.WEEPING_ANGEL::get, new Item.Properties().tab(WAItems.MAIN_TAB)));


}

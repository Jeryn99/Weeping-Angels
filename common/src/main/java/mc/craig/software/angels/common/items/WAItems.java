package mc.craig.software.angels.common.items;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class WAItems {

    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(WeepingAngels.MODID, Registries.ITEM);

    public static final RegistrySupplier<Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", () -> new DetectorItem(new Item.Properties().stacksTo(1)));
    public static final RegistrySupplier<Item> DISC_TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> new RecordItem(0, WASounds.DISC_TIME_PREVAILS.get(), (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE), 320 / 20));
    public static final RegistrySupplier<Item> DISC_SALLY = ITEMS.register("music_disc_sally", () -> new RecordItem(0, WASounds.DISC_SALLY.get(), (new Item.Properties()).stacksTo(1).rarity(Rarity.RARE), 1300 / 20));
    public static final RegistrySupplier<Item> CHRONODYNE_GENERATOR = ITEMS.register("chronodyne_generator", () -> new ThrowableGeneratorItem(new Item.Properties().stacksTo(6)));
    public static final RegistrySupplier<Item> ANGEL_SPAWNER = ITEMS.register("angel_spawner", () -> new SpawnerItem(WAEntities.WEEPING_ANGEL::get, new Item.Properties()));

    public static final RegistrySupplier<Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(new Item.Properties().stacksTo(1)));


}

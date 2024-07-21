package mc.craig.software.angels.common.items;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongs;
import net.minecraft.world.item.Rarity;

public class WAItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(WeepingAngels.MODID, Registries.ITEM);

    public static final ResourceKey<JukeboxSong> TIME_PREVAILS = createKey("time_prevails");
    public static final ResourceKey<JukeboxSong> SALLY = createKey("sally");

    public static final RegistryHolder<Item, Item> TIMEY_WIMEY_DETECTOR = ITEMS.register("timey_wimey_detector", () -> new DetectorItem(new Item.Properties().stacksTo(1)));
    public static final RegistryHolder<Item, Item> DISC_TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> new Item((new Item.Properties()).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(TIME_PREVAILS)));
    public static final RegistryHolder<Item, Item> DISC_SALLY = ITEMS.register("music_disc_sally", () -> new Item((new Item.Properties()).stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(SALLY)));
    public static final RegistryHolder<Item, Item> CHRONODYNE_GENERATOR = ITEMS.register("chronodyne_generator", () -> new ThrowableGeneratorItem(new Item.Properties().stacksTo(6)));
    public static final RegistryHolder<Item, Item> ANGEL_SPAWNER = ITEMS.register("angel_spawner", () -> new SpawnerItem(WAEntities.WEEPING_ANGEL.get(), new Item.Properties()));

    public static final RegistryHolder<Item, Item> CHISEL = ITEMS.register("chisel", () -> new ChiselItem(new Item.Properties().stacksTo(1)));

    public static ResourceKey<JukeboxSong> createKey(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.tryBuild(WeepingAngels.MODID, name));
    }

}

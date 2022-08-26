package mc.craig.software.angels.common.items;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.blocks.WABlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
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
    public static final RegistryObject<Item> DISC_TIME_PREVAILS = ITEMS.register("music_disc_time_prevails", () -> new RecordItem(88, WASounds.DISC_TIME_PREVAILS, (new Item.Properties()).stacksTo(1).tab(MAIN_TAB).rarity(Rarity.RARE), 320));
    public static final RegistryObject<Item> DISC_SALLY = ITEMS.register("music_disc_sally", () -> new RecordItem(1, WASounds.DISC_SALLY, (new Item.Properties()).stacksTo(1).tab(MAIN_TAB).rarity(Rarity.RARE), 1300));
    public static final RegistryObject<Item> KONTRON_INGOT = ITEMS.register("kontron_ingot", () -> new Item(new Item.Properties().tab(WAItems.MAIN_TAB)));


}

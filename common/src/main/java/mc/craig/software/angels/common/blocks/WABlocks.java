package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.Registry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class WABlocks {

    public static final DeferredRegistry<Block> BLOCKS = DeferredRegistry.create(WeepingAngels.MODID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> KONTRON_ORE = register("kontron_ore", () -> new MineableBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)), WAItems.MAIN_TAB);
    public static final RegistrySupplier<Block> KONTRON_ORE_DEEPSLATE = register("deepslate_kontron_ore", () -> new MineableBlock(BlockBehaviour.Properties.copy(WABlocks.KONTRON_ORE.get()).color(MaterialColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)), WAItems.MAIN_TAB);
    public static final RegistrySupplier<Block> CHRONODYNE_GENERATOR = register("chronodyne_generator", () -> new GeneratorBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.COLOR_LIGHT_GREEN).sound(SoundType.METAL)), WAItems.MAIN_TAB);
    public static final RegistrySupplier<Block> COFFIN = register("coffin", () -> new CoffinBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).sound(SoundType.WOOD)), WAItems.MAIN_TAB);


    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        RegistrySupplier<T> registryObject = BLOCKS.register(id, blockSupplier);
        WAItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

}

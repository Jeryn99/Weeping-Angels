package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.registry.DeferredRegistry;
import mc.craig.software.angels.registry.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


import java.util.function.Supplier;

public class WABlocks {

    public static final DeferredRegistry<Block> BLOCKS = DeferredRegistry.create(WeepingAngels.MODID, Registries.BLOCK);

    public static final RegistrySupplier<Block> KONTRON_ORE = register("kontron_ore", () -> new MineableBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistrySupplier<Block> KONTRON_ORE_DEEPSLATE = register("deepslate_kontron_ore", () -> new MineableBlock(BlockBehaviour.Properties.copy(WABlocks.KONTRON_ORE.get()).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE)));
    public static final RegistrySupplier<Block> CHRONODYNE_GENERATOR = register("chronodyne_generator", () -> new GeneratorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.METAL)), false);
    public static final RegistrySupplier<Block> COFFIN = register("coffin", () -> new CoffinBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> STATUE = register("statue", () -> new StatueBaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE)));
    public static final RegistrySupplier<Block> PLINTH = register("plinth", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE)));
    public static final RegistrySupplier<Block> SNOW_ANGEL = register("snow_angel", SnowAngelBlock::new);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, boolean needsItem) {
        RegistrySupplier<T> registryObject = BLOCKS.register(id, blockSupplier);
        if(needsItem) {
            WAItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties()));
        }
        return registryObject;
    }

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier) {
        return register(id, blockSupplier, true);
    }

}

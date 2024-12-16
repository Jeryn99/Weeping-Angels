package dev.jeryn.angels.common.blocks;

import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.items.WAItems;
import dev.jeryn.angels.registry.DeferredRegistry;
import dev.jeryn.angels.registry.RegistrySupplier;
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

    public static final RegistrySupplier<Block> CHRONODYNE_GENERATOR = register("chronodyne_generator", () -> new GeneratorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.METAL)), false);
    public static final RegistrySupplier<Block> COFFIN = register("coffin", () -> new CoffinBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.WOOD)));
    public static final RegistrySupplier<Block> STATUE = register("statue", () -> new StatueBaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE)));
    public static final RegistrySupplier<Block> PLINTH = register("plinth", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE).noOcclusion()));
    public static final RegistrySupplier<Block> SNOW_ANGEL = register("snow_angel", SnowAngelBlock::new);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup, boolean needsItem) {
        RegistrySupplier<T> registryObject = BLOCKS.register(id, blockSupplier);
        if(needsItem) {
            WAItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        }
        return registryObject;
    }

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, CreativeModeTab itemGroup) {
        return register(id, blockSupplier, itemGroup, true);
    }

}

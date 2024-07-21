package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import mc.craig.software.angels.common.items.WAItems;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.DeferredRegister;
import mc.craig.software.angels.registry.RegistryHolder;
import mc.craig.software.angels.registry.RegistryHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;


import java.util.function.Supplier;

public class WABlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(WeepingAngels.MODID, Registries.BLOCK);

    public static final RegistryHolder<Block, Block> CHRONODYNE_GENERATOR = register("chronodyne_generator", () -> new GeneratorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.METAL)), false);
    public static final RegistryHolder<Block, Block> COFFIN = register("coffin", () -> new CoffinBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD), new Supplier<BlockEntityType<? extends CoffinBlockEntity>>() {
        @Override
        public BlockEntityType<? extends CoffinBlockEntity> get() {
            return WABlockEntities.COFFIN.get();
        }
    }));
    public static final RegistryHolder<Block, Block> STATUE = register("statue", () -> new StatueBaseBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE)));
    public static final RegistryHolder<Block, Block> PLINTH = register("plinth", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).sound(SoundType.STONE).noOcclusion()));
    public static final RegistryHolder<Block, Block> SNOW_ANGEL = register("snow_angel", SnowAngelBlock::new);

    private static RegistryHolder<Block, Block> register(String id, Supplier<Block> blockSupplier, boolean needsItem) {
        RegistryHolder<Block, Block> registryObject = BLOCKS.register(id, blockSupplier);
        if(needsItem) {
            WAItems.ITEMS.register(id, () -> new BlockItem(registryObject.get(), new Item.Properties()));
        }
        return registryObject;
    }

    private static <T extends Block> RegistryHolder<Block, Block> register(String id, Supplier<Block> blockSupplier) {
        return register(id, blockSupplier, true);
    }

}

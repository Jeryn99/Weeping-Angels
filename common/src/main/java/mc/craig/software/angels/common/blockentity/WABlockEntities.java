package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.blocks.WABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class WABlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WeepingAngels.MODID);

    public static RegistryObject<BlockEntityType<CoffinBlockEntity>> COFFIN = BLOCK_ENTITY_TYPES.register("coffin", () -> registerBlockEntity(CoffinBlockEntity::new, WABlocks.COFFIN.get()));

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BlockEntityType.BlockEntitySupplier<T> tile, Block validBlock) {
        return BlockEntityType.Builder.of(tile, validBlock).build(null);
    }

}

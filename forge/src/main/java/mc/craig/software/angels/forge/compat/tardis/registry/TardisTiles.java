package mc.craig.software.angels.forge.compat.tardis.registry;

import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.forge.compat.tardis.AbPropTile;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tardis.mod.blocks.TileBlock;

public class TardisTiles {
    public static final DeferredRegister<BlockEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WeepingAngels.MODID);

    private static <T extends BlockEntity> BlockEntityType<T> registerTiles(BlockEntityType.BlockEntitySupplier<T> tile, Block... validBlock) {
        BlockEntityType<T> type = BlockEntityType.Builder.of(tile, validBlock).build(null);

        for (Block block : validBlock) {
            if (block instanceof TileBlock) {
                ((TileBlock) block).setTileEntity(type);
            }
        }

        return type;
    }    //Exteriors

    public static RegistryObject<BlockEntityType<AbPropTile>> EXTERIOR_2005 = TILES.register("2005_exterior", () -> registerTiles(AbPropTile::new, TardisBlocks.EXTERIOR_2005.get()));

}

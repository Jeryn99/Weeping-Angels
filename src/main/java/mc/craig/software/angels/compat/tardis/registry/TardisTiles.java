package mc.craig.software.angels.compat.tardis.registry;

import com.google.common.base.Supplier;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.compat.tardis.AbPropTile;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.blocks.TileBlock;

public class TardisTiles {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, WeepingAngels.MODID);

    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        TileEntityType<T> type = TileEntityType.Builder.of(tile, validBlock).build(null);

        for (Block block : validBlock) {
            if (block instanceof TileBlock) {
                ((TileBlock) block).setTileEntity(type);
            }
        }

        return type;
    }    //Exteriors

    public static RegistryObject<TileEntityType<AbPropTile>> EXTERIOR_2005 = TILES.register("2005_exterior", () -> registerTiles(AbPropTile::new, NewTardisBlocks.EXTERIOR_2005.get()));


}

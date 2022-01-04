package me.suff.mc.angels.compat.tardis.registry;

import com.google.common.base.Supplier;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.compat.tardis.AbPropTile;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tardis.mod.blocks.TileBlock;

public class STiles {
    public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, WeepingAngels.MODID);
    //Exteriors
    public static RegistryObject<TileEntityType<AbPropTile>> EXTERIOR_ABPROP = TILES.register("exterior_abprop", () -> registerTiles(AbPropTile::new, NewTardisBlocks.EXTERIOR_ABPROP.get()));

    private static <T extends TileEntity> TileEntityType<T> registerTiles(Supplier<T> tile, Block... validBlock) {
        TileEntityType<T> type = TileEntityType.Builder.of(tile, validBlock).build(null);

        for (Block block : validBlock) {
            if (block instanceof TileBlock) {
                ((TileBlock) block).setTileEntity(type);
            }
        }

        return type;
    }
}

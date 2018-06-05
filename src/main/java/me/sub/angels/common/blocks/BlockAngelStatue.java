package me.sub.angels.common.blocks;

import me.sub.angels.common.tiles.TileEntityPlinth;
import me.sub.angels.main.WeepingAngels;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockAngelStatue extends Block implements ITileEntityProvider {

    public BlockAngelStatue() {
        super(Material.CORAL);
        this.setUnlocalizedName("plinth");
        setRegistryName(WeepingAngels.MODID, "plinth");
        translucent = true;
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityPlinth();
    }


    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}

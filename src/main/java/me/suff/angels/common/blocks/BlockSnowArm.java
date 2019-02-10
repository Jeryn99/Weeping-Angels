package me.suff.angels.common.blocks;

import me.suff.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSnowArm extends BlockSnow {
	
	public BlockSnowArm() {
		super();
		translucent = true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySnowArm();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}

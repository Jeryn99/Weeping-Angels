package me.sub.angels.common.blocks;

import me.sub.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSnowArm extends BlockSnow {
	
	public BlockSnowArm() {
		super();
		translucent = true;
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntitySnowArm();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}

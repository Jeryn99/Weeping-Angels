package me.suff.angels.common.blocks;

import me.suff.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockSnowArm extends BlockSnow {
	
	public BlockSnowArm() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE));
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(IBlockState state, IBlockReader world) {
		return new TileEntitySnowArm();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
}

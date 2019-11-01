package me.swirtzly.angels.common.blocks;

import me.swirtzly.angels.common.tileentities.TileEntitySnowArm;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockSnowArm extends SnowBlock {
	
	public BlockSnowArm() {
		super(Block.Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE));
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntitySnowArm();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
}

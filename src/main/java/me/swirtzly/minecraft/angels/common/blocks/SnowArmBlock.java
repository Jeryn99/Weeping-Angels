package me.swirtzly.minecraft.angels.common.blocks;

import javax.annotation.Nullable;

import me.swirtzly.minecraft.angels.common.tileentities.SnowArmTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class SnowArmBlock extends SnowBlock {
	
	public SnowArmBlock() {
		super(Block.Properties.create(Material.SNOW).notSolid().hardnessAndResistance(3).sound(SoundType.SNOW));
	}
	
	@Nullable
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new SnowArmTile();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
}

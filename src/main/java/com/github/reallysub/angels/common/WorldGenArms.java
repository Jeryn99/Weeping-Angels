package com.github.reallysub.angels.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenArms extends WorldGenerator {
	private Block arm;
	private IBlockState state;
	
	public WorldGenArms(Block arm) {
		this.setGeneratedBlock(arm);
	}
	
	public void setGeneratedBlock(Block arm) {
		this.arm = arm;
		this.state = arm.getDefaultState();
	}
	
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		
		int yCoord = worldIn.getHeight(position).getY();
		BlockPos pos = new BlockPos(position.add(rand.nextInt(8), yCoord, rand.nextInt(8) - rand.nextInt(8)));
		
		if (worldIn.isAirBlock(pos) && (!worldIn.provider.isNether() || pos.getY() < 255) && worldIn.getBiome(position).isSnowyBiome()) {
			worldIn.setBlockState(pos, this.state, 1);
		}
		return true;
	}
}

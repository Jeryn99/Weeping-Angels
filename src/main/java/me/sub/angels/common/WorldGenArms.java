package me.sub.angels.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenArms extends WorldGenerator {
	private Block arm;
	private IBlockState state;
	
	public WorldGenArms(Block arm) {
		this.arm = arm;
		this.state = arm.getDefaultState();
	}

	
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		int yCoord = world.getHeight(position).getY();
		BlockPos pos = new BlockPos(position.add(rand.nextInt(8), yCoord, rand.nextInt(8) - rand.nextInt(8)));
		
		if ((!world.provider.isNether() || pos.getY() < 255) && world.getBiome(position).isSnowyBiome()) {
			if (world.getBlockState(pos).getBlock() == Blocks.SNOW || world.getBlockState(pos).getBlock() == Blocks.SNOW_LAYER) world.setBlockState(pos, this.state, 1);
		}
		return true;
	}
}

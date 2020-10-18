package me.swirtzly.minecraft.angels.common.world;

import com.mojang.serialization.Codec;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

/**
 * Created by Swirtzly on 11/02/2020 @ 21:58
 */
public class ArmGeneration extends Feature<NoFeatureConfig> {

	public ArmGeneration(Codec<NoFeatureConfig> noFeatureConfigCodec) {
		super(noFeatureConfigCodec);
	}

	@Override
	public boolean func_241855_a(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoFeatureConfig noFeatureConfig) {
		for (int y = 45; y < 70; ++y) {
			BlockPos test = new BlockPos(blockPos.getX(), y, blockPos.getZ());
			if(random.nextInt(100) < 20) {
				if (iSeedReader.getBlockState(test).getBlock().getRegistryName().toString().contains("snow") || iSeedReader.getBlockState(test.down()).getBlock() == Blocks.GRASS_BLOCK) {
					iSeedReader.setBlockState(test, WAObjects.Blocks.ARM.get().getDefaultState(), 2);
					return true;
				}
			}
		}
		return false;
	}
}

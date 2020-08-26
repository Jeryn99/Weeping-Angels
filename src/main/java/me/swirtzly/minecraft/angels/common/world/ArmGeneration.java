package me.swirtzly.minecraft.angels.common.world;

import com.mojang.serialization.Codec;
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


	public ArmGeneration(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}

	@Override
	public boolean func_241855_a(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, NoFeatureConfig p_241855_5_) {
		return false;
	}
}

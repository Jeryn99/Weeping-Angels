package me.swirtzly.minecraft.angels.common.world;

import com.mojang.datafixers.Dynamic;
import com.mojang.serialization.Codec;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

/**
 * Created by Craig on 11/02/2020 @ 21:58
 */
public class ArmGeneration extends Feature< NoFeatureConfig > {


    public ArmGeneration(Function< Dynamic< ? >, ? extends NoFeatureConfig > configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator< ? extends GenerationSettings > generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        for (int y = 45; y < 70; ++y) {
            BlockPos test = new BlockPos(pos.getX(), y, pos.getZ());
            if (rand.nextInt(100) < 20) {
                if (worldIn.getBlockState(test).getBlock().getRegistryName().toString().contains("snow") || worldIn.getBlockState(test.down()).getBlock() == Blocks.GRASS_BLOCK) {
                    worldIn.setBlockState(test, WAObjects.Blocks.ARM.get().getDefaultState(), 2);
                    return true;
                }
            }
        }
        return false;
    }
}

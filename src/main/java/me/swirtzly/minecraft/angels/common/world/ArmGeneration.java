package me.swirtzly.minecraft.angels.common.world;

import com.mojang.datafixers.Dynamic;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ArmGeneration extends Feature< NoFeatureConfig > {


    public ArmGeneration(Function< Dynamic< ? >, ? extends NoFeatureConfig > p_i49878_1_) {
        super(p_i49878_1_);
    }

    public ArmGeneration(Function< Dynamic< ? >, ? extends NoFeatureConfig > p_i49879_1_, boolean p_i49879_2_) {
        super(p_i49879_1_, p_i49879_2_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator< ? extends GenerationSettings > generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (worldIn.getBiome(pos) == Biomes.THE_VOID)
            return false;
        for (int y = 45; y < 70; ++y) {
            BlockPos test = new BlockPos(pos.getX(), y, pos.getZ());
            if (worldIn.getBlockState(test).getBlock().getRegistryName().toString().contains("snow") || worldIn.getBlockState(test.down()).getBlock() == Blocks.GRASS_BLOCK) {
                worldIn.setBlockState(test, WAObjects.Blocks.ARM.get().getDefaultState(), 2);
                return true;
            }
        }
        return false;
    }

}

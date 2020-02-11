package me.swirtzly.angels.common.world;

import com.mojang.datafixers.Dynamic;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.config.WAConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

/**
 * Created by Swirtzly
 * on 11/02/2020 @ 21:58
 */
public class ArmGeneration extends Feature<NoFeatureConfig> {

    public ArmGeneration(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!WAConfig.CONFIG.arms.get()) return false;
        for (int i = 0; i < 77; i++) {
            BlockPos newPos = pos.up(i);
            if (newPos.getY() < 255) {
                if (world.getBlockState(newPos).getBlock() == Blocks.SNOW || world.getBlockState(newPos).getBlock() == Blocks.SNOW_BLOCK) {
                    world.setBlockState(newPos, WAObjects.Blocks.ARM.get().getDefaultState(), 1);
                    System.out.println("gen at: " + newPos);
                    return true;
                }
            }
        }
        return false;
    }

}


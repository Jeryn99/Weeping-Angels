package craig.software.mc.angels.common.world;

import com.mojang.serialization.Codec;
import java.util.Random;
import craig.software.mc.angels.common.WAObjects;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ProbabilityConfig;

/**
 * Created by Craig on 11/02/2020 @ 21:58
 */
public class SnowAngelGeneration extends Feature<ProbabilityConfig> {

    public SnowAngelGeneration(Codec<ProbabilityConfig> noFeatureConfigCodec) {
        super(noFeatureConfigCodec);
    }

    @Override
    public boolean place(ISeedReader iSeedReader, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, ProbabilityConfig noFeatureConfig) {
        for (int y = 45; y < 70; ++y) {
            BlockPos test = new BlockPos(blockPos.getX(), y, blockPos.getZ());
            if (random.nextInt(100) < 10) {
                if (iSeedReader.getBlockState(test).getBlock().getRegistryName().toString().contains("snow") || iSeedReader.getBlockState(test.below()).getBlock() == Blocks.GRASS_BLOCK) {
                    iSeedReader.setBlock(test, WAObjects.Blocks.SNOW_ANGEL.get().defaultBlockState(), 2);
                    return true;
                }
            }
        }
        return false;
    }
}

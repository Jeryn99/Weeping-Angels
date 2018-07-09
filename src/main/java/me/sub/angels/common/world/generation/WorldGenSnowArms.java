package me.sub.angels.common.world.generation;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenSnowArms implements IWorldGenerator {
    /**
     * Generate some world
     *
     * @param random         the chunk specific {@link Random}.
     * @param chunkX         the chunk X coordinate of this chunk.
     * @param chunkZ         the chunk Z coordinate of this chunk.
     * @param world          : additionalData[0] The minecraft {@link World} we're generating for.
     * @param chunkGenerator : additionalData[1] The {@link IChunkProvider} that is generating.
     * @param chunkProvider  : additionalData[2] {@link IChunkProvider} that is requesting the world generation.
     */
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        //TODO Write Snow arm generation
    }
}

package me.sub.angels.common.world.generation;

import me.sub.angels.WeepingAngels;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenCatacombs implements IWorldGenerator {

    private static ResourceLocation partStraight = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_0");
    private static ResourceLocation partCorner1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_1");
    private static ResourceLocation partTSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_2");
    private static ResourceLocation partCrossSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_3");
    public static ResourceLocation[] allParts = new ResourceLocation[]{partCorner1, partStraight, partTSection, partCrossSection};
    private static ResourceLocation catacomb_hallway_flat_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_1");
    private static ResourceLocation catacomb_hallway_flat_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_2");
    private static ResourceLocation catacomb_hallway_flat_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_flat_3");

    private static ResourceLocation catacomb_hallway_clean_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_1");
    private static ResourceLocation catacomb_hallway_clean_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_2");
    private static ResourceLocation catacomb_hallway_clean_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_3");
    private static ResourceLocation catacomb_hallway_clean_4 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_clean_4");

    private static ResourceLocation catacomb_hallway_broken_1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_1");
    private static ResourceLocation catacomb_hallway_broken_2 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_2");
    private static ResourceLocation catacomb_hallway_broken_3 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_broken_3");
    public static ResourceLocation[] allStraightParts = new ResourceLocation[]{catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3};

    public static ResourceLocation getStraightPart(Random rand) {
        return allStraightParts[rand.nextInt(allStraightParts.length)];
    }

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

    }
}

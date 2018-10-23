package me.fril.angels.common.world.generation;

import me.fril.angels.common.world.generation.generators.WorldGenStructure;
import me.fril.angels.config.WAConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WorldGenCatacombs implements IWorldGenerator {

	private static final String PATH_CATACOMB = "catacomb/";

	public static final List<String> structuresNames = Arrays.asList("catacomb_hallway_0", "catacomb_hallway_1", "catacomb_hallway_2", "catacomb_hallway_3", "catacomb_hallway_flat_1", "catacomb_hallway_flat_2", "catacomb_hallway_flat_3", "catacomb_hallway_clean_1", "catacomb_hallway_clean_2", "catacomb_hallway_clean_3", "catacomb_hallway_clean_4", "catacomb_hallway_broken_1", "catacomb_hallway_broken_2", "catacomb_hallway_broken_3");
	public static final String partStraight = "catacomb_hallway_0";
	public static final String partCorner1 = "catacomb_hallway_1";
	public static final String partTSection = "catacomb_hallway_2";
	public static final String partCrossSection = "catacomb_hallway_3";

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (WAConfig.worldGen.genCatacombs){
			if (world.provider.getDimension() == 0){
				recursiveGenerator(world,random,chunkX,chunkZ,WAConfig.worldGen.chanceCatacombs);
			}
		}
	}

	private void recursiveGenerator(World world, Random random, int chunkX, int chunkZ, int chance){
		int x = chunkX * 16 + random.nextInt(15);
		int z = chunkZ * 16 + random.nextInt(15);
		int y = random.nextInt(64 - 15) + 15;

		if (world.getWorldType()!= WorldType.FLAT && random.nextInt(chance) == 0) {
			generateStructure(getRandomStructure(random), world, random, x, y, z);

			while (random.nextInt(3) == 0) {
				if (random.nextBoolean()){
					x = random.nextBoolean() ? x + 7 : x - 7;
				}
				else{
					z = random.nextBoolean() ? z + 7 : z - 7;
				}

				generateStructure(getRandomStructure(random), world, random, x, y, z);
			}
		}
	}

	private void generateStructure(WorldGenerator generator, World world, Random random, int x, int y, int z){
		BlockPos pos = new BlockPos(x,y,z);
		generator.generate(world,random,pos);
	}

	private WorldGenStructure getRandomStructure(Random random){
		return new WorldGenStructure(PATH_CATACOMB + structuresNames.get(random.nextInt(structuresNames.size() - 1)));
	}
/*	public static ResourceLocation[] allParts = new ResourceLocation[]{partCorner1, partStraight, partTSection, partCrossSection};

	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3 };

	public static ResourceLocation getStraightPart(Random rand) {
		return allStraightParts[rand.nextInt(allStraightParts.length)];
	} */
}
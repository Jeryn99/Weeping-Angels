package me.fril.angels.common.world.generation;

import me.fril.angels.common.world.generation.generators.WorldGenStructure;
import me.fril.angels.config.WAConfig;
import me.fril.angels.WeepingAngels;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class WorldGenCatacombs implements IWorldGenerator {

	private static final String PATH_CATACOMB = "catacomb/";

	public static final WorldGenStructure CATACOMB_HALLWAY_0 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_0");
	public static final WorldGenStructure CATACOMB_HALLWAY_1 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_1");
	public static final WorldGenStructure CATACOMB_HALLWAY_2 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_2");
	public static final WorldGenStructure CATACOMB_HALLWAY_3 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_3");

	public static final WorldGenStructure CATACOMB_HALLWAY_FLAT_1 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_flat_1");
	public static final WorldGenStructure CATACOMB_HALLWAY_FLAT_2 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_flat_2");
	public static final WorldGenStructure CATACOMB_HALLWAY_FLAT_3 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_flat_3");

	public static final WorldGenStructure CATACOMB_HALLWAY_CLEAN_1 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_clean_1");
	public static final WorldGenStructure CATACOMB_HALLWAY_CLEAN_2 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_clean_2");
	public static final WorldGenStructure CATACOMB_HALLWAY_CLEAN_3 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_clean_3");
	public static final WorldGenStructure CATACOMB_HALLWAY_CLEAN_4 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_clean_4");

	public static final WorldGenStructure CATACOMB_HALLWAY_BROKEN_1 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_broken_1");
	public static final WorldGenStructure CATACOMB_HALLWAY_BROKEN_2 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_broken_2");
	public static final WorldGenStructure CATACOMB_HALLWAY_BROKEN_3 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_broken_3");

	public static final List<String> structuresNames = Arrays.asList("catacomb_hallway_0", "catacomb_hallway_1", "catacomb_hallway_2", "catacomb_hallway_3", "catacomb_hallway_flat_1", "catacomb_hallway_flat_2", "catacomb_hallway_flat_3", "catacomb_hallway_clean_1", "catacomb_hallway_clean_2", "catacomb_hallway_clean_3", "catacomb_hallway_clean_4", "catacomb_hallway_broken_1", "catacomb_hallway_broken_2", "catacomb_hallway_broken_3");

	//public static final List<WorldGenStructure> CATACOMBS = Arrays.asList(CATACOMB_HALLWAY_0,CATACOMB_HALLWAY_1,CATACOMB_HALLWAY_2,CATACOMB_HALLWAY_3, CATACOMB_HALLWAY_FLAT_1, CATACOMB_HALLWAY_FLAT_2,CATACOMB_HALLWAY_FLAT_3,CATACOMB_HALLWAY_CLEAN_1,CATACOMB_HALLWAY_CLEAN_2,CATACOMB_HALLWAY_CLEAN_3,CATACOMB_HALLWAY_CLEAN_4,CATACOMB_HALLWAY_BROKEN_1,CATACOMB_HALLWAY_BROKEN_2,CATACOMB_HALLWAY_BROKEN_3);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0){
			WorldGenStructure theWinnerIs = new WorldGenStructure(PATH_CATACOMB + structuresNames.get(random.nextInt(structuresNames.size()-1)));
			WeepingAngels.LOGGER.info(theWinnerIs.toString());
			generateStructure(theWinnerIs,world,random,chunkX,chunkZ,4);
		}
	}

	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance){
		int x = chunkX * 16 + random.nextInt(15);
		int z = chunkZ * 16 + random.nextInt(15);
		//TODO : change 100 to a real value
		int y = 100;
		BlockPos pos = new BlockPos(x,y,z);

		if (world.getWorldType()!= WorldType.FLAT && random.nextInt(chance) == 0){
			generator.generate(world,random,pos);
		}
	}
/*	public static ResourceLocation[] allParts = new ResourceLocation[]{partCorner1, partStraight, partTSection, partCrossSection};

	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3 };

	public static ResourceLocation getStraightPart(Random rand) {
		return allStraightParts[rand.nextInt(allStraightParts.length)];
	} */

	private int getRandom(int lower, int higher){
		return (int)(Math.random() * (higher-lower)) + lower;
	}

}

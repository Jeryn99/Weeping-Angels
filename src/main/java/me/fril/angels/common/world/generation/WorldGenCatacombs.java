package me.fril.angels.common.world.generation;

import me.fril.angels.common.world.generation.generators.WorldGenStructure;
import me.fril.angels.config.WAConfig;
import me.fril.angels.WeepingAngels;
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

import java.util.Random;

public class WorldGenCatacombs implements IWorldGenerator {

	private static final String PATH_CATACOMB = "catacomb/";
	public static final WorldGenStructure CATACOMB_HALLWAY_0 = new WorldGenStructure(PATH_CATACOMB + "catacomb_hallway_0");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimension() == 0){
			generateStructure(CATACOMB_HALLWAY_0,world,random,chunkX,chunkZ,4);
		}
	}

	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance){
		int x = chunkX * 16 + random.nextInt(15);
		int z = chunkZ * 16 + random.nextInt(15);
		int y = 100;
		BlockPos pos = new BlockPos(x,y,z);

		if (world.getWorldType()!= WorldType.FLAT && random.nextInt(chance) == 0){
			generator.generate(world,random,pos);
		}
	}
/*  private static ResourceLocation partStraight = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_0");
	private static ResourceLocation partCorner1 = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_1");
	private static ResourceLocation partTSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_2");
	private static ResourceLocation partCrossSection = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_3");
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


	public static ResourceLocation[] allParts = new ResourceLocation[]{partCorner1, partStraight, partTSection, partCrossSection};

	public static ResourceLocation[] allStraightParts = new ResourceLocation[] { catacomb_hallway_flat_1, catacomb_hallway_flat_2, catacomb_hallway_flat_3, partStraight, catacomb_hallway_clean_1, catacomb_hallway_clean_2, catacomb_hallway_clean_3, catacomb_hallway_clean_4, catacomb_hallway_broken_1, catacomb_hallway_broken_2, catacomb_hallway_broken_3 };

	public static ResourceLocation getStraightPart(Random rand) {
		return allStraightParts[rand.nextInt(allStraightParts.length)];
	} */

}

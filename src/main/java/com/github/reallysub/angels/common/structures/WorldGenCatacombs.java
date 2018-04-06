package com.github.reallysub.angels.common.structures;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCatacombs extends WorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
			if (rand.nextInt(4000) == 0) {
				
				int x = chunkX * 16 + rand.nextInt(16);
				int y = 25;
				int z = chunkZ * 16 + rand.nextInt(16);
				
				while (!world.getBlockState(new BlockPos(x, y, z)).isFullBlock() && y > 0) {
					y--;
				}
				
				// System.out.println(new BlockPos(x + 2, y + 2, z));
				
				WorldGenCatacombs.generate(world, rand, new BlockPos(x - 6, y, z), Rotation.NONE, CatacombParts.partTSection);
				
				// 8 Corridors
				for (int times = 0; times <= 8; times++) {
					
					ResourceLocation part = CatacombParts.partStraight;
					
					if (times == 3) {
						part = CatacombParts.partCrossSection;
					} else {
						part = CatacombParts.partStraight;
					}
					
					// System.out.println(part + " : " + times);
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x + 6 * times, y, z), Rotation.NONE, part);
					WorldGenCatacombs.generate(world, rand, new BlockPos(x - 6 * times, y, z), Rotation.NONE, CatacombParts.partStraight);
				}
				
				// Corridor
				WorldGenCatacombs.generate(world, rand, new BlockPos(x + 6 + 72, y, z), Rotation.NONE, CatacombParts.partCrossSection);
				
				for (int times = 0; times <= 8; times++) {
					
					ResourceLocation part = CatacombParts.partStraight;
					
					if (times == 4) {
						part = CatacombParts.partCrossSection;
					} else {
						part = CatacombParts.partStraight;
					}
					
					// System.out.println(part + " : " + times);
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x, y, z + 6 * times), Rotation.CLOCKWISE_90, part);
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x, y, z - 6 * times), Rotation.CLOCKWISE_90, CatacombParts.partStraight);
					
				}
			}
		}
	}
	
	public static boolean generate(World world, Random rand, BlockPos pos, Rotation rot, ResourceLocation loc) {
		MinecraftServer server = world.getMinecraftServer();
		TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
		
		Template template = manager.getTemplate(server, loc);
		PlacementSettings placeSettings = (new PlacementSettings()).setRotation(rot).setChunk((ChunkPos) null).setReplacedBlock((Block) null);
		
		if (template == null) {
			return false;
		}
		
		template.addBlocksToWorld(world, pos, placeSettings);
		
		return true;
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		return true;
	}
	
	public ResourceLocation getRandomPart() {
		Random rand = new Random();
		return CatacombParts.allParts[rand.nextInt(CatacombParts.allParts.length)];
	}
}

package com.github.reallysub.angels.common.structures;

import com.github.reallysub.angels.main.WeepingAngels;
import net.minecraft.block.Block;
import net.minecraft.init.Biomes;
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

import java.util.Random;

public class WorldGenCatacombs extends WorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
			if (random.nextInt(4000) == 0) {
				int x = chunkX * 16 + random.nextInt(16);
				int y = 25;
				int z = chunkZ * 16 + random.nextInt(16);
				
				while (!world.getBlockState(new BlockPos(x, y, z)).isFullBlock() && y > 0) {
					y--;
				}
				
				System.out.println(new BlockPos(x + 2, y + 2, z));
				
				for (int times = 0; times <= 12; times++) {
					generate(world, random, new BlockPos(x, y, z), Rotation.NONE);
					generate(world, random, new BlockPos(x + 6 * times, y, z), Rotation.NONE);
				}
			}
		}
	}
	
	public boolean generate(World world, Random rand, BlockPos pos, Rotation rot) {
		MinecraftServer server = world.getMinecraftServer();
		TemplateManager manager = world.getSaveHandler().getStructureTemplateManager();
		
		ResourceLocation resource = new ResourceLocation(WeepingAngels.MODID, "catacomb/catacomb_hallway_" + rand.nextInt(3));
		Template template = manager.getTemplate(server, resource);
		PlacementSettings placeSettings = (new PlacementSettings()).setRotation(rot).setChunk((ChunkPos) null).setReplacedBlock((Block) null);
		
		if (template == null) {
			return false;
		}
		
		if (resource.getResourcePath().equals("catacomb/catacomb_hallway_3")) {
			System.out.println("cross detected");
			for (int times = 0; times <= 8; times++) {
				generate(world, rand, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 7 * times), Rotation.CLOCKWISE_90);
				generate(world, rand, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 7 * times), Rotation.CLOCKWISE_90);
			}
		}
		
		template.addBlocksToWorld(world, pos, placeSettings);
		
		return true;
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		return true;
	}
}

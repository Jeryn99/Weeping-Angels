package com.github.reallysub.angels.common.structures;

import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
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
import net.minecraft.world.storage.loot.LootTableList;
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
				
				WorldGenCatacombs.generate(world, rand, new BlockPos(x - 6, y, z), Rotation.NONE, CatacombParts.partTSection);
				
				// 8 Corridors
				for (int times = 0; times <= 12; times++) {
					
					ResourceLocation part = CatacombParts.partStraight;
					
					if (times == 6) {
						part = CatacombParts.partCrossSection;
					} else {
						part = CatacombParts.partStraight;
					}
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x + 6 * times, y, z), Rotation.NONE, part);
					WorldGenCatacombs.generate(world, rand, new BlockPos(x - 6 * times, y, z), Rotation.NONE, CatacombParts.partStraight);
				}
				
				// Corridor
				WorldGenCatacombs.generate(world, rand, new BlockPos(x + 6 + 72, y, z), Rotation.NONE, CatacombParts.partCrossSection);
				
				for (int times = 0; times <= 12; times++) {
					
					ResourceLocation part;
					
					if (times == 4) {
						if (rand.nextBoolean()) {
							part = CatacombParts.partCrossSection;
						} else {
							part = CatacombParts.partTSection;
						}
					} else {
						part = CatacombParts.getStraightPart();
					}
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x, y, z + 6 * times), Rotation.CLOCKWISE_90, part);
					
					WorldGenCatacombs.generate(world, rand, new BlockPos(x, y, z - 6 * times), Rotation.CLOCKWISE_90, CatacombParts.getStraightPart());
					
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
		
		Map<BlockPos, String> map = template.getDataBlocks(pos, placeSettings);
		
		for (Map.Entry<BlockPos, String> entry : map.entrySet()) {
			if ("chest".equals(entry.getValue())) {
				BlockPos blockpos2 = entry.getKey();
				world.setBlockState(blockpos2.up(), Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(blockpos2);
				
				if (tileentity instanceof TileEntityChest) {
					TileEntityChest chest = (TileEntityChest) tileentity;
					chest.setLootTable(LootTableList.ENTITIES_WITCH, rand.nextLong());
				}
			}
		}
		
		return true;
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos position) {
		return true;
	}
	
}

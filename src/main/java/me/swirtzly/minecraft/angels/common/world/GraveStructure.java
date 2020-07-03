package me.swirtzly.minecraft.angels.common.world;

import com.mojang.datafixers.Dynamic;
import me.swirtzly.minecraft.angels.WeepingAngels;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import org.apache.logging.log4j.Level;

import java.util.Random;
import java.util.function.Function;

public class GraveStructure extends Structure<NoFeatureConfig> {
	public GraveStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> config) {
		super(config);
	}
	
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
		int maxDistance = 12;
		int minDistance = 7;
		
		int xTemp = x + maxDistance * spacingOffsetsX;
		int ztemp = z + maxDistance * spacingOffsetsZ;
		int xTemp2 = xTemp < 0 ? xTemp - maxDistance + 1 : xTemp;
		int zTemp2 = ztemp < 0 ? ztemp - maxDistance + 1 : ztemp;
		int validChunkX = xTemp2 / maxDistance;
		int validChunkZ = zTemp2 / maxDistance;
		
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), validChunkX, validChunkZ, this.getSeedModifier());
		validChunkX = validChunkX * maxDistance;
		validChunkZ = validChunkZ * maxDistance;
		validChunkX = validChunkX + random.nextInt(maxDistance - minDistance);
		validChunkZ = validChunkZ + random.nextInt(maxDistance - minDistance);
		
		return new ChunkPos(validChunkX, validChunkZ);
	}
	
	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {
		ChunkPos chunkpos = this.getStartPositionForPosition(chunkGen, rand, chunkPosX, chunkPosZ, 0, 0);
		
		// Checks to see if current chunk is valid to spawn in.
		if (chunkPosX == chunkpos.x && chunkPosZ == chunkpos.z) {
			// Checks if the biome can spawn this structure.
			return chunkGen.hasStructure(chunkGen.getBiomeProvider().getBiome(chunkPosX, chunkPosZ), this);
		}
		
		return false;
	}
	
	public String getStructureName() {
		return WeepingAngels.MODID + ":graves/graveyard_1";
	}
	
	public int getSize() {
		return 0;
	}
	
	public Structure.IStartFactory getStartFactory() {
		return Start::new;
	}
	
	protected int getSeedModifier() {
		return 123456789;
	}
	
	public static class Start extends StructureStart {
		
		public Start(Structure<?> p_i51341_1_, int chunkX, int chunkZ, Biome biomeIn, MutableBoundingBox boundsIn, int referenceIn, long seed) {
			super(p_i51341_1_, chunkX, chunkZ, biomeIn, boundsIn, referenceIn, seed);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
			
			int x = (chunkX << 4) + 7;
			int z = (chunkZ << 4) + 7;
			
			int surfaceY = generator.func_222531_c(x, z, Heightmap.Type.WORLD_SURFACE_WG);
			BlockPos blockpos = new BlockPos(x, surfaceY, z);
			
			GraveyardPieces.start(templateManagerIn, blockpos, rotation, this.components, this.rand);
			this.recalculateStructureSize();
			
			WeepingAngels.LOGGER.log(Level.DEBUG, "Rundown House at " + (blockpos.getX()) + " " + blockpos.getY() + " " + (blockpos.getZ()));
		}
		
	}
}

package me.swirtzly.minecraft.angels.common.world.structures;


import java.util.List;

import org.apache.logging.log4j.Level;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;

import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class GraveyardStructure extends Structure<ProbabilityConfig>{

	public GraveyardStructure(Codec<ProbabilityConfig> codec) {
		super(codec);
	}
   
	
	/**
     * || ONLY WORKS IN FORGE 34.1.12+ ||
     *
     * <br>This method allows us to have mobs that spawn naturally over time in our structure.
     * No other mobs will spawn in the structure of the same entity classification.
     * <br> The reason you want to match the classifications is so that your structure's mob
     * will contribute to that classification's cap. Otherwise, it may cause a runaway
     * spawning of the mob that will never stop.
     *
     * <br> NOTE: getDefaultSpawnList is for monsters only and getDefaultCreatureSpawnList is
     *       for creatures only. 
     *       <br> If you want to add entities of another classification,
     *       use the StructureSpawnListGatherEvent to add water_creatures, water_ambient,
     *       ambient, or misc mobs. 
     *       <br> Use that event to add/remove mobs from structures
     *       that are not your own.
     */
    private static final List<MobSpawnInfo.Spawners> STRUCTURE_MONSTERS = ImmutableList.of(
            new MobSpawnInfo.Spawners(WAObjects.EntityEntries.WEEPING_ANGEL.get(), 100, 4, 9)
    );
    
    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return STRUCTURE_MONSTERS;
    }

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.BAT, 30, 10, 15),
            new MobSpawnInfo.Spawners(EntityType.CAT, 100, 1, 2)
    );
    
    @Override
    public List<MobSpawnInfo.Spawners> getDefaultCreatureSpawnList() {
        return STRUCTURE_CREATURES;
    }
	
    //Required, sets the Structure Start settings
	@Override
	public IStartFactory<ProbabilityConfig> getStartFactory() {
		return GraveyardStructure.Start::new;
	}
	
	//Required, otherwise will cause NPE Crash
	@Override
    public Decoration getDecorationStage() {
        return Decoration.SURFACE_STRUCTURES;
    }
	
	public static class Start extends StructureStart<ProbabilityConfig>  {

		public Start(Structure<ProbabilityConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void func_230364_a_(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, ProbabilityConfig config) {
        	
        	Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
        	// Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        	int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            // Finds the y value of the terrain at location.
            int surfaceY = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos blockpos = new BlockPos(x, surfaceY, z); 
            
            
            // Now adds the structure pieces to this.components with all details such as where each part goes
            // so that the structure can be added to the world by worldgen.
            GraveyardStructurePieces.start(templateManagerIn, blockpos, rotation, this.components, this.rand);
            // Sets the bounds of the structure.
            this.recalculateStructureSize();
            WeepingAngels.LOGGER.log(Level.INFO, "Graveyard at " + (blockpos.getX()) + " " + blockpos.getY() + " " + (blockpos.getZ()));
        }
		
	}

}

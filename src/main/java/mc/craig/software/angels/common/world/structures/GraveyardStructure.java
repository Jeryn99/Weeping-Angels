package mc.craig.software.angels.common.world.structures;


import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
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
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Collections;
import java.util.List;

public class GraveyardStructure extends Structure<ProbabilityConfig> {

    private static final List<MobSpawnInfo.Spawners> STRUCTURE_CREATURES = ImmutableList.of(
            new MobSpawnInfo.Spawners(EntityType.BAT, 60, 10, 15));

    public GraveyardStructure(Codec<ProbabilityConfig> codec) {
        super(codec);
    }

    @Override
    public List<MobSpawnInfo.Spawners> getDefaultSpawnList() {
        return Collections.emptyList();
    }

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
    public Decoration step() {
        return Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<ProbabilityConfig> {

        public Start(Structure<ProbabilityConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox mutableBoundingBox, int referenceIn, long seedIn) {
            super(structureIn, chunkX, chunkZ, mutableBoundingBox, referenceIn, seedIn);
        }

        @Override
        public void generatePieces(DynamicRegistries dynamicRegistryManager, ChunkGenerator chunkGenerator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, ProbabilityConfig config) {
            Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
            int x = (chunkX << 4) + 7;
            int z = (chunkZ << 4) + 7;
            int surfaceY = chunkGenerator.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos blockpos = new BlockPos(x, surfaceY, z);
            GraveyardStructurePieces.start(templateManagerIn, blockpos, rotation, this.pieces, this.random);
            this.calculateBoundingBox();
        }

    }

}

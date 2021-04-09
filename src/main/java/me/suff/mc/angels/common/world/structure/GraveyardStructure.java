package me.suff.mc.angels.common.world.structure;

import com.mojang.serialization.Codec;
import me.suff.mc.angels.util.WAConfig;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

/* Created by Craig on 23/02/2021 */
public class GraveyardStructure extends StructureFeature< DefaultFeatureConfig > {
    public GraveyardStructure(Codec< DefaultFeatureConfig > codec) {
        super(codec);
    }

    @Override
    public StructureFeature.StructureStartFactory< DefaultFeatureConfig > getStructureStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart< DefaultFeatureConfig > {
        public Start(StructureFeature< DefaultFeatureConfig > feature, int chunkX, int chunkZ, BlockBox box, int references, long seed) {
            super(feature, chunkX, chunkZ, box, references, seed);
        }

        // Called when the world attempts to spawn in a new structure, and is the gap between your feature and generator.
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, int chunkX, int chunkZ, Biome biome, DefaultFeatureConfig config) {
            if(!WAConfig.WorldConfig.graveyards.getValue()) return;
            int x = chunkX * 16;
            int z = chunkZ * 16;
            int y = chunkGenerator.getHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            BlockPos pos = new BlockPos(x, y, z);
            BlockRotation rotation = BlockRotation.random(this.random);
            GraveyardPieces.addPieces(manager, pos, rotation, this.children);
            this.setBoundingBoxFromChildren();
        }
    }
}

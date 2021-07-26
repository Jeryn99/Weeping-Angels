package me.suff.mc.angels.common.world.structures;

import com.mojang.serialization.Codec;
import me.suff.mc.angels.WeepingAngels;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElementType;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.OceanRuinPieces;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Optional;

public class CatacombStructure extends StructureFeature<NoneFeatureConfiguration> {

    public CatacombStructure(Codec<NoneFeatureConfiguration> p_67039_) {
        super(p_67039_);
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return FeatureStart::new;
    }

    public static class FeatureStart extends StructureStart<NoneFeatureConfiguration> {
        public FeatureStart(StructureFeature<NoneFeatureConfiguration> p_160489_, ChunkPos p_160490_, int p_160491_, long p_160492_) {
            super(p_160489_, p_160490_, p_160491_, p_160492_);
        }


        @Override
        public void generatePieces(RegistryAccess dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos p_160505_, Biome p_160506_, NoneFeatureConfiguration p_160507_, LevelHeightAccessor heightLimitView) {

            int x = getChunkPos().x * 16;
            int z = getChunkPos().z * 16;

            BlockPos.MutableBlockPos centerPos = new BlockPos.MutableBlockPos(x, 0, z);

            Optional<? extends Registry<StructureTemplatePool>> optional = dynamicRegistryManager.registry(Registry.TEMPLATE_POOL_REGISTRY);
            optional.ifPresent(structurePoolElementTypes -> {

                JigsawPlacement jigsawPlacement = new JigsawPlacement(() -> structurePoolElementTypes.get(new ResourceLocation(WeepingAngels.MODID, "run_down_house/start_pool")), 10);

                // All a structure has to do is call this method to turn it into a jigsaw based structure!
                PoolElementStructurePiece.generate(
                        dynamicRegistryManager,
                        structureManager,
                        OceanRuinPieces::new,
                        chunkGenerator,
                        structureManager,
                        centerPos, // Position of the structure. Y value is ignored if last parameter is set to true.
                        this, // The class instance that holds the list that will be populated with the jigsaw pieces after this method.
                        this.random,
                        false, // Special boundary adjustments for villages. It's... hard to explain. Keep this false and make your pieces not be partially intersecting.
                        // Either not intersecting or fully contained will make children pieces spawn just fine. It's easier that way.
                        true, // Place at heightmap (top land). Set this to false for structure to be place at the passed in blockpos's Y value instead.
                        // Definitely keep this false when placing structures in the nether as otherwise, heightmap placing will put the structure on the Bedrock roof.
                        heightLimitView);

                this.pieces.forEach(piece -> piece.move(0, 1, 0));
                this.pieces.forEach(piece -> piece.getBoundingBox().move(0, -1, 0));

                Vec3i structureCenter = this.pieces.get(0).getBoundingBox().getCenter();
                int xOffset = centerPos.getX() - structureCenter.getX();
                int zOffset = centerPos.getZ() - structureCenter.getZ();
                for (StructurePiece structurePiece : this.pieces) {
                    structurePiece.move(xOffset, 0, zOffset);
                }

                this.createBoundingBox();
            });
        }

    }
}
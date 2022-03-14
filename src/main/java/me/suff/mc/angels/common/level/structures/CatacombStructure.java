package me.suff.mc.angels.common.level.structures;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CatacombStructure extends StructureFeature<JigsawConfiguration> {

    protected static final String[] variants = new String[]{"flat", "clean", "broken", "normal", "classic"};

    public CatacombStructure() {
        super(JigsawConfiguration.CODEC, CatacombStructure::createPiecesGenerator, PostPlacementProcessor.NONE);
    }


    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context) {
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenrandom.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
        return context.validBiome().test(context.chunkGenerator().getNoiseBiome(QuartPos.fromBlock(context.chunkPos().getMiddleBlockX()), QuartPos.fromBlock(50), QuartPos.fromBlock(context.chunkPos().getMiddleBlockZ()))) && WAConfig.CONFIG.genCatacombs.get();
    }


    private static @NotNull Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> jigsawConfigurationContext) {

        // Check if the spot is valid for our structure. This is just as another method for cleanness.
        // Returning an empty optional tells the game to skip this spot as it will not generate the structure.
        if (!CatacombStructure.isFeatureChunk(jigsawConfigurationContext)) {
            return Optional.empty();
        }

        // Turns the chunk coordinates into actual coordinates we can use. (Gets center of that chunk)
        BlockPos blockpos = jigsawConfigurationContext.chunkPos().getMiddleBlockPosition(0);

        // Random Y value between -30, -40
        int yPos = Mth.randomBetweenInclusive(AngelUtil.RAND, 30, 40) * -1;

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        jigsawConfigurationContext,
                        PoolElementStructurePiece::new,
                        blockpos.atY(yPos),
                        false,
                        false
                );

        if (structurePiecesGenerator.isPresent()) {
            WeepingAngels.LOGGER.log(Level.INFO, "Catacomb Structure at {}", blockpos);
        }

        return structurePiecesGenerator;
    }


    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.UNDERGROUND_STRUCTURES;
    }
}
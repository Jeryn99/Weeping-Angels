package me.suff.mc.angels.common.level.structures;

import com.mojang.serialization.Codec;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.level.WAFeatures;
import me.suff.mc.angels.common.level.WAPieces;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;

public class GraveyardStructure extends Structure {

    public static final Codec<GraveyardStructure> CODEC = simpleCodec(GraveyardStructure::new);

    public GraveyardStructure(StructureSettings structureSettings) {
        super(structureSettings);
    }

    @Override
    public @NotNull Optional<GenerationStub> findGenerationPoint(Structure.@NotNull GenerationContext p_227595_) {
        return onTopOfChunkCenter(p_227595_, Heightmap.Types.WORLD_SURFACE_WG, (p_227598_) -> {
            this.generatePieces(p_227598_, p_227595_);
        });
    }

    private void generatePieces(StructurePiecesBuilder structurePiecesBuilder, Structure.GenerationContext generationContext) {
        ChunkPos chunkpos = generationContext.chunkPos();
        WorldgenRandom worldgenrandom = generationContext.random();
        BlockPos blockpos = new BlockPos(chunkpos.getMinBlockX(), 90, chunkpos.getMinBlockZ());
        Rotation rotation = Rotation.getRandom(worldgenrandom);
        GraveyardStructure.addPiece(generationContext.structureTemplateManager(), blockpos, rotation, structurePiecesBuilder, worldgenrandom);
    }

    private static void addPiece(StructureTemplateManager structureManager, BlockPos blockPos, Rotation rotation, StructurePiecesBuilder structurePieceAccessor, WorldgenRandom random) {
        ResourceLocation piece = GraveyardPiece.ALL_GRAVES[random.nextInt(GraveyardPiece.ALL_GRAVES.length)];
        structurePieceAccessor.addPiece(new GraveyardPiece(0, structureManager, piece, piece.toString(), GraveyardPiece.makeSettings(rotation), blockPos));
    }

    @Override
    public GenerationStep.@NotNull Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    @Override
    public @NotNull StructureType<?> type() {
        return WAFeatures.GRAVEYARD.get();
    }

    public static class GraveyardPiece extends TemplateStructurePiece {

        private static final ResourceLocation GRAVEYARD_1 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_1");
        private static final ResourceLocation GRAVEYARD_2 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_2");
        private static final ResourceLocation GRAVEYARD_3 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_3");
        private static final ResourceLocation GRAVEYARD_4 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_4");
        private static final ResourceLocation GRAVEYARD_5 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_5");
        private static final ResourceLocation GRAVEYARD_6 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_6");
        private static final ResourceLocation GRAVEYARD_WALKWAY = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_walkway");
        private static final ResourceLocation GRAVEYARD_LARGE_ONE = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_lrg_1");
        private static final ResourceLocation GRAVEYARD_LARGE_TWO = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_lrg_2");
        private static final ResourceLocation[] ALL_GRAVES = new ResourceLocation[]{GRAVEYARD_1, GRAVEYARD_2, GRAVEYARD_3, GRAVEYARD_4, GRAVEYARD_5, GRAVEYARD_6, GRAVEYARD_WALKWAY, GRAVEYARD_LARGE_ONE, GRAVEYARD_LARGE_TWO};
        private static String[] USERNAMES = new String[]{};

        public GraveyardPiece(int p_163661_, StructureTemplateManager p_163662_, ResourceLocation p_163663_, String p_163664_, StructurePlaceSettings p_163665_, BlockPos p_163666_) {
            super(WAPieces.GRAVEYARD, p_163661_, p_163662_, p_163663_, p_163664_, p_163665_, p_163666_);
        }

        public GraveyardPiece(CompoundTag p_192678_, StructureTemplateManager p_192679_, Function<ResourceLocation, StructurePlaceSettings> p_192680_) {
            super(WAPieces.GRAVEYARD, p_192678_, p_192679_, p_192680_);
        }

        public GraveyardPiece(StructurePieceSerializationContext structurePieceSerializationContext, CompoundTag tag) {
            super(WAPieces.GRAVEYARD, tag, structurePieceSerializationContext.structureTemplateManager(), (p_162451_) -> makeSettings(Rotation.getRandom(RandomSource.create())));
        }


        private static StructurePlaceSettings makeSettings(Rotation rot) {
            return (new StructurePlaceSettings()).setRotation(rot).setMirror(Mirror.NONE).setRotationPivot(BlockPos.ZERO).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }


        public static String createRandomDate() {
            long startEpochDay = LocalDate.of(1800, 1, 1).toEpochDay();
            long endEpochDay = LocalDate.now().toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
            LocalDate finalDate = LocalDate.ofEpochDay(randomDay);
            return finalDate.getDayOfMonth() + "." + finalDate.getMonthValue() + "." + finalDate.getYear();

        }
        public static Block getRandomPottedPlant(RandomSource random) {
            Optional<Block> plant = ForgeRegistries.BLOCKS.tags().getTag(AngelUtil.POTTED_PLANTS).getRandomElement(random);
            return plant.get();
        }

        @Override
        protected void handleDataMarker(@NotNull String function, @NotNull BlockPos blockPos, @NotNull ServerLevelAccessor serverLevelAccessor, @NotNull RandomSource random, @NotNull BoundingBox p_73687_) {
            if (USERNAMES.length == 0) {
                try {
                    loadNames();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (ServerLifecycleHooks.getCurrentServer().isDedicatedServer()) {
                USERNAMES = ArrayUtils.addAll(USERNAMES, ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerNamesArray());
            }

            if ("angel".equals(function)) {
                StatueBlockEntity statueTile = (StatueBlockEntity) serverLevelAccessor.getBlockEntity(blockPos.below());
                statueTile.setPose(WeepingAngelPose.HIDING);
                statueTile.setAngelType(AngelType.DISASTER_MC);
                statueTile.setAngelVarients(AngelType.DISASTER_MC.getWeightedHandler().getRandom(null));
                statueTile.setChanged();
                serverLevelAccessor.removeBlock(blockPos, false);
            }

            if ("coffin".equals(function)) {
                CoffinBlockEntity coffinTile = (CoffinBlockEntity) serverLevelAccessor.getBlockEntity(blockPos.below());
                if (coffinTile != null) {
                    coffinTile.setOpen(random.nextBoolean());
                    coffinTile.setCoffin(AngelUtil.randomCoffin());
                    coffinTile.setHasSkeleton(random.nextBoolean());
                    serverLevelAccessor.removeBlock(blockPos, false);
                }
            }

            if ("cobweb".equals(function)) {
                Block block = random.nextBoolean() ? Blocks.COBWEB : Blocks.AIR;
                serverLevelAccessor.setBlock(blockPos, block.defaultBlockState(), 2);
            }

            if ("crypt_chest".equals(function) || "chest".equals(function)) {
                RandomizableContainerBlockEntity.setLootTable(serverLevelAccessor, random, blockPos.below(), WAObjects.CRYPT_LOOT);
                serverLevelAccessor.removeBlock(blockPos, false);
            }

            if ("chest_2down".equals(function)) {
                RandomizableContainerBlockEntity.setLootTable(serverLevelAccessor, random, blockPos.below(2), WAObjects.CRYPT_LOOT);
                serverLevelAccessor.removeBlock(blockPos.below(2), false);
                serverLevelAccessor.removeBlock(blockPos, false);
            }

            if ("path".equals(function)) {
                serverLevelAccessor.setBlock(blockPos, random.nextBoolean() ? Blocks.AIR.defaultBlockState() : getRandomPottedPlant(random).defaultBlockState(), 2);
                serverLevelAccessor.setBlock(blockPos.below(), Blocks.PODZOL.defaultBlockState(), 2);
            }

            if ("sign".equals(function)) {
                if (serverLevelAccessor.getBlockState(blockPos.below()).getBlock() instanceof WallSignBlock) {
                    SignBlockEntity signTileEntity = (SignBlockEntity) serverLevelAccessor.getBlockEntity(blockPos.below());
                    if (signTileEntity != null) {
                        signTileEntity.setMessage(0, Component.translatable("========"));
                        signTileEntity.setMessage(1, Component.translatable((USERNAMES[random.nextInt(USERNAMES.length - 1)])));
                        signTileEntity.setMessage(2, Component.translatable(("Died: " + createRandomDate())));
                        signTileEntity.setMessage(3, Component.translatable(("========")));
                        serverLevelAccessor.removeBlock(blockPos, false);
                        serverLevelAccessor.setBlock(blockPos.below(2), Blocks.PODZOL.defaultBlockState(), 2);
                    }
                } else {
                    serverLevelAccessor.removeBlock(blockPos, false);
                }
            }
        }

        private void loadNames() throws IOException {
            ResourceLocation resourceLocation = new ResourceLocation(WeepingAngels.MODID, "names.json");
            InputStream stream = ServerLifecycleHooks.getCurrentServer().getServerResources().resourceManager().getResource(resourceLocation).get().open();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
            stream.close();
            String[] names = GSON.fromJson(sb.toString(), String[].class);
            if (names != null) {
                USERNAMES = names;
            }
        }
    }

}

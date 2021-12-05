package me.suff.mc.angels.common.level.structures;

import com.mojang.serialization.Codec;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.blockentities.CoffinBlockEntity;
import me.suff.mc.angels.common.blockentities.StatueBlockEntity;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix.GSON;

public class GraveyardStructure extends StructureFeature<NoneFeatureConfiguration> {


    public GraveyardStructure(Codec<NoneFeatureConfiguration> p_67039_) {
        super(p_67039_);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return FeatureStart::new;
    }

    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public static class FeatureStart extends StructureStart<NoneFeatureConfiguration> {
        public FeatureStart(StructureFeature<NoneFeatureConfiguration> p_159888_, ChunkPos p_159889_, int p_159890_, long p_159891_) {
            super(p_159888_, p_159889_, p_159890_, p_159891_);
        }

        @Override
        public void generatePieces(RegistryAccess p_159901_, ChunkGenerator p_159902_, StructureManager p_159903_, ChunkPos chunkPos, Biome p_159905_, NoneFeatureConfiguration p_159906_, LevelHeightAccessor p_159907_) {

            int k = chunkPos.getBlockX(7);
            int l = chunkPos.getBlockZ(7);
            int i1 = p_159902_.getFirstOccupiedHeight(k, l, Heightmap.Types.WORLD_SURFACE_WG, p_159907_);
            int j1 = p_159902_.getFirstOccupiedHeight(k, l + 5, Heightmap.Types.WORLD_SURFACE_WG, p_159907_);
            int k1 = p_159902_.getFirstOccupiedHeight(k + 5, l, Heightmap.Types.WORLD_SURFACE_WG, p_159907_);
            int l1 = p_159902_.getFirstOccupiedHeight(k + 5, l + 5, Heightmap.Types.WORLD_SURFACE_WG, p_159907_);
            int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));
            if (i2 >= 60) {
                BlockPos blockpos = new BlockPos(chunkPos.getMinBlockX(), i2, chunkPos.getMinBlockZ());
                Rotation rotation = Rotation.getRandom(this.random);
                GraveyardPiece.addPieces(p_159903_, blockpos, rotation, this, this.random);
            }
        }
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

        public GraveyardPiece(StructureManager p_71244_, ResourceLocation p_71245_, BlockPos p_71246_, Rotation p_71247_, int p_71248_) {
            super(WAPieces.GRAVEYARD, 0, p_71244_, p_71245_, p_71245_.toString(), makeSettings(p_71247_, p_71245_), makePosition(p_71245_, p_71246_, p_71248_));
        }

        public GraveyardPiece(ServerLevel p_162441_, CompoundTag p_162442_) {
            super(WAPieces.GRAVEYARD, p_162442_, p_162441_, (p_162451_) -> makeSettings(Rotation.valueOf(p_162442_.getString("Rot")), p_162451_));

        }

        private static StructurePlaceSettings makeSettings(Rotation p_162447_, ResourceLocation p_162448_) {
            return (new StructurePlaceSettings()).setRotation(p_162447_).setMirror(Mirror.NONE).setRotationPivot(BlockPos.ZERO).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }


        private static BlockPos makePosition(ResourceLocation p_162453_, BlockPos p_162454_, int p_162455_) {

            if (p_162453_ == GRAVEYARD_LARGE_ONE || p_162453_ == GRAVEYARD_LARGE_TWO || p_162453_ == GRAVEYARD_WALKWAY) {
                return p_162454_.below(3);
            }
            return p_162454_;
        }

        public static void addPieces(StructureManager p_162435_, BlockPos p_162436_, Rotation p_162437_, StructurePieceAccessor p_162438_, Random p_162439_) {
            p_162438_.addPiece(new GraveyardPiece(p_162435_, ALL_GRAVES[p_162439_.nextInt(ALL_GRAVES.length)], p_162436_, p_162437_, 0));
        }

        public static LocalDate createRandomDate() {
            long startEpochDay = LocalDate.of(1800, 1, 1).toEpochDay();
            long endEpochDay = LocalDate.now().toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
            return LocalDate.ofEpochDay(randomDay);
        }

        public static Block getRandomPottedPlant(Random random) {
            List<Block> plants = AngelUtil.POTTED_PLANTS.getValues();
            return plants.get(random.nextInt(plants.size()));
        }

        protected void addAdditionalSaveData(ServerLevel p_162444_, CompoundTag p_162445_) {
            super.addAdditionalSaveData(p_162444_, p_162445_);
            p_162445_.putString("Rot", this.placeSettings.getRotation().name());
        }

        protected void handleDataMarker(String function, BlockPos blockPos, ServerLevelAccessor serverLevelAccessor, Random random, BoundingBox boundingBox) {
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
                        signTileEntity.setMessage(0, new TranslatableComponent("========"));
                        signTileEntity.setMessage(1, new TranslatableComponent(USERNAMES[random.nextInt(USERNAMES.length - 1)]));
                        signTileEntity.setMessage(2, new TranslatableComponent(createRandomDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
                        signTileEntity.setMessage(3, new TranslatableComponent("========"));
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
            InputStream stream = ServerLifecycleHooks.getCurrentServer().getServerResources().getResourceManager().getResource(resourceLocation).getInputStream();
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

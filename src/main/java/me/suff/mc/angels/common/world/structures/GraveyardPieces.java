package me.suff.mc.angels.common.world.structures;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.fixes.BlockEntitySignTextStrictJsonFix;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import static me.suff.mc.angels.WeepingAngels.MODID;

public class GraveyardPieces {

    private static final ResourceLocation GRAVEYARD_1 = new ResourceLocation(MODID, "graves/graveyard_1");
    private static final ResourceLocation GRAVEYARD_2 = new ResourceLocation(MODID, "graves/graveyard_2");
    private static final ResourceLocation GRAVEYARD_3 = new ResourceLocation(MODID, "graves/graveyard_3");
    private static final ResourceLocation GRAVEYARD_4 = new ResourceLocation(MODID, "graves/graveyard_4");
    private static final ResourceLocation GRAVEYARD_5 = new ResourceLocation(MODID, "graves/graveyard_5");
    private static final ResourceLocation GRAVEYARD_6 = new ResourceLocation(MODID, "graves/graveyard_6");
    private static final ResourceLocation GRAVEYARD_WALKWAY = new ResourceLocation(MODID, "graves/graveyard_walkway");
    private static final ResourceLocation GRAVEYARD_LARGE_ONE = new ResourceLocation(MODID, "graves/graveyard_lrg_1");
    private static final ResourceLocation GRAVEYARD_LARGE_TWO = new ResourceLocation(MODID, "graves/graveyard_lrg_2");
    private static final ResourceLocation[] ALL_GRAVES = new ResourceLocation[]{GRAVEYARD_1, GRAVEYARD_2, GRAVEYARD_3, GRAVEYARD_4, GRAVEYARD_5, GRAVEYARD_6, GRAVEYARD_WALKWAY, GRAVEYARD_LARGE_ONE, GRAVEYARD_LARGE_TWO};
    private static String[] USERNAMES = new String[]{};


    public static void start(StructurePieceType structurePieceType, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
        int x = pos.getX();
        int z = pos.getZ();
        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
        // pieceList.add(new GraveyardStructurePieces.Piece(structurePieceType, 0, ));
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
        String[] names = BlockEntitySignTextStrictJsonFix.GSON.fromJson(sb.toString(), String[].class);
        if (names != null) {
            USERNAMES = names;
        }
    }

    public static class Piece extends TemplateStructurePiece {


        public Piece(StructurePieceType p_163660_, int p_163661_, StructureManager p_163662_, ResourceLocation p_163663_, String p_163664_, StructurePlaceSettings p_163665_, BlockPos p_163666_) {
            super(p_163660_, p_163661_, p_163662_, p_163663_, p_163664_, p_163665_, p_163666_);
        }

        public Piece(StructurePieceType p_163668_, CompoundTag p_163669_, ServerLevel p_163670_, Function<ResourceLocation, StructurePlaceSettings> p_163671_) {
            super(p_163668_, p_163669_, p_163670_, p_163671_);
        }

        @Override
        protected void handleDataMarker(String p_73683_, BlockPos p_73684_, ServerLevelAccessor p_73685_, Random p_73686_, BoundingBox p_73687_) {

        }
    }

}

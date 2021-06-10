package me.suff.mc.angels.common.world.structures;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.common.entities.AngelEnums;
import me.suff.mc.angels.common.tileentities.CoffinTile;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallSignBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
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

import static me.suff.mc.angels.WeepingAngels.MODID;

public class GraveyardStructurePieces {

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


    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
        int x = pos.getX();
        int z = pos.getZ();
        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.offset(x, pos.getY(), z);
        pieceList.add(new GraveyardStructurePieces.Piece(templateManager, /*ALL_GRAVES[random.nextInt(ALL_GRAVES.length)]*/GRAVEYARD_LARGE_ONE, blockpos, rotation));
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

    public static class Piece extends TemplateStructurePiece {
        private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(WAObjects.Structures.GRAVEYARD_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockpos = BlockPos.ZERO;

            if (resourceLocation == GRAVEYARD_LARGE_ONE || resourceLocation == GRAVEYARD_LARGE_TWO) {
                blockpos = blockpos.below(9);
            }

            if (resourceLocation == GRAVEYARD_WALKWAY) {
                blockpos = blockpos.below(6);
            }
            this.templatePosition = pos.offset(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            this.rotation = rotationIn;
            this.setupPiece(templateManagerIn);
        }

        public Piece(TemplateManager templateManagerIn, CompoundNBT tagCompound) {
            super(WAObjects.Structures.GRAVEYARD_PIECE, tagCompound);
            this.resourceLocation = new ResourceLocation(tagCompound.getString("Template"));
            this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
            this.setupPiece(templateManagerIn);
        }

        private void setupPiece(TemplateManager templateManager) {
            Template template = templateManager.getOrCreate(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

            if (ServerLifecycleHooks.getCurrentServer().isDedicatedServer()) {
                USERNAMES = ArrayUtils.addAll(USERNAMES, ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayerNamesArray());
            }

            if (USERNAMES.length == 0) {
                try {

                    ResourceLocation resourceLocation = new ResourceLocation(WeepingAngels.MODID, "names.json");
                    InputStream stream = ServerLifecycleHooks.getCurrentServer().getDataPackRegistries().getResourceManager().getResource(resourceLocation).getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                    stream.close();
                    String[] splashes = GSON.fromJson(sb.toString(), String[].class);
                    if (splashes != null) {
                        USERNAMES = splashes;
                    }
                } catch (IOException e) {
                    WeepingAngels.LOGGER.catching(e);
                }

            }

            if ("angel".equals(function)) {
                StatueTile statueTile = (StatueTile) worldIn.getBlockEntity(pos.below());
                statueTile.setPose(WeepingAngelPose.HIDING);
                statueTile.setAngelType(AngelEnums.AngelType.ANGELA_MC);
                statueTile.setAngelVarients(AngelUtil.randomVarient());
                statueTile.setChanged();
                worldIn.removeBlock(pos, false);
            }

            if ("coffin".equals(function)) {
                CoffinTile coffinTile = (CoffinTile) worldIn.getBlockEntity(pos.below());
                if (coffinTile != null) {
                    coffinTile.setOpen(rand.nextBoolean());
                    coffinTile.setCoffin(AngelUtil.randomCoffin());
                    coffinTile.setHasSkeleton(rand.nextBoolean());
                    worldIn.removeBlock(pos, false);
                }
            }

            if ("cobweb".equals(function)) {
                Block block = rand.nextBoolean() ? Blocks.COBWEB : Blocks.AIR;
                worldIn.setBlock(pos, block.defaultBlockState(), 2);
            }

            if ("crypt_chest".equals(function) || "chest".equals(function)) {
                LockableLootTileEntity.setLootTable(worldIn, rand, pos.below(), WAObjects.CRYPT_LOOT);
                worldIn.removeBlock(pos, false);
            }

            if ("chest_2down".equals(function)) {
                LockableLootTileEntity.setLootTable(worldIn, rand, pos.below(2), WAObjects.CRYPT_LOOT);
                worldIn.removeBlock(pos.below(2), false);
                worldIn.removeBlock(pos, false);
            }

            if ("path".equals(function)) {
                worldIn.setBlock(pos, rand.nextBoolean() ? Blocks.AIR.defaultBlockState() : getRandomPottedPlant(rand).defaultBlockState(), 2);
                worldIn.setBlock(pos.below(), Blocks.PODZOL.defaultBlockState(), 2);
            }

            if ("sign".equals(function)) {
                if (worldIn.getBlockState(pos.below()).getBlock() instanceof WallSignBlock) {
                    SignTileEntity signTileEntity = (SignTileEntity) worldIn.getBlockEntity(pos.below());
                    if (signTileEntity != null) {
                        signTileEntity.setMessage(0, new TranslationTextComponent("========"));
                        signTileEntity.setMessage(1, new TranslationTextComponent(USERNAMES[rand.nextInt(USERNAMES.length - 1)]));
                        signTileEntity.setMessage(2, new TranslationTextComponent(createRandomDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
                        signTileEntity.setMessage(3, new TranslationTextComponent("========"));
                        worldIn.removeBlock(pos, false);
                        worldIn.setBlock(pos.below(2), Blocks.PODZOL.defaultBlockState(), 2);
                    }
                } else {
                    worldIn.removeBlock(pos, false);
                }
            }
        }

    }


}

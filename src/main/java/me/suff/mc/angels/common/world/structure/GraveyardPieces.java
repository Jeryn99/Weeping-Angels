package me.suff.mc.angels.common.world.structure;

import com.google.common.collect.ImmutableMap;
import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.blockentity.CoffinTile;
import me.suff.mc.angels.common.blockentity.StatueTile;
import me.suff.mc.angels.common.objects.WALoot;
import me.suff.mc.angels.enums.WeepingAngelPose;
import me.suff.mc.angels.enums.WeepingAngelVariants;
import me.suff.mc.angels.mixin.ServerMixin;
import me.suff.mc.angels.util.AngelUtils;
import me.suff.mc.angels.util.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resource.Resource;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* Created by Craig on 23/02/2021 */
public class GraveyardPieces {

    private static final Identifier GRAVEYARD_1 = new Identifier(Constants.MODID, "graves/graveyard_1");
    private static final Identifier GRAVEYARD_2 = new Identifier(Constants.MODID, "graves/graveyard_2");
    private static final Identifier GRAVEYARD_3 = new Identifier(Constants.MODID, "graves/graveyard_3");
    private static final Identifier[] ALL_GRAVES = new Identifier[]{GRAVEYARD_1, GRAVEYARD_2, GRAVEYARD_3};
    private static final Map< Identifier, BlockPos > OFFSET = ImmutableMap.of(GRAVEYARD_1, new BlockPos(BlockPos.ZERO), GRAVEYARD_2, new BlockPos(BlockPos.ZERO), GRAVEYARD_3, new BlockPos(BlockPos.ZERO));
    private static String[] USERNAMES = new String[]{};


    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List< StructurePiece > pieces) {
        pieces.add(new MyPiece(manager, pos, ALL_GRAVES[AngelUtils.RAND.nextInt(ALL_GRAVES.length)], rotation, AngelUtils.randomVarient()));
    }

    public static class MyPiece extends SimpleStructurePiece {
        private final BlockRotation rotation;
        private final Identifier template;
        private final WeepingAngelVariants angelVariants;

        public MyPiece(StructureManager structureManager, CompoundTag compoundTag) {
            super(WAStructures.GRAVE_PIECE, compoundTag);
            this.template = new Identifier(compoundTag.getString("Template"));
            this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
            this.angelVariants = WeepingAngelVariants.getVariant(compoundTag.getString(Constants.VARIANT));
            this.initializeStructureData(structureManager);
        }

        public MyPiece(StructureManager structureManager, BlockPos pos, Identifier template, BlockRotation rotation, WeepingAngelVariants weepingAngelVariants) {
            super(WAStructures.GRAVE_PIECE, 0);
            this.pos = pos;
            this.rotation = rotation;
            this.template = template;
            this.angelVariants = weepingAngelVariants;
            this.initializeStructureData(structureManager);
        }

        private void initializeStructureData(StructureManager structureManager) {
            Structure structure = structureManager.getStructureOrBlank(this.template);
            StructurePlacementData placementData = (new StructurePlacementData())
                    .setRotation(this.rotation)
                    .setMirror(BlockMirror.NONE)
                    .addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(structure, this.pos, placementData);
        }

        protected void toNbt(CompoundTag tag) {
            super.toNbt(tag);
            tag.putString("Template", this.template.toString());
            tag.putString("Rot", this.rotation.name());
            tag.putString(Constants.VARIANT, this.angelVariants.name());
        }


        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random rand, BlockBox boundingBox) {
            if (USERNAMES.length == 0) {
                try {
                    Identifier resourceLocation = new Identifier(Constants.MODID, "names.json");
                    ServerMixin serverMixin = (ServerMixin) serverWorldAccess.toServerWorld().getServer();
                    Resource path = serverMixin.getServerManager().getResourceManager().getResource(resourceLocation);
                    InputStream stream = path.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    reader.close();
                    stream.close();
                    String[] names = WeepingAngels.GSON.fromJson(sb.toString(), String[].class);
                    if (names != null) {
                        USERNAMES = names;
                    }
                    USERNAMES = ArrayUtils.addAll(USERNAMES, serverWorldAccess.toServerWorld().getServer().getPlayerNames());

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if ("angel".equals(metadata)) {
                StatueTile statueTile = (StatueTile) serverWorldAccess.getBlockEntity(pos.down());
                statueTile.setAngelPose(WeepingAngelPose.HIDING);
                statueTile.setAngelVariant(angelVariants);
                statueTile.markDirty();
                serverWorldAccess.removeBlock(pos, false);
            }

            if ("coffin".equals(metadata)) {
                CoffinTile coffinTile = (CoffinTile) serverWorldAccess.getBlockEntity(pos.down());
                if (coffinTile != null) {
                    coffinTile.setOpen(rand.nextBoolean());
                    coffinTile.setCoffin(AngelUtils.randomCoffin());
                    serverWorldAccess.removeBlock(pos, false);
                }
            }

            if ("cobweb".equals(metadata)) {
                Block block = rand.nextBoolean() ? Blocks.COBWEB : Blocks.AIR;
                serverWorldAccess.setBlockState(pos, block.getDefaultState(), 2);
            }

            if ("crypt_chest".equals(metadata) || "chest".equals(metadata)) {
                ChestBlockEntity.setLootTable(serverWorldAccess, rand, pos.down(), WALoot.CRYPT_LOOT);
                serverWorldAccess.removeBlock(pos, false);
            }

            if ("chest_2down".equals(metadata)) {
                ChestBlockEntity.setLootTable(serverWorldAccess, rand, pos.down(2), WALoot.CRYPT_LOOT);
                serverWorldAccess.removeBlock(pos.down(2), false);
                serverWorldAccess.removeBlock(pos, false);
            }

            if ("path".equals(metadata)) {
                serverWorldAccess.setBlockState(pos, rand.nextBoolean() ? Blocks.AIR.getDefaultState() : getRandomPottedPlant(rand).getDefaultState(), 2);
                serverWorldAccess.setBlockState(pos.down(), Blocks.PODZOL.getDefaultState(), 2);
            }

            if ("sign".equals(metadata)) {
                SignBlockEntity signTileEntity = (SignBlockEntity) serverWorldAccess.getBlockEntity(pos.down());
                if (signTileEntity != null) {
                    signTileEntity.setTextOnRow(0, new TranslatableText("========"));
                    signTileEntity.setTextOnRow(1, new TranslatableText(USERNAMES[rand.nextInt(USERNAMES.length - 1)]));
                    signTileEntity.setTextOnRow(2, new TranslatableText(createRandomDate().format(DateTimeFormatter.ISO_LOCAL_DATE)));
                    signTileEntity.setTextOnRow(3, new TranslatableText("========"));
                    serverWorldAccess.removeBlock(pos, false);
                    serverWorldAccess.setBlockState(pos.down(2), Blocks.PODZOL.getDefaultState(), 2);
                }
            }
        }

        private Block getRandomPottedPlant(Random rand) {
            return WALoot.POTTED_PLANETS.getRandom(rand);
        }
    }


    public static LocalDate createRandomDate() {
        long startEpochDay = LocalDate.of(1800, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}

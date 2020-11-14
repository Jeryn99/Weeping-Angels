package me.swirtzly.minecraft.angels.common.world.structures;

import com.google.common.collect.ImmutableMap;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.blocks.CoffinBlock;
import me.swirtzly.minecraft.angels.common.tileentities.CoffinTile;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
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
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.commons.lang3.ArrayUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GraveyardStructurePieces {

    private static String[] USERNAMES = new String[]{"WizeWizzard", "Magicmaan", "Icebrin", "Swirtzly", "Cadiboo", "Chell", "UsualTundra1994", "50ap5ud5", "a_dizzle", "dhi", "ConnorDawn", "Spectre0987", "Nictogen"};

    private static final ResourceLocation GRAVEYARD_1 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_1");
    private static final ResourceLocation GRAVEYARD_2 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_2");
    private static final ResourceLocation GRAVEYARD_3 = new ResourceLocation(WeepingAngels.MODID, "graves/graveyard_3");

    private static final ResourceLocation[] ALL_GRAVES = new ResourceLocation[]{GRAVEYARD_1, GRAVEYARD_2, GRAVEYARD_3};

    private static final Map<ResourceLocation, BlockPos> OFFSET = ImmutableMap.of(GRAVEYARD_1, BlockPos.ZERO, GRAVEYARD_2, BlockPos.ZERO, GRAVEYARD_3, BlockPos.ZERO);

    public static void start(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> pieceList, Random random) {
        int x = pos.getX();
        int z = pos.getZ();
        BlockPos rotationOffSet = new BlockPos(0, 0, 0).rotate(rotation);
        BlockPos blockpos = rotationOffSet.add(x, pos.getY(), z);
        pieceList.add(new GraveyardStructurePieces.Piece(templateManager, ALL_GRAVES[random.nextInt(ALL_GRAVES.length - 1)], blockpos, rotation));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager templateManagerIn, ResourceLocation resourceLocationIn, BlockPos pos, Rotation rotationIn) {
            super(WAObjects.Structures.GRAVEYARD_PIECE, 0);
            this.resourceLocation = resourceLocationIn;
            BlockPos blockpos = GraveyardStructurePieces.OFFSET.get(resourceLocation);
            this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
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
            Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE);
            this.setup(template, this.templatePosition, placementsettings);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        @Override
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        @Override
        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {

            if (ServerLifecycleHooks.getCurrentServer().isDedicatedServer()) {
                USERNAMES = ArrayUtils.addAll(USERNAMES, ServerLifecycleHooks.getCurrentServer().getPlayerList().getOnlinePlayerNames());
            }

            if ("angel".equals(function)) {
                StatueTile statueTile = (StatueTile) worldIn.getTileEntity(pos.down());
                statueTile.setPose(AngelPoses.POSE_HIDING_FACE.getRegistryName());
                statueTile.setAngelType(5);
                statueTile.markDirty();
                worldIn.removeBlock(pos, false);
            }

            if ("coffin".equals(function)) {
                CoffinTile coffinTile = (CoffinTile) worldIn.getTileEntity(pos.down());
                if (coffinTile != null) {
                    coffinTile.setOpen(rand.nextBoolean());
                    coffinTile.setCoffin(AngelUtils.randomCoffin());
                    worldIn.removeBlock(pos, false);
                    //TODO Skeletons
                }
            }

            if ("cobweb".equals(function)) {
                Block block = rand.nextBoolean() ? Blocks.COBWEB : Blocks.AIR;
                worldIn.setBlockState(pos, block.getDefaultState(), 2);
            }

            if ("crypt_chest".equals(function) || "chest".equals(function)) {
                LockableLootTileEntity.setLootTable(worldIn, rand, pos.down(), WAObjects.CRYPT_LOOT);
                worldIn.removeBlock(pos, false);
            }

            if ("chest_2down".equals(function)) {
                LockableLootTileEntity.setLootTable(worldIn, rand, pos.down(2), WAObjects.CRYPT_LOOT);
                worldIn.removeBlock(pos.down(2), false);
                worldIn.removeBlock(pos, false);
            }

            if ("path".equals(function)) {
                worldIn.setBlockState(pos, rand.nextBoolean() ? Blocks.AIR.getDefaultState() : getRandomPottedPlant(rand).getDefaultState(), 2);
                worldIn.setBlockState(pos.down(), Blocks.PODZOL.getDefaultState(), 2);
            }

            if ("sign".equals(function)) {
                SignTileEntity signTileEntity = (SignTileEntity) worldIn.getTileEntity(pos.down());
                if (signTileEntity != null) {
                    signTileEntity.setText(0, new TranslationTextComponent("========"));
                    signTileEntity.setText(1, new TranslationTextComponent(USERNAMES[(int) (System.currentTimeMillis() % USERNAMES.length)]));
                    signTileEntity.setText(2, new TranslationTextComponent(createRandomDate().format(DateTimeFormatter.ISO_DATE)));
                    signTileEntity.setText(3, new TranslationTextComponent("========"));
                    worldIn.removeBlock(pos, false);
                    worldIn.setBlockState(pos.down(2), Blocks.PODZOL.getDefaultState(), 2);
                }
            }
        }
    }

    public static LocalDate createRandomDate() {
        long startEpochDay = LocalDate.of(1800, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static Block getRandomPottedPlant(Random random) {
        List<Block> plants = AngelUtils.POTTED_PLANTS.getAllElements();
        return plants.get(random.nextInt(plants.size()));
    }


}

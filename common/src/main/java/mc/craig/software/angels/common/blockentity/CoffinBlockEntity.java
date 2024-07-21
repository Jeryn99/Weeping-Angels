package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class CoffinBlockEntity extends BlockEntity implements BlockEntityTicker<CoffinBlockEntity> {


    private CoffinType coffinType = null;

    // Animation States
    public AnimationState COFFIN_OPEN = new AnimationState();
    public AnimationState COFFIN_CLOSE = new AnimationState();
    public AnimationState TARDIS_TAKEOFF = new AnimationState();

    public int animationTimer, takeoffTimer = 0;
    private boolean isDemat = false, needsBox = false;

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    public CoffinBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(WABlockEntities.COFFIN.get(), pPos, pBlockState);
    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, CoffinBlockEntity pBlockEntity) {

        if (pLevel.isClientSide()) {
            animationTimer++;
            if (isOpen()) {
                if (!COFFIN_OPEN.isStarted()) {
                    COFFIN_CLOSE.stop();
                    COFFIN_OPEN.start(animationTimer);
                }
            } else {
                if (!COFFIN_CLOSE.isStarted()) {
                    COFFIN_OPEN.stop();
                    COFFIN_CLOSE.start(animationTimer);
                }
            }
        }

        if (needsBox) {
            needsBox = false;
            setCoffinType(CoffinType.randomTardis(level.random));
        }

        if (isDemat && coffinType.isTardis()) {

            ++takeoffTimer;
            if (takeoffTimer >= 12 * 20) {
                if (!level.isClientSide) {
                    level.removeBlock(worldPosition, false);
                    activateAngels(40);
                    int i = 25;
                    while (i > 0) {
                        int j = ExperienceOrb.getExperienceValue(i);
                        i -= j;
                        this.level.addFreshEntity(new ExperienceOrb(this.level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), j));
                    }
                }
            }
        }

    }

    private void activateAngels(int range) {
        for (Iterator<BlockPos> iterator = BlockPos.withinManhattanStream(worldPosition, range, range, range).iterator(); iterator.hasNext(); ) {
            BlockPos pos = iterator.next();
            ServerLevel serverWorld = (ServerLevel) level;
            if (level.getDifficulty() != Difficulty.PEACEFUL && serverWorld.getBlockEntity(pos) instanceof StatueBlockEntity statueBlockEntity) {
                WeepingAngel angel = new WeepingAngel(level);
                angel.setVariant(statueBlockEntity.getVariant());
                angel.moveTo(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, (float) Math.toRadians(22.5F * statueBlockEntity.getBlockState().getValue(StatueBaseBlock.ROTATION)), 0);
                level.addFreshEntity(angel);
                level.removeBlock(pos, false);
            }
        }
    }



    public boolean isOpen() {
        BlockState state = level.getBlockState(getBlockPos());
        if (state.hasProperty(CoffinBlock.OPEN))
            return state.getValue(CoffinBlock.OPEN);
        return false;
    }

    @Override
    protected void loadAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.loadAdditional(compoundTag, provider);
        setCoffinType(CoffinType.find(compoundTag.getString(WAConstants.COFFIN_TYPE)));
        isDemat = compoundTag.getBoolean(WAConstants.IS_DEMAT);
        needsBox = compoundTag.getBoolean(WAConstants.NEEDS_BOX);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag, HolderLookup.Provider provider) {
        super.saveAdditional(compoundTag, provider);
        compoundTag.putString(WAConstants.COFFIN_TYPE, getCoffinType().getId());
        compoundTag.putBoolean(WAConstants.IS_DEMAT, isDemat);
        compoundTag.putBoolean(WAConstants.NEEDS_BOX, needsBox);
    }



    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CoffinType getCoffinType() {

        if (coffinType == null) {
            coffinType = CoffinType.randomTardis(RandomSource.create());
            sendUpdates();
        }

        return coffinType;
    }


    public void setCoffinType(CoffinType coffinType) {
        this.coffinType = coffinType;
        sendUpdates();
    }

    public void demat() {
        isDemat = true;
        TARDIS_TAKEOFF.start(0);
        sendUpdates();
    }

    public boolean isDemat() {
        return isDemat;
    }
}

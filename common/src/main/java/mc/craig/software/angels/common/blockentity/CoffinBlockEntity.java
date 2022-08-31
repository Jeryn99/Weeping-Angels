package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.WAEntities;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import net.minecraft.core.BlockPos;
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

    public int animationTimer = 0;
    private boolean isDemat = false, needsBox = false;
    private int ticks, pulses;
    private float alpha = 1;


    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
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
            if (ticks % 60 < 30) {
                if (pulses <= 2)
                    this.alpha -= 0.01;
                else this.alpha -= 0.02;
            } else {
                this.alpha += 0.01;
            }

            if (ticks % 60 == 0)
                ++this.pulses;

            ++ticks;
            if (ticks >= 360) {
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
                WeepingAngel angel = new WeepingAngel(level, WAEntities.WEEPING_ANGEL.get());
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
    public void load(CompoundTag pTag) {
        super.load(pTag);
        setCoffinType(CoffinType.find(pTag.getString(WAConstants.COFFIN_TYPE)));
        alpha = pTag.getFloat(WAConstants.ALPHA);
        isDemat = pTag.getBoolean(WAConstants.IS_DEMAT);
        needsBox = pTag.getBoolean(WAConstants.NEEDS_BOX);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString(WAConstants.COFFIN_TYPE, getCoffinType().getId());
        pTag.putBoolean(WAConstants.IS_DEMAT, isDemat);
        pTag.putFloat(WAConstants.ALPHA, alpha);
        pTag.putBoolean(WAConstants.NEEDS_BOX, needsBox);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CoffinType getCoffinType() {

        if (coffinType == null) {
            coffinType = CoffinType.randomCoffin(RandomSource.create());
            sendUpdates();
        }

        return coffinType;
    }


    public void setCoffinType(CoffinType coffinType) {
        this.coffinType = coffinType;
    }

    public float getAlpha() {
        return alpha;
    }

    public void demat() {
        isDemat = true;
    }
}

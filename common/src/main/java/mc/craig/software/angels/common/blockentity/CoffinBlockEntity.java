package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class CoffinBlockEntity extends BlockEntity implements BlockEntityTicker<CoffinBlockEntity> {


    private CoffinType coffinType = null;

    // Animation States
    public AnimationState COFFIN_OPEN = new AnimationState();
    public AnimationState COFFIN_CLOSE = new AnimationState();

    public int animationTimer = 0;
    private boolean isDemat = false;
    private int ticks, pulses, knockTime;
    private float alpha = 1;


    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
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
        //TODO Implement
    }

    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
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
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString(WAConstants.COFFIN_TYPE, getCoffinType().getId());
        pTag.putBoolean(WAConstants.IS_DEMAT, isDemat);
        pTag.putFloat(WAConstants.ALPHA, alpha);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CoffinType getCoffinType() {

        if (coffinType == null) {
            coffinType = CoffinType.random(RandomSource.create());
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

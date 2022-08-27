package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.blocks.CoffinBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class CoffinBlockEntity extends BlockEntity implements BlockEntityTicker<CoffinBlockEntity> {


    private CoffinType coffinType = CoffinType.random(RandomSource.create());

    // Animation States
    public AnimationState COFFIN_OPEN = new AnimationState();
    public AnimationState COFFIN_CLOSE = new AnimationState();

    public int animationTimer = 0;

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
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString(WAConstants.COFFIN_TYPE, getCoffinType().getId());
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public CoffinType getCoffinType() {
        return coffinType;
    }


    public void setCoffinType(CoffinType coffinType) {
        this.coffinType = coffinType;
    }

}

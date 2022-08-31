package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.blocks.StatueBaseBlock;
import mc.craig.software.angels.common.entity.angel.AngelEmotion;
import mc.craig.software.angels.common.entity.angel.AngelTextureVariant;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class StatueBlockEntity extends BlockEntity implements BlockEntityTicker<StatueBlockEntity>, Plinth {

    private AngelTextureVariant currentVariant = null;
    private int animation = 1;
    private AngelEmotion emotion = AngelEmotion.IDLE;

    public StatueBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WABlockEntities.STATUE.get(), blockPos, blockState);
    }

    @Override
    public void changeVariant(Plinth plinth) {
        setSpecificVariant(AngelTextureVariant.getRandomVariant(AngelTextureVariant.VARIANTS, level.random));
    }

    @Override
    public void setSpecificVariant(AngelTextureVariant angelTextureVariant) {
        this.currentVariant = angelTextureVariant;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public AngelTextureVariant getVariant() {
        if(currentVariant == null) return AngelTextureVariant.STONE;
        return currentVariant;
    }

    @Override
    public void setAnimation(int animation) {
        this.animation = animation;
    }

    @Override
    public int getAnimation() {
        return animation;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        writeNbt(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        readNbt(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    @Override
    public void setEmotion(AngelEmotion value) {
        this.emotion = value;
    }

    @Override
    public AngelEmotion getEmotion() {
        return emotion;
    }



    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, StatueBlockEntity blockEntity) {
        if (!getAnimationState().isStarted()) {
            getAnimationState().start(level.getBlockTicks().count());
        }

        if (!level.isClientSide()) {
            if (level.hasNeighborSignal(blockPos)) {
                WAHelper.spawnWeepingAngel((ServerLevel) level, blockPos, currentVariant, false, (float) Math.toRadians(22.5F * blockState.getValue(StatueBaseBlock.ROTATION)));
                level.removeBlock(blockPos, false);
            }
        }
    }
}

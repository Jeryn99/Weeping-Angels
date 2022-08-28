package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.entity.angel.AngelVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class StatueBlockEntity extends BlockEntity implements BlockEntityTicker<StatueBlockEntity>, Plinth {

    private AngelVariant currentVariant = AngelVariant.STONE;

    public StatueBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WABlockEntities.STATUE.get(), blockPos, blockState);
    }

    @Override
    public void changeVariant(Plinth plinth) {
        setSpecificVariant(AngelVariant.getVariantForPos(null)); //TODO this is bad!!!!!!!!
    }

    @Override
    public void setSpecificVariant(AngelVariant angelVariant) {
        this.currentVariant = angelVariant;
    }

    @Override
    public AngelVariant getVariant() {
        return currentVariant;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        serializeNBT(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        deserializeNBT(tag);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, StatueBlockEntity blockEntity) {

    }
}

package mc.craig.software.angels.common.blockentity;

import mc.craig.software.angels.common.WAConstants;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.Locale;

import static net.minecraft.world.level.block.SnowLayerBlock.LAYERS;

public class SnowAngelBlockEntity extends BlockEntity implements BlockEntityTicker<SnowAngelBlockEntity> {

    private final AABB collisionAabb = new AABB(0.2, 0, 0, 0.8, 2, 0.1);

    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            level.updateNeighbourForOutputSignal(worldPosition, getBlockState().getBlock());
            level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition), level.getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    public enum SnowStage {
        ARM, HEAD, BODY, WINGS;

        public String getId() {
            return name().toLowerCase(Locale.ROOT);
        }

        public static SnowStage randomStage(RandomSource randomSource) {
            int pick = randomSource.nextInt(SnowStage.values().length);
            return SnowStage.values()[pick];
        }

        public static SnowStage find(String id) {
            for (SnowStage value : values()) {
                if (value.getId().equalsIgnoreCase(id)) {
                    return value;
                }
            }
            return SnowStage.HEAD;
        }
    }

    private SnowStage snowAngelStages = SnowStage.ARM;
    private boolean isSetup = false;

    public SnowAngelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(WABlockEntities.SNOW_ANGEL.get(), blockPos, blockState);
    }

    public void setSetup(boolean setup) {
        isSetup = setup;
    }

    public boolean isSetup() {
        return isSetup;
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, SnowAngelBlockEntity blockEntity) {

        if(!isSetup){
            setSnowAngelStages(SnowStage.randomStage(level.getRandom()));
            setSetup(true);
            blockEntity.sendUpdates();
        }

        if (snowAngelStages == SnowStage.ARM) return;
        if (!level.getEntitiesOfClass(Player.class, collisionAabb.move(getBlockPos())).isEmpty() && !level.isClientSide) {
            WeepingAngel angel = new WeepingAngel(level);
            angel.setVariant(AngelVariant.STONE);
            BlockPos newPos = getBlockPos();
            angel.setPos(newPos.getX() + 0.5D, newPos.getY(), newPos.getZ() + 0.5D);
            level.addFreshEntity(angel);
            Integer layers = level.getBlockState(worldPosition).getValue(LAYERS);
            level.setBlockAndUpdate(worldPosition, Blocks.SNOW.defaultBlockState().setValue(LAYERS, layers));
        }
    }


    public SnowStage getSnowAngelStages() {
        return snowAngelStages;
    }

    public void setSnowAngelStages(SnowStage snowAngelStages) {
        this.snowAngelStages = snowAngelStages;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString(WAConstants.SNOW_STAGE, getSnowAngelStages().getId());
        tag.putBoolean(WAConstants.NEEDS_BOX, isSetup);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        setSnowAngelStages(SnowStage.find(tag.getString(WAConstants.SNOW_STAGE)));
        isSetup = tag.getBoolean(WAConstants.NEEDS_BOX);
    }
}

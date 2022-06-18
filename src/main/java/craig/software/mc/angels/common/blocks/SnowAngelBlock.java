package craig.software.mc.angels.common.blocks;

import craig.software.mc.angels.common.blockentities.SnowAngelBlockEntity;
import craig.software.mc.angels.common.entities.AngelType;
import craig.software.mc.angels.common.entities.WeepingAngel;
import craig.software.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SnowAngelBlock extends SnowLayerBlock implements EntityBlock {

    public SnowAngelBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).randomTicks().noOcclusion());
    }

    @Override
    public void entityInside(@NotNull BlockState blockState, Level world, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        if (world.getBlockEntity(blockPos) instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            if (snowAngelBlockEntity.getSnowAngelStage() == SnowAngelBlockEntity.SnowAngelStages.ARM) {
                entity.makeStuckInBlock(blockState, new Vec3(0.15D, 0.05F, 0.15D));
            }
        }
    }

    @Override
    public void setPlacedBy(@NotNull Level worldIn, @NotNull BlockPos pos, @NotNull BlockState state, LivingEntity placer, @NotNull ItemStack stack) {

        super.setPlacedBy(worldIn, pos, state, placer, stack);
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            int rotation = Mth.floor(placer.yBodyRot);
            if (!snowAngelBlockEntity.isHasSetup()) {
                snowAngelBlockEntity.setSnowAngelStage(AngelUtil.randowSnowStage());
                snowAngelBlockEntity.setRotation(rotation);
                snowAngelBlockEntity.setHasSetup(true);
                snowAngelBlockEntity.setVariant(AngelType.DISASTER_MC.getWeightedHandler().getRandom());
                snowAngelBlockEntity.sendUpdates();
            }
        }
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel worldIn, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11) {
            Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.SNOW));
            BlockEntity tile = worldIn.getBlockEntity(pos);
            if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
                WeepingAngel angel = new WeepingAngel(worldIn);
                angel.setType(AngelType.DISASTER_MC);
                angel.setVarient(snowAngelBlockEntity.getVariant());
                angel.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                worldIn.addFreshEntity(angel);
                worldIn.removeBlock(pos, false);
            }
        }
    }


    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState p_51567_) {
        return RenderShape.MODEL;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SnowAngelBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
                snowAngelBlockEntity.tick(level, blockPos, blockState, snowAngelBlockEntity);
            }
        };
    }
}

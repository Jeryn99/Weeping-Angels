package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.common.blockentity.SnowAngelBlockEntity;
import mc.craig.software.angels.common.entity.angel.WeepingAngel;
import mc.craig.software.angels.common.entity.angel.ai.AngelVariant;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowAngelBlock extends SnowLayerBlock implements EntityBlock {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    public SnowAngelBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).randomTicks().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(ROTATION, 1).setValue(CoffinBlock.WATERLOGGED, false));
    }

    @Override
    public void entityInside(@NotNull BlockState blockState, Level world, @NotNull BlockPos blockPos, @NotNull Entity entity) {
        if (world.getBlockEntity(blockPos) instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            if (snowAngelBlockEntity.getSnowAngelStages() == SnowAngelBlockEntity.SnowStage.ARM) {
                entity.makeStuckInBlock(blockState, new Vec3(0.15D, 0.05F, 0.15D));
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ROTATION, CoffinBlock.WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.is(FluidTags.WATER));
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.defaultFluidState() : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(ROTATION, rot.rotate(state.getValue(ROTATION), 16));
    }

    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(ROTATION, mirrorIn.mirror(state.getValue(ROTATION), 16));
    }


    @Override
    public void setPlacedBy(@NotNull Level worldIn, @NotNull BlockPos pos, @NotNull BlockState state, LivingEntity placer, @NotNull ItemStack stack) {

        super.setPlacedBy(worldIn, pos, state, placer, stack);
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            snowAngelBlockEntity.setSetup(true);
            snowAngelBlockEntity.setSnowAngelStages(SnowAngelBlockEntity.SnowStage.randomStage(worldIn.getRandom()));
            snowAngelBlockEntity.sendUpdates();
        }
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void randomTick(@NotNull BlockState state, ServerLevel worldIn, @NotNull BlockPos pos, @NotNull Random random) {
        if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11) {
            Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.SNOW));
            BlockEntity tile = worldIn.getBlockEntity(pos);
            if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
                WeepingAngel angel = new WeepingAngel(worldIn);
                angel.setVariant(AngelVariant.STONE);
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
package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.blockentities.PlinthBlockEntity;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class PlinthBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final BooleanProperty CLASSIC = BooleanProperty.create("is_old");

    protected static final VoxelShape BOTTOM_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);


    public PlinthBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(CLASSIC, false).setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return state.getValue(CLASSIC) ? BOTTOM_SHAPE : Shapes.block();
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }


    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(CLASSIC, false).setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.is(FluidTags.WATER));
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
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
        builder.add(CLASSIC);
        builder.add(BlockStateProperties.WATERLOGGED);
    }


    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void setPlacedBy(@NotNull Level world, @NotNull BlockPos pos, @NotNull BlockState state, LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.getBlockEntity(pos) instanceof PlinthBlockEntity plinth) {
            plinth.setPose(WeepingAngelPose.getRandomPose(world.random));
            plinth.setAngelType(AngelUtil.randomType().name());
            plinth.setAngelVarients(plinth.getAngelType().getWeightedHandler().getRandom(null));
            if (stack.getTagElement("BlockEntityTag") != null) {
                plinth.load(stack.getTagElement("BlockEntityTag"));
            }
            plinth.sendUpdates();
        }
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos p_153215_, @NotNull BlockState p_153216_) {
        return new PlinthBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof PlinthBlockEntity plinthBlockEntity) {
                plinthBlockEntity.tick(level, blockPos, blockState, plinthBlockEntity);
            }
        };
    }

}

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
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

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
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return state.getValue(CLASSIC) ? BOTTOM_SHAPE : Shapes.block();
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(CLASSIC, false).setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.is(FluidTags.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.defaultFluidState() : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(ROTATION, rot.rotate(state.getValue(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
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
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.getBlockEntity(pos) instanceof PlinthBlockEntity plinth) {
            plinth.setPose(WeepingAngelPose.getRandomPose(AngelUtil.RAND));
            plinth.setAngelType(AngelUtil.randomType().name());
            plinth.setAngelVarients(plinth.getAngelType().getWeightedHandler().getRandom());
            if (stack.getTagElement("BlockEntityTag") != null) {
                plinth.load(stack.getTagElement("BlockEntityTag"));
            }
            plinth.sendUpdates();
        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new PlinthBlockEntity(p_153215_, p_153216_);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof PlinthBlockEntity plinthBlockEntity) {
                plinthBlockEntity.tick(level, blockPos, blockState, plinthBlockEntity);
            }
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level p_153210_, T p_153211_) {
        return EntityBlock.super.getListener(p_153210_, p_153211_);
    }
}

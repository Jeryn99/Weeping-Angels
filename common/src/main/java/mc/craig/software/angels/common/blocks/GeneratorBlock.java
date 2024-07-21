package mc.craig.software.angels.common.blocks;

import com.mojang.serialization.MapCodec;
import mc.craig.software.angels.common.blockentity.GeneratorBlockEntity;
import mc.craig.software.angels.common.items.WAItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public class GeneratorBlock extends BaseEntityBlock {

    private static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    public GeneratorBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, false).setValue(ROTATION, 0));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return null;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getBlockEntity(pos) instanceof GeneratorBlockEntity generatorBlockEntity) {
            if (!generatorBlockEntity.hasSpawned()) return;
            double d = (double) pos.getX() + random.nextDouble();
            double e = (double) pos.getY() + 0.8;
            double f = (double) pos.getZ() + random.nextDouble();
            level.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(WATERLOGGED, fluid.is(FluidTags.WATER));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ROTATION, WATERLOGGED);
    }

    public static boolean isBreakable(LevelAccessor level, BlockPos blockPos) {
        if (level.getBlockEntity(blockPos) instanceof GeneratorBlockEntity generatorBlockEntity) {
            return !generatorBlockEntity.isActivated();
        }
        return true;
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.defaultFluidState() : Fluids.EMPTY.defaultFluidState();
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
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BOTTOM_AABB;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof GeneratorBlockEntity generatorBlockEntity) {
                generatorBlockEntity.tick(pLevel, blockPos, blockState, generatorBlockEntity);
            }
        };
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSupportCenter(level, pos.below(), Direction.UP);
    }


    @Override
    public ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(WAItems.CHRONODYNE_GENERATOR.get());
    }

    public static boolean canSupportCenter(LevelReader level, BlockPos pos, Direction direction) {
        BlockState blockState = level.getBlockState(pos);
        return (direction != Direction.DOWN || !blockState.is(BlockTags.UNSTABLE_BOTTOM_CENTER)) && blockState.isFaceSturdy(level, pos, direction, SupportType.CENTER);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !this.canSurvive(state, level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }


    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);

        if (!pLevel.isClientSide()) {
            if (pLevel.hasNeighborSignal(pPos)) {
                activateFromPos(pPos, pLevel, true);
            }
        }
    }

    private boolean activateFromPos(BlockPos pos, Level level, boolean activated) {
        if (level.getBlockEntity(pos) instanceof GeneratorBlockEntity generatorBlockEntity) {
            if (!generatorBlockEntity.isActivated()) {
                generatorBlockEntity.setActivated(activated);
                generatorBlockEntity.sendUpdates();
                return true;
            }
        }
        return false;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            if (isBreakable(level, blockPos)) {
                activateFromPos(blockPos, level, true);
                player.swing(interactionHand);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.FAIL;
        }
        return ItemInteractionResult.FAIL;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GeneratorBlockEntity(pos, state);
    }
}

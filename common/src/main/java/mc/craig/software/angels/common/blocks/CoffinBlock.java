package mc.craig.software.angels.common.blocks;

import com.mojang.serialization.*;
import mc.craig.software.angels.common.WASounds;
import mc.craig.software.angels.common.blockentity.CoffinBlockEntity;
import mc.craig.software.angels.common.blockentity.WABlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CoffinBlock extends BaseEntityBlock {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty UPRIGHT = BooleanProperty.create("upright");
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected final Supplier<BlockEntityType<? extends CoffinBlockEntity>> blockEntityType;


    public CoffinBlock(Properties pProperties, Supplier<BlockEntityType<? extends CoffinBlockEntity>> supplier) {
        super(pProperties.noOcclusion());
        this.blockEntityType = supplier;
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false).setValue(UPRIGHT, false).setValue(WATERLOGGED, false).setValue(ROTATION, 1));
    }

    public static final MapCodec<CoffinBlock> CODEC = simpleCodec((properties) -> new CoffinBlock(properties, WABlockEntities.COFFIN::get));

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN, ROTATION, WATERLOGGED, UPRIGHT);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (interactionHand == InteractionHand.OFF_HAND) return ItemInteractionResult.FAIL;
        player.swing(interactionHand);

        if (level.getBlockEntity(blockPos) instanceof CoffinBlockEntity coffinBlockEntity) {
            if (coffinBlockEntity.getCoffinType().isTardis()) {

                if (player.getMainHandItem().get(DataComponents.JUKEBOX_PLAYABLE) != null) {
                    level.playSound(null, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, WASounds.TARDIS_TAKEOFF.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                    coffinBlockEntity.demat();
                    coffinBlockEntity.sendUpdates();
                    if (!player.isCreative()) {
                        player.getMainHandItem().shrink(1);
                    }
                    return ItemInteractionResult.SUCCESS;
                } else {
                    level.playSound(null, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, WASounds.LOCKED.get(), SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
                    coffinBlockEntity.sendUpdates();
                }
            }
        }

        level.setBlock(blockPos, blockState.cycle(OPEN), 3);
        level.gameEvent(player, GameEvent.BLOCK_OPEN, blockPos);
        level.playSound(null, blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D, level.getBlockState(blockPos).getValue(OPEN) ? SoundEvents.ENDER_CHEST_OPEN : SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        return ItemInteractionResult.SUCCESS;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CoffinBlockEntity(pPos, pState);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof CoffinBlockEntity coffinBlockEntity) {
                coffinBlockEntity.tick(pLevel, blockPos, blockState, coffinBlockEntity);
            }
        };
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(OPEN, false).setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.is(FluidTags.WATER)).setValue(UPRIGHT, !context.getPlayer().isShiftKeyDown());
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

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(ServerLevel pLevel, T pBlockEntity) {
        return super.getListener(pLevel, pBlockEntity);
    }
}

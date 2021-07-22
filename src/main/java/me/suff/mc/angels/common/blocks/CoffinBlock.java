package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.common.tileentities.CoffinTile;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.RecordItem;
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
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.SculkSensorBlock.WATERLOGGED;


public class CoffinBlock extends Block implements EntityBlock {

    public static final BooleanProperty UPRIGHT = BooleanProperty.create("upright");
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;


    public CoffinBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof CoffinTile) {
            CoffinTile coffinTile = (CoffinTile) world.getBlockEntity(pos);
            if (coffinTile.getCoffin().isPoliceBox()) {
                return 7;
            }
        }
        return super.getLightEmission(state, world, pos);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            CoffinTile coffinTile = (CoffinTile) worldIn.getBlockEntity(pos);
            if (coffinTile != null) {
                if (!coffinTile.getCoffin().isPoliceBox()) {
                    coffinTile.setOpen(!coffinTile.isOpen());
                    worldIn.playSound(null, pos.getX() + 0.5D, (double) pos.getY() + 0.5D, pos.getZ() + 0.5D, coffinTile.isOpen() ? SoundEvents.ENDER_CHEST_OPEN : SoundEvents.CHEST_CLOSE, SoundSource.BLOCKS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
                } else {
                    if (player.getMainHandItem().getItem() instanceof RecordItem) {
                        coffinTile.setDoingSomething(true);
                        if (!player.isCreative()) {
                            player.getMainHandItem().shrink(1);
                        }
                    }
                }
            }
            coffinTile.sendUpdates();
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UPRIGHT, ROTATION, WATERLOGGED);
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(ROTATION, Mth.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.is(FluidTags.WATER)).setValue(UPRIGHT, context.getPlayer().isShiftKeyDown());
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new CoffinTile(p_153215_, p_153216_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153212_, BlockState p_153213_, BlockEntityType<T> p_153214_) {
        return EntityBlock.super.getTicker(p_153212_, p_153213_, p_153214_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level p_153210_, T p_153211_) {
        return EntityBlock.super.getListener(p_153210_, p_153211_);
    }
}

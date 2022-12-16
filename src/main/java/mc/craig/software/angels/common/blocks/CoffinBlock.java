package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.common.tileentities.CoffinTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static net.minecraft.state.properties.BlockStateProperties.WATERLOGGED;

public class CoffinBlock extends DirectionalBlock {

    public static final BooleanProperty UPRIGHT = BooleanProperty.create("upright");
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;


    public CoffinBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof CoffinTile) {
            CoffinTile coffinTile = (CoffinTile) world.getBlockEntity(pos);
            if (coffinTile.getCoffin().isPoliceBox()) {
                return 7;
            }
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            CoffinTile coffinTile = (CoffinTile) worldIn.getBlockEntity(pos);
            if (coffinTile != null) {
                if (!coffinTile.getCoffin().isPoliceBox()) {
                    coffinTile.setOpen(!coffinTile.isOpen());
                    worldIn.playSound(null, pos.getX() + 0.5D, (double) pos.getY() + 0.5D, pos.getZ() + 0.5D, coffinTile.isOpen() ? SoundEvents.ENDER_CHEST_OPEN : SoundEvents.CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, worldIn.random.nextFloat() * 0.1F + 0.9F);
                } else {
                    if (player.getMainHandItem().getItem() instanceof MusicDiscItem) {
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


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new CoffinTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UPRIGHT, ROTATION, WATERLOGGED);
    }


    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(ROTATION, MathHelper.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.getFluidState().is(FluidTags.WATER)).setValue(UPRIGHT, context.getPlayer().isShiftKeyDown());
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

}

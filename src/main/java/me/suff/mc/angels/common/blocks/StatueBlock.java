package me.suff.mc.angels.common.blocks;

import javax.annotation.Nullable;
import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

/**
 * Created by Craig on 17/02/2020 @ 12:19
 */
public class StatueBlock extends Block implements IWaterLoggable {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;

    public StatueBlock() {
        super(Properties.of(Material.STONE).noOcclusion().strength(3).sound(SoundType.STONE).requiresCorrectToolForDrops());
        this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new StatueTile();
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
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return state.setValue(ROTATION, MathHelper.floor((double) (context.getRotation() * 16.0F / 360.0F) + 0.5D) & 15).setValue(BlockStateProperties.WATERLOGGED, fluid.getFluidState().is(FluidTags.WATER));
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
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
        builder.add(BlockStateProperties.WATERLOGGED);
    }


    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(world, pos, state, placer, stack);
        if (world.getBlockEntity(pos) instanceof StatueTile) {
            StatueTile statue = (StatueTile) world.getBlockEntity(pos);

            if (!world.isClientSide) {
                BlockPos position = statue.getBlockPos();

                if (stack.getTagElement("BlockEntityTag") != null) {
                    statue.load(state, stack.getTagElement("BlockEntityTag"));
                    statue.setPosition(position);
                } else {
                    statue.setAngelType(AngelUtil.randomType().name());
                    statue.setPose(WeepingAngelPose.getRandomPose(world.random));
                    statue.setAngelVarients(statue.getAngelType().getRandom());
                }
            }
        }
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
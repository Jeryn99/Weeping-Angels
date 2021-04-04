package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.client.poses.WeepingAngelPose;
import me.suff.mc.angels.common.tileentities.StatueTile;
import me.suff.mc.angels.utils.AngelUtils;
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

import javax.annotation.Nullable;

/**
 * Created by Craig on 17/02/2020 @ 12:19
 */
public class StatueBlock extends Block implements IWaterLoggable {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;


    public StatueBlock() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3).sound(SoundType.STONE).setRequiresTool());
        this.setDefaultState(this.getDefaultState().with(BlockStateProperties.WATERLOGGED, false));
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
    public boolean isVariableOpacity() {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        FluidState fluid = context.getWorld().getFluidState(context.getPos());
        return state.with(ROTATION, MathHelper.floor((double) (context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15).with(BlockStateProperties.WATERLOGGED, fluid.getFluidState().isTagged(FluidTags.WATER));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getDefaultState() : Fluids.EMPTY.getDefaultState();
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(ROTATION, rot.rotate(state.get(ROTATION), 16));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.with(ROTATION, mirrorIn.mirrorRotation(state.get(ROTATION), 16));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(ROTATION);
        builder.add(BlockStateProperties.WATERLOGGED);
    }


    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        if (world.getTileEntity(pos) instanceof StatueTile) {
            StatueTile statue = (StatueTile) world.getTileEntity(pos);

            if (!world.isRemote) {
                BlockPos position = statue.getPos();

                if (stack.getChildTag("BlockEntityTag") != null) {
                    statue.read(state, stack.getChildTag("BlockEntityTag"));
                    statue.setPos(position);
                } else {
                    statue.setAngelType(AngelUtils.randomType().name());
                    statue.setPose(WeepingAngelPose.getRandomPose(world.rand));
                    statue.setAngelVarients(AngelUtils.randomVarient());
                }

        /*        int offset = 0;
                for (AngelEnums.AngelType type : AngelEnums.AngelType.values()) {
                    for (WeepingAngelEntity.AngelVariants value : WeepingAngelEntity.AngelVariants.values()) {
                        for (WeepingAngelPose angelPose : WeepingAngelPose.values()) {
                            if (type == AngelEnums.AngelType.ANGELA_MC) {
                                world.setBlockState(pos.west(offset), WAObjects.Blocks.STATUE.get().getDefaultState());
                                StatueTile statueTile = (StatueTile) world.getTileEntity(pos.west(offset));
                                statueTile.setPose(angelPose);
                                statueTile.setAngelType(type);
                                statueTile.setRotation(rotation);
                                statueTile.setAngelVarients(value);
                                world.setBlockState(pos.west(offset).north(), Blocks.SPRUCE_WALL_SIGN.getDefaultState());
                                SignTileEntity signTileEntity = (SignTileEntity) world.getTileEntity(pos.west(offset).north());
                                signTileEntity.setText(0, new TranslationTextComponent(type.name()));
                                signTileEntity.setText(1, new TranslationTextComponent(angelPose.name()));
                                signTileEntity.setText(3, new TranslationTextComponent(value.name()));
                                signTileEntity.markDirty();
                                offset++;
                            }
                        }
                    }
                }*/
            }
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}
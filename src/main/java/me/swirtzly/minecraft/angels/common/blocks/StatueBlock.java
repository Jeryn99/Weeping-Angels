package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.client.poses.WeepingAngelPose;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import me.swirtzly.minecraft.angels.utils.AngelUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Craig on 17/02/2020 @ 12:19
 */
public class StatueBlock extends Block implements IWaterLoggable {

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
        return state.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite()).with(BlockStateProperties.WATERLOGGED, fluid.getFluidState().isTagged(FluidTags.WATER));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder< Block, BlockState > builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getDefaultState() : Fluids.EMPTY.getDefaultState();
    }


    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        if (world.getTileEntity(pos) instanceof StatueTile) {
            int rotation = MathHelper.floor(placer.rotationYaw);
            StatueTile statue = (StatueTile) world.getTileEntity(pos);

            if (!world.isRemote) {
                BlockPos position = statue.getPos();

                if (stack.getChildTag("BlockEntityTag") != null) {
                    statue.read(state, stack.getChildTag("BlockEntityTag"));
                    statue.setPos(position);
                } else {
                    statue.setRotation(rotation);
                    statue.setAngelType(AngelUtils.randomType().name());
                    statue.setPose(WeepingAngelPose.getRandomPose(world.rand));
                    statue.setAngelVarients(AngelUtils.randomVarient());
                    statue.sendUpdates();
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
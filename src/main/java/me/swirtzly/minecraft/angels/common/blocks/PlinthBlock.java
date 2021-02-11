package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.tileentities.PlinthTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class PlinthBlock extends Block {

    public PlinthBlock() {
        super(Properties.create(Material.ROCK).hardnessAndResistance(3).sound(SoundType.STONE));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new PlinthTile();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean isVariableOpacity() {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState state = super.getStateForPlacement(context);
        return state.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(Builder< Block, BlockState > builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (world.getTileEntity(pos) instanceof PlinthTile) {
            int rotation = MathHelper.floor(placer.rotationYaw);
            PlinthTile plinth = (PlinthTile) world.getTileEntity(pos);
            plinth.setRotation(rotation);
            plinth.setPose(AngelPoses.getRandomPose().getRegistryName());
            plinth.sendUpdates();
        }
    }

}

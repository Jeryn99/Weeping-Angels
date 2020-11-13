package me.swirtzly.minecraft.angels.common.blocks;

import me.swirtzly.minecraft.angels.client.poses.AngelPoses;
import me.swirtzly.minecraft.angels.common.tileentities.StatueTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by Swirtzly on 17/02/2020 @ 12:19
 */
public class StatueBlock extends Block {

    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_0_15;

    public StatueBlock() {
        super(Properties.create(Material.ROCK).notSolid().hardnessAndResistance(3).sound(SoundType.STONE).setRequiresTool());
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
        return this.getDefaultState().with(ROTATION, MathHelper.floor((double) (context.getPlacementYaw() * 16.0F / 360.0F) + 0.5D) & 15);
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
    }


    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        if (world.getTileEntity(pos) instanceof StatueTile) {
            StatueTile statue = (StatueTile) world.getTileEntity(pos);
            statue.setAngelType(world.rand.nextInt(6));
            statue.setPose(AngelPoses.getRandomPose().getRegistryName());
            statue.sendUpdates();
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            StatueTile statue = (StatueTile) worldIn.getTileEntity(pos);
            statue.setPose(AngelPoses.getRandomPose().getRegistryName());
            statue.sendUpdates();
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}

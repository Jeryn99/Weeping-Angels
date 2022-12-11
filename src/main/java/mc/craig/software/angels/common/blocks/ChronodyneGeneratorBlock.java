package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.common.entities.AnomalyEntity;
import mc.craig.software.angels.common.entities.WeepingAngelEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class ChronodyneGeneratorBlock extends Block {

    private static final VoxelShape CG_AABB = VoxelShapes.create(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));

    public ChronodyneGeneratorBlock() {
        super(Block.Properties.of(Material.DECORATION, MaterialColor.SAND).noOcclusion().strength(0.1F).noOcclusion());
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return CG_AABB;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return CG_AABB;
    }

    @Override
    public boolean hasDynamicShape() {
        return false;
    }

    @Override
    public void stepOn(World worldIn, BlockPos pos, Entity entityIn) {
        super.stepOn(worldIn, pos, entityIn);

        if (entityIn instanceof WeepingAngelEntity) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.hasNeighborSignal(pos)) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isClientSide) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}

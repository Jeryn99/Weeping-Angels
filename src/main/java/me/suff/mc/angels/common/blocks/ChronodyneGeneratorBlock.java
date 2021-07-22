package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.common.entities.AnomalyEntity;
import me.suff.mc.angels.common.entities.WeepingAngelEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChronodyneGeneratorBlock extends Block {

    private static final VoxelShape CG_AABB = Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));

    public ChronodyneGeneratorBlock() {
        super(Properties.of(Material.STONE).noOcclusion().strength(3).sound(SoundType.STONE).requiresCorrectToolForDrops());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return CG_AABB;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return CG_AABB;
    }

    @Override
    public boolean hasDynamicShape() {
        return false;
    }

    @Override
    public void stepOn(Level worldIn, BlockPos pos, Entity entityIn) {
        super.stepOn(worldIn, pos, entityIn);

        if (entityIn instanceof WeepingAngelEntity) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.hasNeighborSignal(pos)) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            AnomalyEntity anomalyEntity = new AnomalyEntity(worldIn);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(anomalyEntity);
            worldIn.removeBlock(pos, false);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}

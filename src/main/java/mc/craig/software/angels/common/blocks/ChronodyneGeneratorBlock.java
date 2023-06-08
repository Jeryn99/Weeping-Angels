package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.common.entities.Portal;
import mc.craig.software.angels.common.entities.WeepingAngel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChronodyneGeneratorBlock extends Block {

    private static final VoxelShape CG_AABB = Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));

    public ChronodyneGeneratorBlock() {
        super(Properties.copy(Blocks.STONE).noOcclusion());
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
    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        super.stepOn(worldIn, pos, state, entityIn);

        if (entityIn instanceof WeepingAngel) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.hasNeighborSignal(pos)) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}

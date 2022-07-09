package craig.software.mc.angels.common.blocks;

import craig.software.mc.angels.common.entities.Portal;
import craig.software.mc.angels.common.entities.WeepingAngel;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ChronodyneGeneratorBlock extends Block {

    private static final VoxelShape CG_AABB = Shapes.create(new AABB(0.0D, 0.0D, 0.0D, 1.0D, 0.09375D, 1.0D));

    public ChronodyneGeneratorBlock() {
        super(BlockBehaviour.Properties.of(Material.DECORATION, MaterialColor.SAND).noOcclusion().strength(0.1F).noOcclusion());
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return CG_AABB;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return CG_AABB;
    }

    @Override
    public boolean hasDynamicShape() {
        return false;
    }

    @Override
    public void stepOn(@NotNull Level worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Entity entityIn) {
        super.stepOn(worldIn, pos, state, entityIn);

        if (entityIn instanceof WeepingAngel) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborChanged(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Block blockIn, @NotNull BlockPos fromPos, boolean isMoving) {
        if (worldIn.hasNeighborSignal(pos)) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            Portal portal = new Portal(worldIn);
            portal.setPos(pos.getX(), pos.getY(), pos.getZ());
            worldIn.addFreshEntity(portal);
            worldIn.removeBlock(pos, false);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }
}

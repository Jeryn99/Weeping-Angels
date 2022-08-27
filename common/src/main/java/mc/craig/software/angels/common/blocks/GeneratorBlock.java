package mc.craig.software.angels.common.blocks;

import mc.craig.software.angels.common.entity.angel.AbstractWeepingAngel;
import mc.craig.software.angels.util.WAHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GeneratorBlock extends Block {

    protected static final VoxelShape BOTTOM_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);


    public GeneratorBlock(Properties pProperties) {
        super(pProperties.noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BOTTOM_AABB;
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof AbstractWeepingAngel) {
            WAHelper.spawnBlackHole((ServerLevel) pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        if (!pLevel.isClientSide()) {
            if (pLevel.hasNeighborSignal(pPos)) {
                WAHelper.spawnBlackHole((ServerLevel) pLevel, pPos);
                pLevel.removeBlock(pPos, false);
            }
        }
    }

    @Override
    public void onProjectileHit(Level pLevel, BlockState pState, BlockHitResult pHit, Projectile pProjectile) {
        super.onProjectileHit(pLevel, pState, pHit, pProjectile);
        if (pLevel.isClientSide()) return;
        WAHelper.spawnBlackHole((ServerLevel) pLevel, pHit.getBlockPos());
        pLevel.removeBlock(pHit.getBlockPos(), false);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            WAHelper.spawnBlackHole((ServerLevel) pLevel, pPos);
            pLevel.removeBlock(pPos, false);
            pPlayer.swing(pHand);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

}

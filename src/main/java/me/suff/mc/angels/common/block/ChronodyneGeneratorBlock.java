package me.suff.mc.angels.common.block;

import me.suff.mc.angels.common.entity.PortalEntity;
import me.suff.mc.angels.common.entity.WeepingAngelEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.jetbrains.annotations.Nullable;

public class ChronodyneGeneratorBlock extends BlockWithEntity {

    public ChronodyneGeneratorBlock() {
        super(Settings.of(Material.STONE).nonOpaque().sounds(BlockSoundGroup.STONE).requiresTool());
    }


    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (entity instanceof WeepingAngelEntity) {
            PortalEntity anomalyEntity = new PortalEntity(world);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(anomalyEntity);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
        if (world.isReceivingRedstonePower(pos) && !world.isClient) {
            PortalEntity anomalyEntity = new PortalEntity(world);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(anomalyEntity);
            world.removeBlock(pos, false);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            PortalEntity anomalyEntity = new PortalEntity(world);
            anomalyEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            world.spawnEntity(anomalyEntity);
            world.removeBlock(pos, false);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return super.getTicker(world, state, type);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getGameEventListener(World world, T blockEntity) {
        return super.getGameEventListener(world, blockEntity);
    }
}
package me.suff.mc.angels.common.blocks;

import me.suff.mc.angels.common.blockentities.SnowAngelBlockEntity;
import me.suff.mc.angels.common.entities.AngelType;
import me.suff.mc.angels.common.entities.WeepingAngel;
import me.suff.mc.angels.utils.AngelUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowArmBlock extends SnowLayerBlock implements EntityBlock {

    public SnowArmBlock() {
        super(BlockBehaviour.Properties.copy(Blocks.STONE).randomTicks().noOcclusion());
    }

    @Override
    public void entityInside(BlockState blockState, Level world, BlockPos blockPos, Entity entity) {
        if (world.getBlockEntity(blockPos) instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            if (snowAngelBlockEntity.getSnowAngelStage() == SnowAngelBlockEntity.SnowAngelStages.ARM) {
                entity.makeStuckInBlock(blockState, new Vec3(0.15D, 0.05F, 0.15D));
            }
        }
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {

        super.setPlacedBy(worldIn, pos, state, placer, stack);
        BlockEntity tile = worldIn.getBlockEntity(pos);
        if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
            int rotation = Mth.floor(placer.yBodyRot);
            if (!snowAngelBlockEntity.isHasSetup()) {
                snowAngelBlockEntity.setSnowAngelStage(AngelUtil.randowSnowStage());
                snowAngelBlockEntity.setRotation(rotation);
                snowAngelBlockEntity.setHasSetup(true);
                snowAngelBlockEntity.setVariant(AngelType.DISASTER_MC.getWeightedHandler().getRandom());
                snowAngelBlockEntity.sendUpdates();
            }
        }
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, Random random) {
        if (worldIn.getBrightness(LightLayer.BLOCK, pos) > 11) {
            Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Blocks.SNOW));
            BlockEntity tile = worldIn.getBlockEntity(pos);
            if (tile instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
                WeepingAngel angel = new WeepingAngel(worldIn);
                angel.setType(AngelType.DISASTER_MC);
                angel.setVarient(snowAngelBlockEntity.getVariant());
                angel.setPos(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
                worldIn.addFreshEntity(angel);
                worldIn.removeBlock(pos, false);
            }
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState p_51567_) {
        return RenderShape.MODEL;
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SnowAngelBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (level1, blockPos, blockState, t) -> {
            if (t instanceof SnowAngelBlockEntity snowAngelBlockEntity) {
                snowAngelBlockEntity.tick(level, blockPos, blockState, snowAngelBlockEntity);
            }
        };
    }

    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level p_153210_, T p_153211_) {
        return EntityBlock.super.getListener(p_153210_, p_153211_);
    }
}

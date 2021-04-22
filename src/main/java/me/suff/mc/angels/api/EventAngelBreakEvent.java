package me.suff.mc.angels.api;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/* Created by Craig on 11/02/2021 */
@Cancelable
public class EventAngelBreakEvent extends EntityEvent {

    private final BlockPos blockPos;
    private BlockState blockState;

    public EventAngelBreakEvent(LivingEntity entity, BlockState blockState, BlockPos blockPos) {
        super(entity);
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public World getWorld() {
        return getEntity().level;
    }
}

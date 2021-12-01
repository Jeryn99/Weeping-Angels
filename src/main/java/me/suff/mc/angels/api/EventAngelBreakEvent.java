package me.suff.mc.angels.api;

import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/* Created by Craig on 11/02/2021 */
@Cancelable
public class EventAngelBreakEvent extends EntityEvent {

    private final BlockPos blockPos;
    private final BlockState blockState;

    public EventAngelBreakEvent(QuantumLockedLifeform quantumLockedLifeform, BlockState blockState, BlockPos blockPos) {
        super(quantumLockedLifeform);
        this.blockState = blockState;
        this.blockPos = blockPos;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public Level getWorld() {
        return getEntity().getLevel();
    }
}

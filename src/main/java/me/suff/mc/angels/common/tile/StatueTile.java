package me.suff.mc.angels.common.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

/* Created by Craig on 18/02/2021 */
public class StatueTile extends BlockEntity implements Tickable {
    public StatueTile(BlockEntityType< ? > type) {
        super(type);
    }

    @Override
    public void tick() {

    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return super.toTag(tag);
    }
}

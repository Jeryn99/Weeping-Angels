package me.suff.mc.angels.conversion;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.util.INBTSerializable;

public interface AngelVirus extends INBTSerializable<CompoundTag> {
    boolean isInfected();

    void infect(boolean infected);

    void tick();

    void sync();

    void tickCounter();

    int viewTime();

    LivingEntity living();
}

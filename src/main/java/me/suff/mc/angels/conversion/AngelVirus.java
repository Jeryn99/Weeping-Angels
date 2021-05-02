package me.suff.mc.angels.conversion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface AngelVirus extends INBTSerializable<CompoundNBT> {
    boolean isInfected();

    void infect(boolean infected);

    void tick();

    void sync();

    void tickCounter();

    int viewTime();

    LivingEntity living();
}

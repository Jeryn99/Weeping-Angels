package me.suff.mc.angels.conversion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class AngelInfection implements AngelVirus {

    @CapabilityInject(AngelVirus.class)
    public static final Capability<AngelVirus> CAPABILITY = null;
    private final LivingEntity living;

    private boolean isInfected = false;
    private int timeSeen = 0;

    public AngelInfection(LivingEntity livingEntity) {
        this.living = livingEntity;
    }

    @Nonnull
    public static LazyOptional<AngelVirus> get(LivingEntity livingEntity) {
        return livingEntity.getCapability(AngelInfection.CAPABILITY, null);
    }

    @Override
    public boolean isInfected() {
        return isInfected;
    }

    @Override
    public void infect(boolean infected) {
        this.isInfected = infected;
    }

    @Override
    public void tick() {
        if (timeSeen >= 6000) {
            timeSeen = 0;
            living.addEffect(new EffectInstance(Effects.CONFUSION, 200));
            living.addEffect(new EffectInstance(Effects.WITHER, 200));
        }
    }

    @Override
    public void sync() {

    }

    @Override
    public void tickCounter() {
        timeSeen++;
    }

    @Override
    public int viewTime() {
        return timeSeen;
    }

    @Override
    public LivingEntity living() {
        return living;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putBoolean("infected", isInfected);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        infect(nbt.getBoolean("infected"));

    }
}

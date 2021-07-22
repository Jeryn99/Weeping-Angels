package me.suff.mc.angels.common;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AngelParticles {

    public static final DeferredRegister<ParticleType<?>> TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WeepingAngels.MODID);

    public static final RegistryObject<SimpleParticleType> INFECTION = TYPES.register("infection", () -> new SimpleParticleType(false));
}

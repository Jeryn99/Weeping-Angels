package me.suff.mc.angels.common;

import me.suff.mc.angels.WeepingAngels;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AngelParticles {

    public static final DeferredRegister<ParticleType<?>> TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WeepingAngels.MODID);

    public static final RegistryObject<BasicParticleType> INFECTION = TYPES.register("infection", () -> new BasicParticleType(false));
}

package dev.jeryn.angels.util;

import dev.jeryn.angels.WeepingAngels;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;

public class WADamageSources {

    public static final ResourceKey<DamageType> GENERATOR = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(WeepingAngels.MODID, "generator"));
    public static final ResourceKey<DamageType> PUNCH_STONE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(WeepingAngels.MODID, "punch_stone"));
    public static final ResourceKey<DamageType> SNAPPED_NECK = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(WeepingAngels.MODID, "snapped_neck"));

    public static DamageSource getSource(ServerLevel level, ResourceKey<DamageType> damageTypeResourceKey) {
        Holder.Reference<DamageType> damageType = level.registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(damageTypeResourceKey);
        return new DamageSource(damageType);
    }

}

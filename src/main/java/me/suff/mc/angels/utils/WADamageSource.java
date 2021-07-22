package me.suff.mc.angels.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

public class WADamageSource extends DamageSource {

    private final String message;

    public WADamageSource(String name) {
        super(name);
        this.message = "source.weeping_angels." + name;
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity entity) {
        return new TranslatableComponent(message, entity.getName());
    }
}

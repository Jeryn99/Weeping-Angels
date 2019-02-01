package me.sub.angels.common.misc;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.StringTextComponent;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;

public class WADamageSource extends DamageSource
{

    private String name;

    public WADamageSource(String name) {
        super(name);
        this.name = "damagesrc.angels." + name;
    }

    @Override public TextComponent getDeathMessage(LivingEntity livingEntity_1)
    {
        return new StringTextComponent(livingEntity_1.getName() + " ").append(new TranslatableTextComponent(name));
    }
}

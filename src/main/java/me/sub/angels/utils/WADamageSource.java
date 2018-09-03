package me.sub.angels.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

// To-do - Make translating less limited
public class WADamageSource extends DamageSource {

    private String name;

    public WADamageSource(String name) {
		super(name);
		this.name = "damagesrc.angels." + name;
	}

    @Override
	public ITextComponent getDeathMessage(EntityLivingBase entity) {
		return new TextComponentString(entity.getName() + " ").appendSibling(new TextComponentTranslation(name));
	}
}

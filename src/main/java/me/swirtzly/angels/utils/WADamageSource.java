package me.swirtzly.angels.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class WADamageSource extends DamageSource {
	
	private String message;
	
	public WADamageSource(String name) {
		super(name);
		this.message = "damagesrc.angels." + name;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entity) {
		return new TextComponentTranslation(message, entity.getName());
	}
}

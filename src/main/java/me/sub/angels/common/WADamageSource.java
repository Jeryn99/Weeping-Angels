package me.sub.angels.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class WADamageSource extends DamageSource {
	
	private String message = "";
	
	public WADamageSource(String damageMessage) {
		super(damageMessage);
		this.message = damageMessage;
	}
	
	@Override
	public ITextComponent getDeathMessage(EntityLivingBase entity) {
		return new TextComponentString(entity.getName() + " " + message);
	}
}

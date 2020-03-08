package me.swirtzly.angels.common.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by 50ap5ud5 on 13 Feb 2020 @ 11:56:38 am
 */
public class FortuneLevelEvent extends LivingEvent {
	
	private final DamageSource damageSource;
	
	private int fortuneLevel;
	
	public FortuneLevelEvent(LivingEntity entity, DamageSource damageSource, int fortuneLevel) {
		super(entity);
		this.damageSource = damageSource;
		this.fortuneLevel = fortuneLevel;
	}
	
	public DamageSource getDamageSource() {
		return damageSource;
	}
	
	public int getFortuneLevel() {
		return fortuneLevel;
	}
	
	public void setFortuneLevel(int fortuneLevel) {
		this.fortuneLevel = fortuneLevel;
	}
}

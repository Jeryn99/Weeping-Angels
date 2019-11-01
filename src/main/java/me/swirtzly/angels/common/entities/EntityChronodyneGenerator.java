package me.swirtzly.angels.common.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityChronodyneGenerator extends ThrowableEntity {
	
	protected EntityChronodyneGenerator(EntityType<?> type, World p_i48540_2_) {
		super(type, p_i48540_2_);
	}
	
	protected EntityChronodyneGenerator(EntityType<?> type, double p_i48541_2_, double p_i48541_4_, double p_i48541_6_, World p_i48541_8_) {
		super(type, p_i48541_2_, p_i48541_4_, p_i48541_6_, p_i48541_8_);
	}
	
	protected EntityChronodyneGenerator(EntityType<?> type, LivingEntity p_i48542_2_, World p_i48542_3_) {
		super(type, p_i48542_2_, p_i48542_3_);
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
	
	}
}

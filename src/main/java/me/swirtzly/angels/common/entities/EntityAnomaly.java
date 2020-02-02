package me.swirtzly.angels.common.entities;

import me.swirtzly.angels.common.WAObjects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public class EntityAnomaly extends MobEntity {
	
	private static final DataParameter<Float> EYE_HEIGHT = EntityDataManager.createKey(EntityAnomaly.class, DataSerializers.FLOAT);
	
	public EntityAnomaly(World worldIn) {
		super(WAObjects.EntityEntries.ANOMALY, worldIn);
	}

	public EntityAnomaly(EntityType type, World world){
		this(world);
	}

	@Override
	public boolean isInvulnerable() {
		return true;
	}
	
	@Override
	protected boolean isMovementBlocked() {
		return true;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		setNoAI(true);
		
		if (ticksExisted == 1) {
			playSound(WAObjects.Sounds.ANGEL_TELEPORT.get(), 1.0F, 1.0F);
		}
		
		if (ticksExisted > 100) {
			remove();
		}
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		getDataManager().register(EYE_HEIGHT, getEyeHeight());
	}
	
	public float getEntityEyeHeight() {
		return getDataManager().get(EYE_HEIGHT);
	}
	
	public void setEntityEyeHeight(float eyeHeight) {
		getDataManager().set(EYE_HEIGHT, eyeHeight);
	}
}

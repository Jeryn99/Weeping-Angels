package com.github.reallysub.angels.common.entities;

import com.github.reallysub.angels.common.InitEvents;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;

public class EntityAngel extends EntityMob {
	
	private static DataParameter<Boolean> isAngry = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Boolean> isSeen = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Integer> timeViewed = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	
	public EntityAngel(World world) {
		super(world);
		
		tasks.addTask(2, new EntityAIAttackMelee(this, 4.0F, false));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] { EntityPigZombie.class }));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		
		experienceValue = 25;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(isSeen, false);
		getDataManager().register(isAngry, false);
		getDataManager().register(timeViewed, 0);
	}
	
	public void travel(float strafe, float vertical, float forward) {
		if (!isSeen()) {
			super.travel(strafe, vertical, forward);
		} else {
			// Do nothing
		}
	}
	
	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	@Override
	protected void jump() {
		if (!isSeen()) {
			super.jump();
		} else {
			// Do nothing
		}
	}
	
	/**
	 * @return Returns whether the angel is being isSeen or not
	 */
	public boolean isSeen() {
		return getDataManager().get(isSeen);
	}
	
	/**
	 * @return Returns whether the angel is angry or not
	 */
	public boolean isAngry() {
		return getDataManager().get(isAngry);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setAngry(boolean angry) {
		getDataManager().set(isAngry, angry);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setSeen(boolean beingViewed) {
		getDataManager().set(isSeen, beingViewed);
	}
	
	/**
	 * @return Returns the time the angel has been isSeen for
	 */
	public int getSeenTime() {
		return getDataManager().get(timeViewed);
	}
	
	/**
	 * Set's the time the angel is being isSeen for
	 */
	public void setSeenTime(int time) {
		getDataManager().set(timeViewed, time);
	}
	
	/**
	 * Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("isSeen", isSeen());
		compound.setInteger("timeSeen", getSeenTime());
		compound.setBoolean("isAngry", isAngry());
	}
	
	/**
	 * Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey("isSeen")) setSeen(compound.getBoolean("isSeen"));
		
		if (compound.hasKey("timeSeen")) setSeenTime(compound.getInteger("timeSeen"));
		
		if (compound.hasKey("isAngry")) setAngry(compound.getBoolean("isAngry"));
	}
	
	@Override
	protected void collideWithEntity(Entity entity) {
		entity.applyEntityCollision(this);
		if (rand.nextInt(100) == 50) {
			WorldBorder border = entity.getEntityWorld().getWorldBorder();
			int x = rand.nextInt(border.getSize());
			int z = rand.nextInt(border.getSize());
			int y = world.getSpawnPoint().getY();
			entity.setPositionAndUpdate(x, y, z);
		}
	}
	
	/**
	 * Dead and sleeping entities cannot move
	 */
	@Override
	protected boolean isMovementBlocked() {
		return getHealth() <= 0.0F || isSeen();
	}
	
	@Override
	public void onUpdate() {
		
		super.onUpdate();
		
		if (rand.nextInt(500) == 250) {
			setAngry(rand.nextBoolean());
		}
		
		if (!world.isRemote) if (isSeen()) {
			setSeenTime(getSeenTime() + 1);
			if (getSeenTime() > 15) setSeen(false);
		} else {
			setSeenTime(0);
		}
	}
	
}

package com.github.reallysub.angels.common.entities;

import javax.annotation.Nullable;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.entities.enums.AngelPoses;
import com.github.reallysub.angels.main.config.WAConfig;
import com.github.reallysub.angels.utils.AngelUtils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityAngel extends EntityMob {
	
	private SoundEvent[] seenSounds = new SoundEvent[] { WAObjects.Sounds.angelSeen, WAObjects.Sounds.angelSeen_2, WAObjects.Sounds.angelSeen_3, WAObjects.Sounds.angelSeen_4, WAObjects.Sounds.angelSeen_5 };
	
	private long soundTime = 0L;
	
	private String POSE = randomPose().toString();
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	
	public EntityAngel(World world) {
		super(world);
		tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.5F, 80));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 2.0F, false));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(7, new EntityAIBreakDoor(this));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, new EntityAITempt(this, 3.5D, Item.getItemFromBlock(Blocks.TORCH), false));
		tasks.addTask(2, new EntityAITempt(this, 3.5D, Item.getItemFromBlock(Blocks.REDSTONE_TORCH), false));
		tasks.addTask(8, new EntityAIMoveThroughVillage(this, 1.0D, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		experienceValue = 25;
	}
	
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		playSound(WAObjects.Sounds.angel_ambience, 1.0F, 1.0F);
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_STONE_HIT;
	}
	
	@Override
	protected SoundEvent getDeathSound()
    {
        return WAObjects.Sounds.angelDeath;
    }
	 
	@Override
	public float getRotatedYaw(Rotation transformRotation) {
		if (!isSeen()) {
			super.getRotatedYaw(transformRotation);
		}
		return rotationYaw;
	}
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (isChild() && rand.nextInt(3) == 2) {
			return WAObjects.Sounds.laughing_child;
		}
		return null;
	}
	
	@Override
	public float getEyeHeight() {
		return this.isChild() ? this.height : 1.3F;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(135.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (this.getHealth() > 5) {
			entity.attackEntityFrom(WAObjects.ANGEL, 4.0F);
		} else {
			entity.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
		}
		return super.attackEntityAsMob(entity);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_SEEN, false);
		getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
		getDataManager().register(TIME_VIEWED, 0);
		getDataManager().register(TYPE, getRandomType());
	}
	
	private int getRandomType() {
		if (rand.nextBoolean()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public void travel(float strafe, float vertical, float forward) {
		if (!isSeen() || !onGround) {
			super.travel(strafe, vertical, forward);
		}
	}
	
	@Override
	public void move(MoverType type, double x, double y, double z) {
		if (!isSeen() || !onGround) {
			super.move(type, x, y, z);
		}
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		dropItem(Item.getItemFromBlock(Blocks.STONE), rand.nextInt(3));
		entityDropItem(getHeldItemMainhand(), getHeldItemMainhand().getCount());
		entityDropItem(getHeldItemOffhand(), getHeldItemOffhand().getCount());
	}
	
	@Override
	protected void jump() {
		if (!isSeen()) {
			super.jump();
		}
	}
	
	public boolean isSeen() {
		return getDataManager().get(IS_SEEN);
	}
	
	public String getPose() {
		return POSE;
	}
	
	public boolean isChild() {
		return getDataManager().get(IS_CHILD);
	}
	
	public void setPose(String newPose) {
		POSE = newPose;
	}
	
	public void setSeen(boolean beingViewed) {
		getDataManager().set(IS_SEEN, beingViewed);
	}
	
	public void setChild(boolean child) {
		getDataManager().set(IS_CHILD, child);
	}
	
	public int getSeenTime() {
		return getDataManager().get(TIME_VIEWED);
	}
	
	public int getType() {
		return getDataManager().get(TYPE);
	}
	
	public void setType(int angelType) {
		getDataManager().set(TYPE, angelType);
	}
	
	public void setSeenTime(int time) {
		getDataManager().set(TIME_VIEWED, time);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("isSeen", isSeen());
		compound.setInteger("timeSeen", getSeenTime());
		compound.setString("pose", getPose());
		compound.setInteger("type", getType());
		compound.setBoolean("angelChild", isChild());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey("isSeen")) setSeen(compound.getBoolean("isSeen"));
		
		if (compound.hasKey("timeSeen")) setSeenTime(compound.getInteger("timeSeen"));
		
		if (compound.hasKey("pose")) setPose(compound.getString("pose"));
		
		if (compound.hasKey("type")) setType(compound.getInteger("type"));
		
		if (compound.hasKey("angelChild")) setChild(compound.getBoolean("angelChild"));
	}
	
	@Override
	protected void collideWithEntity(Entity entity) {
		entity.applyEntityCollision(this);
		
		if (WAConfig.angels.teleportEntities && !isChild() && !(entity instanceof EntityAngel) && rand.nextInt(100) == 50 && !(entity instanceof EntityPainting) && !(entity instanceof EntityItemFrame) && !(entity instanceof EntityItem) && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable)) {
			int dimID = 0;
			
			if (WAConfig.angels.angelDimTeleport) {
				dimID = world.rand.nextInt(DimensionManager.getStaticDimensionIDs().length);
			} else {
				dimID = dimension;
			}
			
			if (DimensionManager.isDimensionRegistered(dimID) && dimID != 1) {
				int x = entity.getPosition().getX() + rand.nextInt(WAConfig.angels.teleportRange);
				int z = entity.getPosition().getZ() + rand.nextInt(WAConfig.angels.teleportRange);
				int y = world.getHeight(x, z);
				heal(4.0F);
				entity.playSound(WAObjects.Sounds.angel_teleport, 1.0F, 1.0F);
				teleport(entity, x, y, z, dimID);
			}
		}
	}
	
	private boolean teleport(Entity entity, double x, double y, double z, int dim) {
		BlockPos p = new BlockPos(x, y, z);
		
		if (world.isAirBlock(p)) {
			if (world.getBlockState(p.add(0, -1, 0)).getMaterial().isSolid()) {
				AngelUtils.teleportDimEntity(entity, new BlockPos(x, y, z), dim);
			} else {
				for (int i = 1; i < 255; i++) {
					if (world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
						AngelUtils.teleportDimEntity(entity, new BlockPos(x, i, z), dim);
					}
				}
			}
		} else {
			for (int i = 1; i < 255; i++) {
				if (world.isAirBlock(p.add(0, -p.getY() + i, 0)) && world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
					AngelUtils.teleportDimEntity(entity, new BlockPos(x, i, z), dim);
				}
			}
		}
		return true;
	}
	
	/**
	 * Dead and sleeping entities cannot move
	 */
	@Override
	protected boolean isMovementBlocked() {
		return getHealth() <= 0.0F || isSeen();
	}
	
	/**
	 * Sets the rotation of the entity.
	 */
	@Override
	protected void setRotation(float yaw, float pitch) {
		if (!isSeen()) {
			super.setRotation(yaw, pitch);
		}
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (isChild() && getAttackTarget() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) getAttackTarget();
			if (getDistance(getAttackTarget()) <= 2) {
				AngelUtils.blowOutTorch(player);
			}
		}
		
		if (!world.isRemote) if (isSeen()) {
			setSeenTime(getSeenTime() + 1);
			if (getSeenTime() > 15) setSeen(false);
		} else {
			setSeenTime(0);
		}
		
		replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
	}
	
	@SubscribeEvent
	public static void cancelDamage(LivingAttackEvent e) {
		Entity source = e.getSource().getTrueSource();
		if (source != null && source instanceof EntityLivingBase) {
			EntityLivingBase attacker = (EntityLivingBase) source;
			EntityLivingBase victim = e.getEntityLiving();
			
			if (victim instanceof EntityAngel) {
				ItemStack item = attacker.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
				boolean isPic = item.getItem() instanceof ItemPickaxe;
				e.setCanceled(!isPic);
				
				if (!isPic) {
					attacker.attackEntityFrom(WAObjects.STONE, 2.5F);
				} else {
					ItemPickaxe pick = (ItemPickaxe) item.getItem();
					
					if (pick != Items.DIAMOND_PICKAXE && victim.world.getDifficulty() == EnumDifficulty.HARD) {
						e.setCanceled(true);
					}
					
					victim.playSound(SoundEvents.BLOCK_STONE_BREAK, 1.0F, 1.0F);
					pick.setDamage(item, pick.getDamage(item) - 1);
				}
				
				if (!(source instanceof Entity)) {
					e.setCanceled(true);
				}
			}
		}
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		if (prevPosX != posX && prevPosZ != posZ) {
			if (!isChild()) {
				playSound(WAObjects.Sounds.stone_scrap, 0.5F, 1.0F);
			} else {
				if (world.rand.nextInt(5) == 5) {
					playSound(WAObjects.Sounds.child_run, 1.0F, 1.0F);
				}
			}
		}
	}
	
	private void replaceBlocks(AxisAlignedBB box) {
		
		boolean stop = false;
		
		if (world.isRemote || ticksExisted % 100 != 0) return;
		
		for (int x = (int) box.minX; x <= box.maxX && !stop; x++) {
			for (int y = (int) box.minY; y <= box.maxY && !stop; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ && !stop; z++) {
					
					BlockPos pos = new BlockPos(x, y, z);
					
					Block block = world.getBlockState(pos).getBlock();
					
					// Breaking Start
					if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {
						if (block == Blocks.TORCH || block == Blocks.REDSTONE_TORCH || block == Blocks.GLOWSTONE || block.getLightValue(block.getDefaultState()) >= 7) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							createItem(pos, new ItemStack(Item.getItemFromBlock(block)));
							stop = true;
						}
						
						if (block == Blocks.LIT_PUMPKIN) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							world.setBlockState(pos, Blocks.PUMPKIN.getDefaultState());
							stop = true;
						}
						
						if (block == Blocks.LIT_REDSTONE_LAMP) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							world.setBlockState(pos, Blocks.REDSTONE_LAMP.getDefaultState());
							stop = true;
						}
						
						if (block instanceof BlockPortal || block instanceof BlockEndPortal) {
							if (world.rand.nextInt(700) < 100 && getHealth() < getMaxHealth()) {
								heal(1.5F);
								world.setBlockToAir(pos);
								stop = true;
							}
						}
					}
				}
			}
		}
	}
	
	public AngelPoses randomPose() {
		AngelPoses[] poses = AngelPoses.values();
		return poses[rand.nextInt(poses.length)];
	}
	
	public SoundEvent getSeenSound() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - soundTime >= 1000) {
			soundTime = currentTime;
			SoundEvent sound = seenSounds[rand.nextInt(seenSounds.length)];
			return sound;
		}
		return null;
	}
	
	public void createItem(BlockPos pos, ItemStack stack) {
		if (!world.isRemote) {
			EntityItem item = new EntityItem(world);
			item.setItem(stack);
			world.spawnEntity(item);
			item.setPosition(pos.getX(), pos.getY(), pos.getZ());
		}
	}
}

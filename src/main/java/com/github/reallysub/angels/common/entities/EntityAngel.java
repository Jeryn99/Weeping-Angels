package com.github.reallysub.angels.common.entities;

import javax.annotation.Nullable;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.entities.enums.AngelPoses;
import com.github.reallysub.angels.main.AngelUtils;
import com.github.reallysub.angels.main.config.Config;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
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
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber
public class EntityAngel extends EntityMob {
	
	private SoundEvent[] seenSounds = new SoundEvent[] { WAObjects.Sounds.angelSeen, WAObjects.Sounds.angelSeen_2, WAObjects.Sounds.angelSeen_3, WAObjects.Sounds.angelSeen_4, WAObjects.Sounds.angelSeen_5 };
	
	private long soundTime = 0L;
	
	private static DataParameter<String> POSE = EntityDataManager.<String>createKey(EntityAngel.class, DataSerializers.STRING);
	private static DataParameter<Boolean> IS_SEEN = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Integer> TIME_VIEWED = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<Integer> TYPE = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<BlockPos> BLOCK_BREAK_POS = EntityDataManager.<BlockPos>createKey(EntityAngel.class, DataSerializers.BLOCK_POS);
	private static DataParameter<Boolean> IS_CHILD = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	
	public EntityAngel(World world) {
		super(world);
		tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 3.0F, 80));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 3.0F, false));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(7, new EntityAIBreakDoor(this));
		tasks.addTask(2, new EntityAITempt(this, 9.5D, Item.getItemFromBlock(Blocks.TORCH), false));
		tasks.addTask(2, new EntityAITempt(this, 9.5D, Item.getItemFromBlock(Blocks.REDSTONE_TORCH), false));
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
		
		if (entity instanceof EntityPlayer && world.rand.nextInt(100) < 20) {
			EntityPlayer player = (EntityPlayer) entity;
			AngelUtils.getForTorch(player, EnumHand.MAIN_HAND);
			AngelUtils.getForTorch(player, EnumHand.OFF_HAND);
		}
		
		if (rand.nextBoolean()) {
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
		getDataManager().register(POSE, AngelPoses.IDLE.toString());
		getDataManager().register(IS_CHILD, randomChild());
		getDataManager().register(TIME_VIEWED, 0);
		getDataManager().register(TYPE, randomType());
		getDataManager().register(BLOCK_BREAK_POS, BlockPos.ORIGIN);
	}
	
	@Override
	public void travel(float strafe, float vertical, float forward) {
		if (!isSeen() || !onGround) {
			super.travel(strafe, vertical, forward);
		}
	}
	
	/**
	 * Drop 0-2 items of this living's type
	 */
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		dropItem(Item.getItemFromBlock(Blocks.STONE), rand.nextInt(3));
	}
	
	/**
	 * Causes this entity to do an upwards motion (jumping).
	 */
	@Override
	protected void jump() {
		if (!isSeen()) {
			super.jump();
		}
	}
	
	public BlockPos getBreakPos() {
		return getDataManager().get(BLOCK_BREAK_POS);
	}
	
	public void setBreakBlockPos(BlockPos pos) {
		getDataManager().set(BLOCK_BREAK_POS, pos);
	}
	
	/**
	 * @return Returns whether the angel is being isSeen or not
	 */
	public boolean isSeen() {
		return getDataManager().get(IS_SEEN);
	}
	
	/**
	 * @return Returns the angels pose
	 */
	public String getPose() {
		return getDataManager().get(POSE);
	}
	
	public boolean isChild() {
		return getDataManager().get(IS_CHILD);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setPose(String newPose) {
		getDataManager().set(POSE, newPose);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setSeen(boolean beingViewed) {
		getDataManager().set(IS_SEEN, beingViewed);
	}
	
	public void setChild(boolean child) {
		getDataManager().set(IS_CHILD, child);
	}
	
	/**
	 * @return Returns the time the angel has been isSeen for
	 */
	public int getSeenTime() {
		return getDataManager().get(TIME_VIEWED);
	}
	
	/**
	 * @return Returns the angel type
	 */
	public int getType() {
		return getDataManager().get(TYPE);
	}
	
	/**
	 * Set's the angel type
	 */
	public void setType(int angelType) {
		getDataManager().set(TYPE, angelType);
	}
	
	/**
	 * Set's the time the angel is being isSeen for
	 */
	public void setSeenTime(int time) {
		getDataManager().set(TIME_VIEWED, time);
	}
	
	/**
	 * Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("isSeen", isSeen());
		compound.setInteger("timeSeen", getSeenTime());
		compound.setString("pose", getPose());
		compound.setInteger("type", getType());
		compound.setBoolean("angelChild", isChild());
	}
	
	/**
	 * Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey("isSeen")) setSeen(compound.getBoolean("isSeen"));
		
		if (compound.hasKey("timeSeen")) setSeenTime(compound.getInteger("timeSeen"));
		
		if (compound.hasKey("pose")) setPose(compound.getString("pose"));
		
		if (compound.hasKey("type")) setType(compound.getInteger("type"));
		
		if (compound.hasKey("angelChild")) setChild(compound.getBoolean("angelChild"));
	}
	
	/**
	 * When a entity collides with this entity
	 */
	@Override
	protected void collideWithEntity(Entity entity) {
		entity.applyEntityCollision(this);
		if (Config.teleportEntities && !isChild() && !(entity instanceof EntityAngel) && rand.nextInt(100) == 50 && !(entity instanceof EntityPainting) && !(entity instanceof EntityItemFrame) && !(entity instanceof EntityItem) && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable)) {
			int dimID = 0;
			
			if (Config.angelDimTeleport) {
				dimID = world.rand.nextInt(DimensionManager.getStaticDimensionIDs().length);
			} else {
				dimID = dimension;
			}
			
			if (DimensionManager.isDimensionRegistered(dimID) && dimID != 1) {
				int x = entity.getPosition().getX() + rand.nextInt(Config.teleportRange);
				int z = entity.getPosition().getZ() + rand.nextInt(Config.teleportRange);
				int y = world.getHeight(x, z);
				AngelUtils.teleportDimEntity(entity, new BlockPos(x, y, z), dimID);
				heal(4.0F);
				entity.playSound(WAObjects.Sounds.angel_teleport, 1.0F, 1.0F);
			}
		}
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
		
		if (!isSeen() && ticksExisted % 100 != 0) {
			setPose(randomPose().toString());
		}
		
		if (!world.isRemote) if (isSeen()) {
			setSeenTime(getSeenTime() + 1);
			if (getSeenTime() > 21) setSeen(false);
		} else {
			setSeenTime(0);
		}
		
		replaceBlocks(getEntityBoundingBox().grow(25, 25, 25));
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
		
		if (getEntityWorld().isRemote || ticksExisted % 100 != 0) return;
		
		for (int x = (int) box.minX; x <= box.maxX && !stop; x++) {
			for (int y = (int) box.minY; y <= box.maxY && !stop; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ && !stop; z++) {
					
					BlockPos pos = new BlockPos(x, y, z);
					
					Block block = getEntityWorld().getBlockState(pos).getBlock();
					
					// Breaking Start
					if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {
						if (block == Blocks.TORCH || block == Blocks.REDSTONE_TORCH || block == Blocks.GLOWSTONE || block.getLightValue(block.getDefaultState()) >= 7) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							spawnItem(world, new ItemStack(Item.getItemFromBlock(block)), pos);
							stop = true;
						}
						
						if (block == Blocks.LIT_PUMPKIN) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							getEntityWorld().setBlockState(pos, Blocks.PUMPKIN.getDefaultState());
							stop = true;
						}
						
						if (block == Blocks.LIT_REDSTONE_LAMP) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							getEntityWorld().setBlockState(pos, Blocks.REDSTONE_LAMP.getDefaultState());
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
	
	private static final List<AngelPoses> VALUES = Collections.unmodifiableList(Arrays.asList(AngelPoses.values()));
	
	private AngelPoses randomPose() {
		return VALUES.get(world.rand.nextInt(VALUES.size()));
	}
	
	private int randomType() {
		if (rand.nextBoolean()) {
			return 1;
		}
		return 0;
	}
	
	private boolean randomChild() {
		return rand.nextInt(10) == 4;
	}
	
	private void spawnItem(World world, ItemStack itemStack, BlockPos pos) {
		if (!world.isRemote) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
			world.spawnEntity(item);
		}
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
}

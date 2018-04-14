package com.github.reallysub.angels.common.entities;

import javax.annotation.Nullable;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.common.entities.enums.AngelPoses;
import com.github.reallysub.angels.main.AngelUtils;
import com.github.reallysub.angels.main.config.Config;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
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
import net.minecraft.entity.monster.EntityZombie;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
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
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Mod.EventBusSubscriber
public class EntityAngel extends EntityMob {
	
	private SoundEvent[] seenSounds = new SoundEvent[] { WAObjects.Sounds.angelSeen, WAObjects.Sounds.angelSeen_2, WAObjects.Sounds.angelSeen_3, WAObjects.Sounds.angelSeen_4, WAObjects.Sounds.angelSeen_5 };
	
	long actionTime = 0L;
	
	private static DataParameter<String> pose = EntityDataManager.<String>createKey(EntityAngel.class, DataSerializers.STRING);
	private static DataParameter<Boolean> isSeen = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Integer> timeViewed = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<Integer> type = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<BlockPos> blockBreakPos = EntityDataManager.<BlockPos>createKey(EntityAngel.class, DataSerializers.BLOCK_POS);
	private static DataParameter<Boolean> isChild = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	
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
		this.playSound(WAObjects.Sounds.angel_ambience, 1.0F, 1.0F);
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
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			AngelUtils.getForTorch(player, EnumHand.MAIN_HAND);
			AngelUtils.getForTorch(player, EnumHand.OFF_HAND);
		}
		
		if (rand.nextInt(4) < 2) {
			entity.attackEntityFrom(WAObjects.ANGEL, 4.0F);
		} else {
			entity.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
		}
		return super.attackEntityAsMob(entity);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(isSeen, false);
		getDataManager().register(pose, AngelPoses.HIDING_FACE.toString());
		getDataManager().register(isChild, randomChild());
		getDataManager().register(timeViewed, 0);
		getDataManager().register(type, randomType());
		getDataManager().register(blockBreakPos, BlockPos.ORIGIN);
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
		return getDataManager().get(blockBreakPos);
	}
	
	public void setBreakBlockPos(BlockPos pos) {
		getDataManager().set(blockBreakPos, pos);
	}
	
	/**
	 * @return Returns whether the angel is being isSeen or not
	 */
	public boolean isSeen() {
		return getDataManager().get(isSeen);
	}
	
	/**
	 * @return Returns the angels pose
	 */
	public String getPose() {
		return getDataManager().get(pose);
	}
	
	public boolean isChild() {
		return getDataManager().get(isChild);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setPose(String newPose) {
		String p2Str = newPose.toString();
		getDataManager().set(pose, p2Str);
	}
	
	/**
	 * Set's whether the angel is being isSeen or not
	 */
	public void setSeen(boolean beingViewed) {
		getDataManager().set(isSeen, beingViewed);
	}
	
	public void setChild(boolean child) {
		getDataManager().set(isChild, child);
	}
	
	/**
	 * @return Returns the time the angel has been isSeen for
	 */
	public int getSeenTime() {
		return getDataManager().get(timeViewed);
	}
	
	/**
	 * @return Returns the angel type
	 */
	public int getType() {
		return getDataManager().get(type);
	}
	
	/**
	 * Set's the angel type
	 */
	public void setType(int angelType) {
		getDataManager().set(type, angelType);
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
			int dimID = world.rand.nextInt(DimensionManager.getStaticDimensionIDs().length);
			
			if (DimensionManager.isDimensionRegistered(dimID)) {
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
	public void onUpdate() {
		super.onUpdate();
		
		if (!isSeen() && rand.nextInt(15) == 5) {
			setPose(randomPose().toString());
		}
		
		if (!world.isRemote) if (isSeen()) {
			setSeenTime(getSeenTime() + 1);
			if (getSeenTime() > 21) setSeen(false);
		} else {
			setSeenTime(0);
		}
		
		// DO NOT REMOVE THAT RANDOM CHANCE, OTHERWISE EARS = GONE.
		if (!isSeen() && world.getGameRules().getBoolean("mobGriefing") && rand.nextInt(2500) == 50) {
			replaceBlocks(getEntityBoundingBox().grow(25, 25, 25));
		}
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
		for (int x = (int) box.minX; x <= box.maxX; x++) {
			for (int y = (int) box.minY; y <= box.maxY; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					Block block = getEntityWorld().getBlockState(pos).getBlock();
					
					if (block == Blocks.TORCH && !stop || block == Blocks.REDSTONE_TORCH && !stop || block == Blocks.GLOWSTONE && !stop) {
						if (shouldBreak()) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, 1, 0)));
							stop = true;
						}
					}
					
					if (block == Blocks.LIT_PUMPKIN && !stop) {
						setBreakBlockPos(pos);
						if (shouldBreak()) {
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
					}
					
					if (block == Blocks.LIT_REDSTONE_LAMP && !stop) {
						
						if (shouldBreak()) {
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
					}
					
					if (block.getLightValue(block.getDefaultState()) >= 7 && !stop) {
						playSound(WAObjects.Sounds.light_break, 1.0F, 1.0F);
						world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, 1, 0)));
						stop = true;
					}
				}
			}
		}
	}
	
	private static final List<AngelPoses> VALUES = Collections.unmodifiableList(Arrays.asList(AngelPoses.values()));
	
	private AngelPoses randomPose() {
		return VALUES.get(world.rand.nextInt(VALUES.size()));
	}
	
	private boolean shouldBreak() {
		return getHealth() > 5 && rand.nextInt(10) < 5;
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
	
	public SoundEvent getSeenSound() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - actionTime >= 1000) {
			actionTime = currentTime;
			SoundEvent sound = seenSounds[rand.nextInt(seenSounds.length)];
			System.out.println(sound.getSoundName() + " " + getSeenTime());
			return sound;
		}
		return null;
	}
}

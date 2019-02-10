package me.suff.angels.common.entities;

import me.suff.angels.client.models.poses.PoseManager;
import me.suff.angels.common.WAObjects;
import me.suff.angels.common.misc.WAConstants;
import me.suff.angels.config.WAConfig;
import me.suff.angels.utils.AngelUtils;
import me.suff.angels.utils.EnumTeleportType;
import me.suff.angels.utils.Teleporter;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityWeepingAngel extends EntityQuantumLockBase {
	
	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<String> CURRENT_POSE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.STRING);
	private static final DataParameter<Integer> HUNGER_LEVEL = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
	
	private SoundEvent[] SEEN_SOUNDS = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5, WAObjects.Sounds.ANGEL_SEEN_6, WAObjects.Sounds.ANGEL_SEEN_7, WAObjects.Sounds.ANGEL_SEEN_8};
	private SoundEvent[] CHILD_SOUNDS = new SoundEvent[]{SoundEvents.ENTITY_VEX_AMBIENT, WAObjects.Sounds.LAUGHING_CHILD};
	
	public EntityWeepingAngel(World world) {
		super(world);
		tasks.addTask(0, new EntityAIBreakDoor(this));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 50.0F));
		experienceValue = WAConfig.angels.xpGained;
	}
	
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
		getDataManager().register(TYPE, AngelUtils.randomType().getId());
		getDataManager().register(CURRENT_POSE, PoseManager.getRandomPose().getRegistryName());
		getDataManager().register(HUNGER_LEVEL, 50);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		playSound(WAObjects.Sounds.ANGEL_AMBIENT, 0.5F, 1.0F);
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_STONE_HIT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return WAObjects.Sounds.ANGEL_DEATH;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		if (isCherub() && ticksExisted % AngelUtils.secondsToTicks(2) == 0) {
			return CHILD_SOUNDS[rand.nextInt(CHILD_SOUNDS.length)];
		}
		return null;
	}
	
	@Override
	public float getEyeHeight() {
		return isCherub() ? height : 1.3F;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.angels.damage);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(9999999.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		
		if (entity instanceof EntityPlayerMP) {
			
			EntityPlayerMP playerMP = (EntityPlayerMP) entity;
			
			//Blowing out light items from the players hand
			if (WAConfig.angels.torchBlowOut && isCherub()) {
				AngelUtils.removeLightFromHand(playerMP, this);
			}
			
			//Steals keys from the player
			if (getHeldItemMainhand().isEmpty() && rand.nextBoolean()) {
				for (int i = 0; i < playerMP.inventory.getSizeInventory(); i++) {
					ItemStack stack = playerMP.inventory.getStackInSlot(i);
					for (String regName : WAConstants.KEYS) {
						if (regName.matches(stack.getItem().getRegistryName().toString())) {
							setHeldItem(EnumHand.MAIN_HAND, playerMP.inventory.getStackInSlot(i).copy());
							playerMP.inventory.getStackInSlot(i).setCount(0);
							playerMP.inventoryContainer.detectAndSendChanges();
						}
					}
				}
			}
			
			
			//Teleporting and damage
			if (WAConfig.teleport.justTeleport) {
				if (!isCherub()) {
					teleportInteraction(playerMP);
					return false;
				} else {
					dealDamage(playerMP);
					return true;
				}
			} else {
				boolean shouldTeleport = rand.nextInt(10) < 5 && !isWeak();
				if (shouldTeleport) {
					teleportInteraction(playerMP);
					return false;
				} else {
					dealDamage(playerMP);
					return true;
				}
			}
			
		}
		return true;
	}
	
	
	public void dealDamage(EntityPlayer playerMP) {
		if (getHealth() > 5) {
			playerMP.attackEntityFrom(WAObjects.ANGEL, 4.0F);
			heal(4.0F);
		} else {
			playerMP.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
			heal(2.0F);
		}
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		dropItem(Item.getItemFromBlock(Blocks.STONE), rand.nextInt(3));
		entityDropItem(getHeldItemMainhand(), getHeldItemMainhand().getCount());
		entityDropItem(getHeldItemOffhand(), getHeldItemOffhand().getCount());
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setString(WAConstants.POSE, getPose());
		compound.setInteger(WAConstants.TYPE, getType());
		compound.setBoolean(WAConstants.ANGEL_CHILD, isCherub());
		compound.setInteger(WAConstants.HUNGER_LEVEL, getHungerLevel());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey(WAConstants.POSE)) setPose(compound.getString(WAConstants.POSE));
		
		if (compound.hasKey(WAConstants.TYPE)) setType(compound.getInteger(WAConstants.TYPE));
		
		if (compound.hasKey(WAConstants.ANGEL_CHILD)) setChild(compound.getBoolean(WAConstants.ANGEL_CHILD));
		
		if (compound.hasKey(WAConstants.HUNGER_LEVEL)) setHungerLevel(compound.getInteger(WAConstants.HUNGER_LEVEL));
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (ticksExisted % 2400 == 0 && !world.isRemote) {
			setHungerLevel(getHungerLevel() - 1);
			if (isWeak()) {
				attackEntityFrom(DamageSource.STARVE, 2);
			}
		}
	}
	
	@Override
	public void invokeSeen(EntityPlayer player) {
		super.invokeSeen(player);
		
		if (player instanceof EntityPlayerMP && getSeenTime() == 1 && getPrevPos().toLong() != getPosition().toLong() && !player.isCreative()) {
			setPrevPos(getPosition());
			if (WAConfig.angels.playSeenSounds) {
				((EntityPlayerMP) player).connection.sendPacket(new SPacketSoundEffect(getSeenSound(), SoundCategory.HOSTILE, player.posX, player.posY, player.posZ, 1.0F, 1.0F));
			}
			if (getType() != AngelEnums.AngelType.ANGEL_THREE.getId()) {
				setPose(PoseManager.getRandomPose().getRegistryName());
			} else {
				setPose(rand.nextBoolean() ? PoseManager.POSE_ANGRY.getRegistryName() : PoseManager.POSE_HIDING_FACE.getRegistryName());
			}
		}
	}
	
	public SoundEvent getSeenSound() {
		return SEEN_SOUNDS[rand.nextInt(SEEN_SOUNDS.length)];
	}
	
	
	@Override
	public void moveTowards(EntityLivingBase entity) {
		super.moveTowards(entity);
		if (isQuantumLocked()) return;
		if (WAConfig.angels.playScrapSounds && !isCherub()) {
			playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
		}
		
		if (isCherub()) {
			if (world.rand.nextInt(5) == 5) {
				playSound(WAObjects.Sounds.CHILD_RUN, 1.0F, 1.0F);
			}
		}
	}
	
	public boolean isWeak() {
		return getHungerLevel() < 15;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (getSeenTime() == 0 || world.getLight(getPosition()) == 0 || world.isAirBlock(getPosition().down())) {
			setNoAI(false);
		}
		
		if (ticksExisted % 500 == 0 && getAttackTarget() == null && !isQuantumLocked() && getSeenTime() == 0) {
			setPose(PoseManager.POSE_HIDING_FACE.toString());
		}
		replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange));
	}
	
	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if (entityLivingIn instanceof EntityPlayer) {
			playSound(WAObjects.Sounds.ANGEL_NECK_SNAP, 1, 1);
		}
	}
	
	@Override
	protected PathNavigate createNavigator(World worldIn) {
		PathNavigateGround navigator = new PathNavigateGround(this, worldIn);
		navigator.setCanSwim(false);
		navigator.setBreakDoors(true);
		navigator.setAvoidSun(false);
		return navigator;
	}
	
	private void replaceBlocks(AxisAlignedBB box) {
		if (world.isRemote || !WAConfig.angels.blockBreaking || ticksExisted % 100 != 0 || isQuantumLocked()) return;
		
		if (world.getLight(getPosition()) == 0) {
			return;
		}
		
		for (BlockPos pos : BlockPos.getAllInBox(new BlockPos(box.minX, box.minY, box.minZ), new BlockPos(box.maxX, box.maxY, box.maxZ))) {
			IBlockState blockState = world.getBlockState(pos);
			if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {
				
				if (!canBreak(blockState) || blockState.getBlock() == Blocks.LAVA || blockState.getBlock() == Blocks.AIR) {
					continue;
				}
				
				if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE) {
					AngelUtils.playBreakEvent(this, pos, Blocks.AIR);
					return;
				}
				
				if (blockState.getBlock() == Blocks.LIT_PUMPKIN) {
					AngelUtils.playBreakEvent(this, pos, Blocks.PUMPKIN);
					return;
				}
				
				if (blockState.getBlock() == Blocks.LIT_REDSTONE_LAMP) {
					AngelUtils.playBreakEvent(this, pos, Blocks.REDSTONE_LAMP);
					return;
				}
				
				if (blockState.getBlock() instanceof BlockPortal || blockState.getBlock() instanceof BlockEndPortal) {
					if (getHealth() < getMaxHealth()) {
						heal(1.5F);
						world.setBlockToAir(pos);
					}
				} else
					continue;
				
				return;
			}
		}
	}
	
	private boolean canBreak(IBlockState blockState) {
		for (String regName : WAConfig.angels.disAllowedBlocks) {
			if (blockState.getBlock().getRegistryName().toString().equals(regName)) {
				return false;
			}
		}
		return true;
	}
	
	private void teleportInteraction(EntityPlayer player) {
		if (world.isRemote) return;
		
		EnumTeleportType type = WAConfig.teleport.teleportType;
		
		switch (type) {
			case DONT:
				break;
			case STRUCTURES:
				Teleporter.handleStructures(player);
				break;
			case RANDOM_PLACE:
				if (rand.nextBoolean()) {
					BlockPos pos = new BlockPos(player.posX + rand.nextInt(WAConfig.teleport.teleportRange), 0, player.posZ + rand.nextInt(WAConfig.teleport.teleportRange));
					Teleporter.moveSafeAcrossDim(player, pos);
				} else {
					Teleporter.handleStructures(player);
				}
				break;
		}
	}
	
	public void dropStuff() {
		dropFewItems(true, 4);
	}
	
	public String getPose() {
		return getDataManager().get(CURRENT_POSE);
	}
	
	public void setPose(String newPose) {
		getDataManager().set(CURRENT_POSE, newPose);
	}
	
	public boolean isCherub() {
		return getDataManager().get(IS_CHILD);
	}
	
	public void setChild(boolean child) {
		getDataManager().set(IS_CHILD, child);
	}
	
	public int getType() {
		return getDataManager().get(TYPE);
	}
	
	public void setType(int angelType) {
		getDataManager().set(TYPE, angelType);
	}
	
	public int getHungerLevel() {
		return getDataManager().get(HUNGER_LEVEL);
	}
	
	public void setHungerLevel(int hunger) {
		getDataManager().set(HUNGER_LEVEL, hunger);
	}
}


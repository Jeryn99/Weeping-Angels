package me.swirtzly.angels.common.entities;


import me.swirtzly.angels.client.models.poses.PoseManager;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.misc.WAConstants;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.utils.AngelUtils;
import me.swirtzly.angels.utils.Teleporter;
import me.swirtzly.angels.utils.TeleporterNew;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPlaySoundEffectPacket;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.function.Predicate;

import static me.swirtzly.angels.utils.TeleporterNew.yCoordSanity;

public class EntityWeepingAngel extends EntityQuantumLockBase {
	
	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<String> CURRENT_POSE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.STRING);
	private static final DataParameter<Integer> HUNGER_LEVEL = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);

	private SoundEvent[] SEEN_SOUNDS = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1.get(), WAObjects.Sounds.ANGEL_SEEN_2.get(), WAObjects.Sounds.ANGEL_SEEN_3.get(), WAObjects.Sounds.ANGEL_SEEN_4.get(), WAObjects.Sounds.ANGEL_SEEN_5.get(), WAObjects.Sounds.ANGEL_SEEN_6.get(), WAObjects.Sounds.ANGEL_SEEN_7.get(), WAObjects.Sounds.ANGEL_SEEN_8.get()};
	private SoundEvent[] CHILD_SOUNDS = new SoundEvent[]{SoundEvents.ENTITY_VEX_AMBIENT, WAObjects.Sounds.LAUGHING_CHILD.get()};

	private static final Predicate<Difficulty> DIFFICULTY = (p_213697_0_) -> {
		return p_213697_0_ == Difficulty.EASY;
	};

	public EntityWeepingAngel(EntityType<? extends EntityQuantumLockBase> type, World world) {
		this(world);
	}

	public EntityWeepingAngel(World world) {
		super(world, WAObjects.EntityEntries.WEEPING_ANGEL.get());
		goalSelector.addGoal(0, new BreakDoorGoal(this, DIFFICULTY));
		goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1.0D));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 50.0F));
		experienceValue = WAConfig.CONFIG.xpGained.get();
	}


	@Override
	protected void registerData() {
		super.registerData();
		getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
		getDataManager().register(TYPE, AngelUtils.randomType().getId());
		getDataManager().register(CURRENT_POSE, PoseManager.getRandomPose().getRegistryName());
		getDataManager().register(HUNGER_LEVEL, 50);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
		playSound(WAObjects.Sounds.ANGEL_AMBIENT.get(), 0.5F, 1.0F);
		return super.onInitialSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_STONE_HIT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return WAObjects.Sounds.ANGEL_DEATH.get();
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		if (isCherub() && ticksExisted % AngelUtils.secondsToTicks(2) == 0) {
			return CHILD_SOUNDS[rand.nextInt(CHILD_SOUNDS.length)];
		}
		return null;
	}

	@Override
	public float getEyeHeight(Pose p_213307_1_) {
		return isCherub() ? getHeight() : 1.3F;
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.CONFIG.damage.get());
		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(9999999.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}
	
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		
		if (entity instanceof ServerPlayerEntity) {
			
			ServerPlayerEntity playerMP = (ServerPlayerEntity) entity;
			
			//Blowing out light items from the players hand
			if (WAConfig.CONFIG.torchBlowOut.get() && isCherub()) {
				AngelUtils.removeLightFromHand(playerMP, this);
			}
			
			//Steals keys from the player
			if (getHeldItemMainhand().isEmpty() && rand.nextBoolean()) {
				for (int i = 0; i < playerMP.inventory.getSizeInventory(); i++) {
					ItemStack stack = playerMP.inventory.getStackInSlot(i);
					for (String regName : WAConstants.KEYS) {
						if (regName.matches(stack.getItem().getRegistryName().toString())) {
							setHeldItem(Hand.MAIN_HAND, playerMP.inventory.getStackInSlot(i).copy());
							playerMP.inventory.getStackInSlot(i).setCount(0);
							playerMP.container.detectAndSendChanges();
						}
					}
				}
			}
			
			
			//Teleporting and damage
			if (WAConfig.CONFIG.justTeleport.get()) {
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
	
	
	public void dealDamage(PlayerEntity playerMP) {
		if (getHealth() > 5) {
			playerMP.attackEntityFrom(WAObjects.ANGEL, 4.0F);
			heal(4.0F);
		} else {
			playerMP.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
			heal(2.0F);
		}
	}
	

	@Override
	protected boolean canDropLoot() {
		return true;
	}
	
	/*Drops Tardis Keys on Death + uses loot table drops
	 * Used to allow for config value defined tardis keys to be dropped
	 * Used instead of adding loot table functions
	 * 	N.B.There is a loot table function that does the same thing, but it requires:
	 *  -Hardcoded item registry names
	 *  -New entry for each tardis key (There could be many Tardis keys/items the player wants the angel to steal and drop on death
	 */
	
	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		entityDropItem(getHeldItemMainhand());
		entityDropItem(getHeldItemOffhand());
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putString(WAConstants.POSE, getAngelPose());
		compound.putInt(WAConstants.TYPE, getAngelType());
		compound.putBoolean(WAConstants.ANGEL_CHILD, isCherub());
		compound.putInt(WAConstants.HUNGER_LEVEL, getHungerLevel());
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);

		if (compound.contains(WAConstants.POSE)) setPose(compound.getString(WAConstants.POSE));

		if (compound.contains(WAConstants.TYPE)) setType(compound.getInt(WAConstants.TYPE));

		if (compound.contains(WAConstants.ANGEL_CHILD)) setChild(compound.getBoolean(WAConstants.ANGEL_CHILD));

		if (compound.contains(WAConstants.HUNGER_LEVEL)) setHungerLevel(compound.getInt(WAConstants.HUNGER_LEVEL));
	}


	@Override
	public void livingTick() {
		super.livingTick();
		if (ticksExisted % 2400 == 0 && !world.isRemote) {
			setHungerLevel(getHungerLevel() - 1);
			if (isWeak()) {
				attackEntityFrom(DamageSource.STARVE, 2);
			}
		}
	}
	
	@Override
	public void invokeSeen(PlayerEntity player) {
		super.invokeSeen(player);
		
		if (player instanceof ServerPlayerEntity && getSeenTime() == 1 && getPrevPos().toLong() != getPosition().toLong() && !player.isCreative()) {
			setPrevPos(getPosition());
			if (WAConfig.CONFIG.playSeenSounds.get() && player.getDistance(this) < 10) {
				((ServerPlayerEntity) player).connection.sendPacket(new SPlaySoundEffectPacket(getSeenSound(), SoundCategory.HOSTILE, player.posX, player.posY, player.posZ, 1.0F, 1.0F));
			}
			if (getAngelType() != AngelEnums.AngelType.ANGEL_THREE.getId()) {
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
	public void moveTowards(LivingEntity entity) {
		super.moveTowards(entity);
		if (isQuantumLocked()) return;
		if (WAConfig.CONFIG.playScrapSounds.get() && !isCherub()) {
			playSound(WAObjects.Sounds.STONE_SCRAP.get(), 0.2F, 1.0F);
		}
		
		if (isCherub()) {
			if (world.rand.nextInt(5) == 5) {
				playSound(WAObjects.Sounds.CHILD_RUN.get(), 1.0F, 1.0F);
			}
		}
	}
	
	public boolean isWeak() {
		return getHungerLevel() < 15;
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (getSeenTime() == 0 || world.getLight(getPosition()) == 0 || world.isAirBlock(getPosition().down())) {
			setNoAI(false);
		}
		
		if (ticksExisted % 500 == 0 && getAttackTarget() == null && !isQuantumLocked() && getSeenTime() == 0) {
			setPose(PoseManager.POSE_HIDING_FACE.toString());
		}
		replaceBlocks(getBoundingBox().grow(WAConfig.CONFIG.blockBreakRange.get()));
	}
	
	@Override
	public void onKillEntity(LivingEntity entityLivingIn) {
		super.onKillEntity(entityLivingIn);
		
		if (entityLivingIn instanceof PlayerEntity) {
			playSound(WAObjects.Sounds.ANGEL_NECK_SNAP.get(), 1, 1);
		}
	}
	
	@Override
	protected PathNavigator createNavigator(World worldIn) {
		GroundPathNavigator navigator = new GroundPathNavigator(this, worldIn);
		navigator.setCanSwim(false);
		navigator.setBreakDoors(true);
		navigator.setAvoidSun(false);
		return navigator;
	}
	
	private void replaceBlocks(AxisAlignedBB box) {
		if (world.isRemote || !WAConfig.CONFIG.blockBreaking.get() || ticksExisted % 100 != 0 || isQuantumLocked())
			return;
		
		if (world.getLight(getPosition()) == 0) {
			return;
		}

		for (Iterator<BlockPos> iterator = BlockPos.getAllInBox(new BlockPos(box.minX, box.minY, box.minZ), new BlockPos(box.maxX, box.maxY, box.maxZ)).iterator(); iterator.hasNext(); ) {
			BlockPos pos = iterator.next();
			BlockState blockState = world.getBlockState(pos);
			if (world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) && getHealth() > 5) {
				
				if (!canBreak(blockState) || blockState.getBlock() == Blocks.LAVA || blockState.getBlock() == Blocks.AIR) {
					continue;
				}
				
				if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE) {
					AngelUtils.playBreakEvent(this, pos, Blocks.AIR);
					return;
				}
				
				if (blockState.getBlock() instanceof NetherPortalBlock || blockState.getBlock() instanceof EndPortalBlock) {
					if (getHealth() < getMaxHealth()) {
						heal(1.5F);
						world.removeBlock(pos, true);
					}
				} else
					continue;
				
				return;
			}
		}
	}
	
	private boolean canBreak(BlockState blockState) {
		for (String regName : WAConfig.CONFIG.disAllowedBlocks.get()) {
			if (blockState.getBlock().getRegistryName().toString().equals(regName)) {
				return false;
			}
		}
		return true;
	}
	
	private void teleportInteraction(PlayerEntity player) {
		if (world.isRemote) return;
		
		AngelUtils.EnumTeleportType type = AngelUtils.EnumTeleportType.valueOf(WAConfig.CONFIG.teleportType.get());
		
		switch (type) {
			case DONT:
				break;
			case STRUCTURES:
				Teleporter.handleStructures(player);
				break;
			case RANDOM_PLACE:
				if (rand.nextBoolean()) {
					double x = player.posX + rand.nextInt(WAConfig.CONFIG.teleportRange.get());
					double z = player.posZ + rand.nextInt(WAConfig.CONFIG.teleportRange.get());
 					TeleporterNew.teleportPlayer(player, player.dimension,x, yCoordSanity(player.world, new BlockPos(x,0,z)).getY(), z);
				} else {
					Teleporter.handleStructures(player);
				}
				break;
		}
	}
	
	/*
	 * Getters and Setters
	 */

	public String getAngelPose() {
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
	
	public int getAngelType() {
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


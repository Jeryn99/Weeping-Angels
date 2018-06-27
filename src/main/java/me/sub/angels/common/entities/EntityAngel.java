package me.sub.angels.common.entities;

import me.sub.angels.client.models.poses.AngelPoses;
import me.sub.angels.common.WAObjects;
import me.sub.angels.main.WAConstants;
import me.sub.angels.main.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityAngel extends EntityMob {

	private SoundEvent[] seenSounds = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5};
	
	private long soundTime = 0L;
	
	private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.createKey(EntityAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<String> POSE = EntityDataManager.createKey(EntityAngel.class, DataSerializers.STRING);
	
	public EntityAngel(World world) {
		super(world);
		
		tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 1.5F, 80));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 2.0F, false));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(7, new EntityAIBreakDoor(this));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, new EntityAITempt(this, 3.5D, Item.getItemFromBlock(Blocks.TORCH), false));
		tasks.addTask(2, new EntityAITempt(this, 3.5D, Item.getItemFromBlock(Blocks.REDSTONE_TORCH), false));
		tasks.addTask(8, new EntityAIMoveThroughVillage(this, 1.0D, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

		experienceValue = WAConfig.angels.xpGained;
	}
	
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
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
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (isChild() && rand.nextInt(3) == 2) {
			return WAObjects.Sounds.LAUGHING_CHILD;
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
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(WAConfig.angels.speed);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.angels.damage);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {
		
		if (!WAConfig.angels.justTeleport) {
			if (this.getHealth() > 5) {
				entity.attackEntityFrom(WAObjects.ANGEL, 4.0F);
			} else {
				entity.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
			}
		}
		return false;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_SEEN, false);
		getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
		getDataManager().register(TIME_VIEWED, 0);
		getDataManager().register(TYPE, getRandomType());
		getDataManager().register(POSE, AngelPoses.ANGRY.toString());
	}
	
	private int getRandomType() {
		if (rand.nextBoolean()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public void travel(float strafe, float vertical, float forward) {
		if (!isSeen()) {
			super.travel(strafe, vertical, forward);
		}
	}
	
	@Override
	public void move(MoverType type, double x, double y, double z) {
		if (!isSeen()) {
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

	public String getPoseName() {
		return getDataManager().get(POSE);
	}
	
	public boolean isChild() {
		return getDataManager().get(IS_CHILD);
	}
	
	public void setPose(String newPose) {
		getDataManager().set(POSE, newPose);
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
		compound.setBoolean(WAConstants.IS_SEEN, isSeen());
		compound.setInteger(WAConstants.TIME_SEEN, getSeenTime());
		compound.setString(WAConstants.POSE, getPoseName());
		compound.setInteger(WAConstants.TYPE, getType());
		compound.setBoolean(WAConstants.ANGEL_CHILD, isChild());
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey(WAConstants.IS_SEEN)) setSeen(compound.getBoolean(WAConstants.IS_SEEN));
		
		if (compound.hasKey(WAConstants.TIME_SEEN)) setSeenTime(compound.getInteger(WAConstants.TIME_SEEN));
		
		if (compound.hasKey(WAConstants.POSE)) setPose(compound.getString(WAConstants.POSE));
		
		if (compound.hasKey(WAConstants.TYPE)) setType(compound.getInteger(WAConstants.TYPE));
		
		if (compound.hasKey(WAConstants.ANGEL_CHILD)) setChild(compound.getBoolean(WAConstants.ANGEL_CHILD));
	}

    public boolean canTeleportEntity(Entity entity) {
        return WAConfig.angels.teleportEntities && !isChild() && !(entity instanceof EntityHanging) && !(entity instanceof EntityThrowable) && !entity.isRidingOrBeingRiddenBy(entity) && !entity.isBeingRidden() && WAConfig.angels.teleportType != WAObjects.TeleportType.PLAYER_ONLY;
	}
	
	private int isDimensionAllowed(int dimID) {
		for (int dim : WAConfig.angels.notAllowedDimensions) {
			if (dim == dimID) {
				return this.dimension;
			}
		}
		return dimID;
	}
	
	private void correctTeleportPos(Entity entity, double x, double y, double z, int dim, EntityAngel angel) {
		BlockPos p = new BlockPos(x, y, z);
		
		if (world.isAirBlock(p)) {
			if (world.getBlockState(p.add(0, -1, 0)).getMaterial().isSolid()) {
				AngelUtils.teleportDimEntity(entity, new BlockPos(x, y, z), dim, angel);
			} else {
				for (int i = 1; i < 255; i++) {
					if (world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
						AngelUtils.teleportDimEntity(entity, new BlockPos(x, i, z), dim, angel);
					}
				}
			}
		} else {
			for (int i = 1; i < 255; i++) {
				if (world.isAirBlock(p.add(0, -p.getY() + i, 0)) && world.getBlockState(p.add(0, -p.getY() + i - 1, 0)).getMaterial().isSolid()) {
					AngelUtils.teleportDimEntity(entity, new BlockPos(x, i, z), dim, angel);
				}
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
	protected void collideWithEntity(Entity entity) {
		entity.applyEntityCollision(this);

		AngelUtils.handleKeyThief(entity, this);

		if (world.isRemote) return;

        if (canTeleportEntity(entity) && rand.nextInt(100) == 50 || canTeleportEntity(entity) && WAConfig.angels.justTeleport) {
			int dimID;

			if (WAConfig.angels.angelDimTeleport) {
				dimID = world.rand.nextInt(DimensionManager.getStaticDimensionIDs().length);
			} else {
				dimID = dimension;
			}

			if (DimensionManager.isDimensionRegistered(isDimensionAllowed(dimID))) {
				EntityAnomaly a = new EntityAnomaly(world);
				a.copyLocationAndAnglesFrom(entity);
				a.setEntityEyeHeight(entity.getEyeHeight());
				world.spawnEntity(a);
				int x = entity.getPosition().getX() + rand.nextInt(WAConfig.angels.teleportRange);
				int z = entity.getPosition().getZ() + rand.nextInt(WAConfig.angels.teleportRange);
				int y = world.getHeight(x, z);
				playSound(WAObjects.Sounds.ANGEL_TELEPORT, 1.0F, 1.0F);
				heal(4.0F);
				correctTeleportPos(entity, x, y, z, dimID, this);
			}
		}
	}
	
	@SubscribeEvent
	public static void cancelDamage(LivingAttackEvent e) {
		Entity source = e.getSource().getTrueSource();
		if (source instanceof EntityLivingBase) {
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public void turn(float yaw, float pitch) {
		if (!isSeen()) {
			super.turn(yaw, pitch);
		}
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block block) {
		if (prevPosX != posX && prevPosZ != posZ) {
			if (!isChild()) {
				if (WAConfig.angels.playScrapSounds) {
					playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
				}
			} else {
				playSound(WAObjects.Sounds.CHILD_RUN, 1.0F, 1.0F);
			}
		}
	}
	
	private void replaceBlocks(AxisAlignedBB box) {
		
		boolean stop = false;
		
		if (world.isRemote || ticksExisted % 100 != 0 || !WAConfig.angels.blockBreaking) return;
		
		for (int x = (int) box.minX; x <= box.maxX && !stop; x++) {
			for (int y = (int) box.minY; y <= box.maxY && !stop; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ && !stop; z++) {
					
					BlockPos pos = new BlockPos(x, y, z);
					IBlockState blockState = world.getBlockState(pos);
					// Breaking Start
					if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {
						
						if (!canBreak(blockState)) {
							return;
						}
						
						if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE || blockState.getLightValue(world, pos) >= 7) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
							InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getX(), new ItemStack(Item.getItemFromBlock(blockState.getBlock())));
							stop = true;
						}
						
						if (blockState.getBlock() == Blocks.LIT_PUMPKIN) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
							world.setBlockState(pos, Blocks.PUMPKIN.getDefaultState());
							stop = true;
						}
						
						if (blockState.getBlock() == Blocks.LIT_REDSTONE_LAMP) {
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							world.setBlockToAir(pos);
							playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
							world.setBlockState(pos, Blocks.REDSTONE_LAMP.getDefaultState());
							stop = true;
						}
						
						if (blockState.getBlock() instanceof BlockPortal || blockState.getBlock() instanceof BlockEndPortal) {
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
	
	private boolean canBreak(IBlockState blockState) {
		for (String regName : WAConfig.angels.disAllowedBlocks) {
			if (blockState.getBlock().getRegistryName().toString().equals(regName)) {
				return false;
			}
		}
		return true;
	}
	
	public SoundEvent getSeenSound() {
		long currentTime = System.currentTimeMillis();
		if (currentTime - soundTime >= 1000) {
			soundTime = currentTime;
			return seenSounds[rand.nextInt(seenSounds.length)];
		}
		
		return null;
	}
	
	private boolean getIsInView() {
		for (EntityPlayer player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
			if (AngelUtils.isInSight(player, this) && !player.isSpectator()) {
				setSeenTime(getSeenTime() + 1);
				if (getSeenTime() == 1 && !AngelUtils.isDarkForPlayer(this, player) && !player.isPotionActive(MobEffects.BLINDNESS)) {
					{
						setPose(getBestPoseForSituation(this, player).toString());
						playSound(getSeenSound(), 1.0F, 1.0F);
					}
				}
				return true;
			}
		}
		setSeenTime(0);
		return false;
	}

	@Override
	public void onUpdate() {

		super.onUpdate();

		if (isSeen()) {
			if (isChild() && getAttackTarget() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) getAttackTarget();
				if (getDistance(getAttackTarget()) <= 1.5F) {
					AngelUtils.blowOutTorch(player);
				}
			}
			// Light block breaking
			replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
		}

		if (!world.isRemote) {
			setSeen(getIsInView());
		}

	}

	public AngelPoses getBestPoseForSituation(EntityAngel angel, EntityLivingBase player) {

		if (angel.getDistance(player) < 1.0F) {
			return AngelPoses.ANGRY;
		}
		if (angel.getDistance(player) < 5.0F) {
			return AngelPoses.ANGRY_TWO;
		}
		if (angel.getDistance(player) < 10.0F) {
			return AngelPoses.SHY;
		}
		if (angel.getDistance(player) < 15.0F) {
			return AngelPoses.IDLE;
		}
		if (angel.getDistance(player) < 25.0F) {
			return AngelPoses.HIDING_FACE;
		}

		return AngelPoses.HIDING_FACE;
	}
}

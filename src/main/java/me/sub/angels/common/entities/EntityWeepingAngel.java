package me.sub.angels.common.entities;

import me.sub.angels.api.IBreakException;
import me.sub.angels.api.ICanTeleport;
import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import me.sub.angels.utils.WATeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class EntityWeepingAngel extends EntityQuantumLockBase {
	
	private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<String> CURRENT_POSE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.STRING);
	private static final DataParameter<Integer> HUNGER_LEVEL = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);

    private SoundEvent[] SEEN_SOUNDS = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5, WAObjects.Sounds.ANGEL_SEEN_6};
	private SoundEvent[] CHILD_SOUNDS = new SoundEvent[]{SoundEvents.ENTITY_VEX_AMBIENT, WAObjects.Sounds.LAUGHING_CHILD};
	
	public EntityWeepingAngel(World world) {
		super(world);

		tasks.addTask(0, new EntityAIBreakDoor(this));

		experienceValue = WAConfig.angels.xpGained;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
		getDataManager().register(TYPE, getRandomType());
		getDataManager().register(CURRENT_POSE, PoseManager.randomPose(PoseManager.AngelPoses.class).toString());
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
		if (isChild() && ticksExisted % AngelUtils.secondsToTicks(2) == 0) {
			return CHILD_SOUNDS[rand.nextInt(CHILD_SOUNDS.length)];
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
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.angels.damage);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(9999999.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {

		if (WAConfig.angels.torchBlowOut && isChild()) {
			if (entity instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entity;
				AngelUtils.removeLightFromHand(player, this);
			}
		}

		if (!WAConfig.angels.justTeleport || isWeak()) {

			if (getHealth() > 5) {
				entity.attackEntityFrom(WAObjects.ANGEL, 4.0F);
			} else {
				entity.attackEntityFrom(WAObjects.ANGEL_NECK_SNAP, 4.0F);
				heal(2.0F);
			}
		} else {
			if (entity instanceof EntityPlayer && !isChild()) {
				teleportPlayer((EntityPlayer) entity);
			}
		}
		return false;
	}

	private int getRandomType() {
		if (rand.nextBoolean()) {
			return 1;
		}
		return 0;
	}

	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		dropItem(Item.getItemFromBlock(Blocks.STONE), rand.nextInt(3));
		entityDropItem(getHeldItemMainhand(), getHeldItemMainhand().getCount());
		entityDropItem(getHeldItemOffhand(), getHeldItemOffhand().getCount());
	}

	public String getPose() {
		return getDataManager().get(CURRENT_POSE);
	}

	public void setPose(String newPose) {
		getDataManager().set(CURRENT_POSE, newPose);
	}

	public boolean isChild() {
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
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setString(WAConstants.POSE, getPose());
		compound.setInteger(WAConstants.TYPE, getType());
		compound.setBoolean(WAConstants.ANGEL_CHILD, isChild());
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
				this.attackEntityFrom(DamageSource.STARVE, 2);
			}
		}
	}

	@Override
	public void invokeSeen(EntityPlayer player) {
		if (player instanceof EntityPlayerMP && getSeenTime() == 1 && getPrevPos().toLong() != getPosition().toLong() && WAConfig.angels.playSeenSounds && !player.isCreative()) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketSoundEffect(getSeenSound(), SoundCategory.HOSTILE, player.posX, player.posY, player.posZ, 1.0F, 1.0F));
			setPrevPos(getPosition());
			setPose(PoseManager.randomPose(PoseManager.AngelPoses.class).toString());
		}
	}

	@Override
	protected boolean isMovementBlocked() {
		return true;
	}

	@Override
	public boolean isAngel() {
		return true;
	}

	@Override
	public void teleportTowards(EntityLivingBase entity) {
		super.teleportTowards(entity);
		if (isQuantumLocked()) return;
		if (WAConfig.angels.playScrapSounds && !isChild()) {
			playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
		}

		if (isChild()) {
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

		if (ticksExisted % 500 == 0 && getAttackTarget() == null && !isQuantumLocked() && getSeenTime() == 0) {
			setPose(PoseManager.AngelPoses.HIDING_FACE.toString());
		}

		replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
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
		if (world.isRemote || !WAConfig.angels.blockBreaking || ticksExisted % 100 != 0 || !isQuantumLocked()) return;
		for (int x = (int) box.minX; x <= box.maxX; x++) {
			for (int y = (int) box.minY; y <= box.maxY; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ; z++) {
					
					BlockPos pos = new BlockPos(x, y, z);
					IBlockState blockState = world.getBlockState(pos);

					if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {

						if (!canBreak(blockState)) {
							continue;
						}

						if (blockState.getBlock() instanceof IBreakException) {
							IBreakException block = (IBreakException) blockState.getBlock();
							if (block.shouldBreak()) {
								playBreakEvent(pos, Blocks.AIR);
							} else {
								continue;
							}
						}

						if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE || blockState.getLightValue(world, pos) >= 7) {
							playBreakEvent(pos, Blocks.AIR);
							return;
						}

						if (blockState.getBlock() == Blocks.LIT_PUMPKIN) {
							playBreakEvent(pos, Blocks.PUMPKIN);
							return;
						}

						if (blockState.getBlock() == Blocks.LIT_REDSTONE_LAMP) {
							playBreakEvent(pos, Blocks.REDSTONE_LAMP);
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

	private void playBreakEvent(BlockPos pos, Block block) {

		if (!world.isRemote) {
			playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(world.getBlockState(pos).getBlock()));
			world.setBlockState(pos, block.getDefaultState());

			for (EntityPlayer player : world.playerEntities) {
				if (player instanceof EntityPlayerMP) {
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					if (playerMP.getDistance(this) < 45 && !playerMP.isCreative()) {
						playerMP.connection.sendPacket(new SPacketParticles(EnumParticleTypes.CRIT_MAGIC, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 1.0F, 11));
					}
				}
			}
		}
	}

	public SoundEvent getSeenSound() {
		return SEEN_SOUNDS[rand.nextInt(SEEN_SOUNDS.length)];
	}
	
	private void teleportPlayer(EntityPlayer player) {
		if (world.isRemote) return;
		
		int dim;
		int range = WAConfig.angels.teleportRange;
		EntityAnomaly anomaly = new EntityAnomaly(world);
		anomaly.setPositionAndUpdate(player.posX, player.posY, player.posZ);
		world.spawnEntity(anomaly);

		if (WAConfig.angels.angelDimTeleport) {
			dim = decideDimension();
		} else {
			dim = dimension;
		}
		WorldServer ws = (WorldServer) world;
		ws.getMinecraftServer().getWorld(dim);
		int x = rand.nextInt(range);
		int z = rand.nextInt(range);
		WATeleporter.move(player, player.getPosition().add(x, ws.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY() - player.posY, z), dim, this);
	}

	private int decideDimension() {
		Integer[] ids = DimensionManager.getStaticDimensionIDs();
		Integer tempId = ids[rand.nextInt(ids.length)];
		for (int id : WAConfig.angels.notAllowedDimensions) {
			if (id == tempId) {
				return 0;
			}
		}

		if (FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(tempId).provider instanceof ICanTeleport) {
			ICanTeleport provider = (ICanTeleport) world.provider;
			if (provider.shouldTeleport()) {
				return tempId;
			} else {
				return 0;
			}
		}

		return tempId;
	}
}

package me.sub.angels.common.entities;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.misc.WAConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.class_1361;
import net.minecraft.class_1369;
import net.minecraft.class_1370;
import net.minecraft.class_1394;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class EntityWeepingAngel extends EntityQuantumLockBase
{

	private static final TrackedData<Integer> TYPE = DataTracker.registerData(EntityWeepingAngel.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Boolean> IS_CHILD = DataTracker.registerData(EntityWeepingAngel.class, TrackedDataHandlerRegistry.BOOLEAN);
	private static final TrackedData<String> CURRENT_POSE = DataTracker.registerData(EntityWeepingAngel.class, TrackedDataHandlerRegistry.STRING);
	//    private SoundEvent[] seenSounds = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5};
	//    private long soundTime = 0L;

	public EntityWeepingAngel(World world)
	{
		super(WAObjects.EntityEntries.WEEPING_ANGEL, world);

		goalSelector.add(1, new MeleeAttackGoal(this, 2.0f, false));
		goalSelector.add(2, new TemptGoal(this, 3.5, Ingredient.ofItems(Item.getItemFromBlock(Blocks.TORCH)), false));
		goalSelector.add(2, new TemptGoal(this, 3.5, Ingredient.ofItems(Item.getItemFromBlock(Blocks.REDSTONE_TORCH)), false));
		goalSelector.add(2, new SwimGoal(this));
		goalSelector.add(3, new class_1369(this, 1.5F, 80));
		goalSelector.add(4, new class_1370(this, 1.0));
		goalSelector.add(5, new class_1394(this, 1.0D));
		goalSelector.add(6, new class_1361(this, PlayerEntity.class, 8.0F));
		goalSelector.add(7, new BreakDoorGoal(this));
		goalSelector.add(8, new MoveThroughVillageGoal(this, 1.0, false));
		targetSelector.add(2, new FollowTargetGoal<>(this, PlayerEntity.class, true));
		this.experiencePoints = 25;
//		experienceValue = WAConfig.angels.xpGained;
	}

	@Override
	protected void initDataTracker()
	{
		super.initDataTracker();
		getDataTracker().startTracking(IS_CHILD, random.nextInt(10) == 4);
		getDataTracker().startTracking(TYPE, getRandomType());
		getDataTracker().startTracking(CURRENT_POSE, PoseManager.AngelPoses.ANGRY.toString());
	}

	//TODO
	//    @Nullable
	//    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
	//        playSound(WAObjects.Sounds.ANGEL_AMBIENT, 0.5F, 1.0F);
	//        return super.onInitialSpawn(difficulty, livingdata);
	//    }

	//TODO sounds
	//    @Override
	//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	//        return SoundEvents.BLOCK_STONE_HIT;
	//    }

	//    @Override
	//    protected SoundEvent getDeathSound() {
	//        return WAObjects.Sounds.ANGEL_DEATH;
	//    }

	//    @Nullable
	//    @Override
	//    protected SoundEvent getAmbientSound() {
	//        if (isChild() && rand.nextInt(3) == 2) {
	//            return WAObjects.Sounds.LAUGHING_CHILD;
	//        }
	//        return null;
	//    }

	@Override
	public float getEyeHeight()
	{
		return this.isChild() ? this.getHeight() : 1.3F;
	}

	@Override protected void initAttributes()
	{
		super.initAttributes();
		getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(135.0D);
		getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
		//TODO config
		//        getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(WAConfig.angels.speed);
		//        getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(WAConfig.angels.damage);
		getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(50.0D);
	}



	@Override
	public boolean method_6121(Entity entity)
	{
		//        if (!WAConfig.angels.justTeleport) {
		if (this.getHealth() > 5)
		{
			entity.damage(WAObjects.ANGEL, 4.0f);
		}
		else
		{
			entity.damage(WAObjects.ANGEL_NECK_SNAP, 4.0F);
			heal(2.0F);
		}
		//        } else
		//            {
		//            if (entity instanceof EntityPlayer) {
		//                teleportPlayer((EntityPlayer) entity);
		//            }
		//        }
		return false;
	}

	private int getRandomType()
	{
		if (random.nextBoolean())
		{
			return 1;
		}
		return 0;
	}

	//TODO find name
//	@Override
//	public void travel(float strafe, float vertical, float forward)
//	{
//		if (!isSeen() || !onGround)
//		{
//			super.travel(strafe, vertical, forward);
//		}
//	}

	@Override public void move(MovementType movementType_1, double double_1, double double_2, double double_3)
	{
		if (!isSeen() || !onGround)
		{
			super.move(movementType_1, double_1, double_2, double_3);
		}
	}


//TODO find name
//	@Override
//	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
//	{
//		dropItem(Item.getItemFromBlock(Blocks.STONE), rand.nextInt(3));
//		entityDropItem(getHeldItemMainhand(), getHeldItemMainhand().getCount());
//		entityDropItem(getHeldItemOffhand(), getHeldItemOffhand().getCount());
//	}

	@Override public void doJump(boolean boolean_1)
	{
		if(!isSeen())
		super.doJump(boolean_1);
	}

	public String getPose()
	{
		return getDataTracker().get(CURRENT_POSE);
	}

	public void setPose(String newPose)
	{
		getDataTracker().set(CURRENT_POSE, newPose);
	}

	public boolean isChild()
	{
		return getDataTracker().get(IS_CHILD);
	}

	public void setChild(boolean child)
	{
		getDataTracker().set(IS_CHILD, child);
	}

	public int getAngelType()
	{
		return getDataTracker().get(TYPE);
	}

	public void setAngelType(int angelType)
	{
		getDataTracker().set(TYPE, angelType);
	}

	@Override public void writeCustomDataToTag(CompoundTag compound)
	{
		super.writeCustomDataToTag(compound);

		compound.putString(WAConstants.POSE, getPose());
		compound.putInt(WAConstants.TYPE, getAngelType());
		compound.putBoolean(WAConstants.ANGEL_CHILD, isChild());
	}

	@Override public void readCustomDataFromTag(CompoundTag compound)
	{
		super.readCustomDataFromTag(compound);

		if (compound.containsKey(WAConstants.POSE))
			setPose(compound.getString(WAConstants.POSE));

		if (compound.containsKey(WAConstants.TYPE))
			setAngelType(compound.getInt(WAConstants.TYPE));

		if (compound.containsKey(WAConstants.ANGEL_CHILD))
			setChild(compound.getBoolean(WAConstants.ANGEL_CHILD));
	}

	//TODO find name
//	@Override
//	protected void collideWithEntity(Entity entity)
//	{
//		entity.applyEntityCollision(this);
//	}

	/**
	 * Dead and sleeping entities cannot move
	 */
	//TODO
//	@Override
//	protected boolean isMovementBlocked()
//	{
//		return getHealth() <= 0.0F || isSeen();
//	}

	/**
	 * Sets the rotation of the entity.
	 */
	@Override
	protected void setRotation(float yaw, float pitch)
	{
		if (!isSeen())
		{
			super.setRotation(yaw, pitch);
		}
	}

	@Override
	public void update()
	{
		super.update();
		// TODO figure out why the hell it's being so weird
		//TODO config
//		replaceBlocks(getBoundingBox().expand(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
		replaceBlocks(getBoundingBox().expand(25));

	}


	@Override
	@Environment(EnvType.CLIENT)
	public void method_5872(double yaw, double pitch)
	{
		if (!isSeen())
		{
			super.method_5872(yaw, pitch);
		}
	}

	//TODO sounds
//	@Override
//	protected void playStepSound(BlockPos pos, Block block)
//	{
//		if (prevPosX != posX && prevPosZ != posZ)
//		{
//
//			if (WAConfig.angels.playScrapSounds && !isChild())
//			{
//				playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
//			}
//
//			if (isChild())
//			{
//				if (world.rand.nextInt(5) == 5)
//				{
//					playSound(WAObjects.Sounds.CHILD_RUN, 1.0F, 1.0F);
//				}
//			}
//
//		}
//	}

	private void replaceBlocks(BoundingBox box)
	{
		//TODO config
//		if (world.isClient || !WAConfig.angels.blockBreaking || ticksExisted % 100 != 0)
		if (world.isClient || age % 100 != 0)
			return;
		for (int x = (int) box.minX; x <= box.maxX; x++)
		{
			for (int y = (int) box.minY; y <= box.maxY; y++)
			{
				for (int z = (int) box.minZ; z <= box.maxZ; z++)
				{

					BlockPos pos = new BlockPos(x, y, z);
					BlockState blockState = world.getBlockState(pos);

					if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5)
					{

						if (!canBreak(blockState))
						{
							continue;
						}

						if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE)
						// || blockState.getLightSubtracted(world, pos) >= 7 TODO
						{
							playBreakEvent(pos, Blocks.AIR);
						}

						if (blockState.getBlock() == Blocks.JACK_O_LANTERN)
						{
							playBreakEvent(pos, Blocks.PUMPKIN);
						}

						if (blockState.getBlock() == Blocks.REDSTONE_LAMP)
						{
							playBreakEvent(pos, Blocks.REDSTONE_LAMP);
						}

						if (blockState.getBlock() instanceof PortalBlock || blockState.getBlock() instanceof EndPortalBlock)
						{
							if (getHealth() < getHealthMaximum())
							{
								heal(1.5F);
								world.setBlockState(pos, Blocks.AIR.getDefaultState());
							}
						}
						else
							continue;

						return;
					}
				}
			}
		}
	}

	private boolean canBreak(BlockState blockState)
	{
		//TODO config
//		for (String regName : WAConfig.angels.disAllowedBlocks)
//		{
//			if (blockState.getBlock().getRegistryName().toString().equals(regName))
//			{
//				return false;
//			}
//		}
		return true;
	}

	private void playBreakEvent(BlockPos pos, Block block)
	{

		if (!world.isClient)
		{
			//TODO sounds
//			playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
			//TODO spawn item
//			InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(world.getBlockState(pos).getBlock()));
			world.setBlockState(pos, block.getDefaultState());

			//TODO Packets?
			//world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
		}
	}

//	public SoundEvent getSeenSound()
//	{
//		long currentTime = System.currentTimeMillis();
//		if (currentTime - soundTime >= 1000)
//		{
//			soundTime = currentTime;
//			return seenSounds[rand.nextInt(seenSounds.length)];
//		}
//
//		return null;
//	}

	private void teleportPlayer(PlayerEntity player)
	{
		if (world.isClient)
			return;

		int dim;
		//TODO config
//		int range = WAConfig.angels.teleportRange;
		int range = 450;
		EntityAnomaly anomaly = new EntityAnomaly(world);
		anomaly.setPosition(player.x, player.y, player.z);
		world.spawnEntity(anomaly);

//		if (WAConfig.angels.angelDimTeleport)
//		{
			dim = decideDimension();
//		}
//		else
//		{
//			dim = dimension;
//		}
		ServerWorld ws = (ServerWorld) world;
		ws.getServer().getWorld(DimensionType.byRawId(dim));
		int x = random.nextInt(range);
		int z = random.nextInt(range);
		//TODO teleport
//		WATeleporter.teleportDimEntity(player, player.getPosition().add(x, ws.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY() - player.posY, z), dim, this);
	}

	private int decideDimension()
	{
		//TODO
//		Integer[] ids = DimensionType.getAll();
//		Integer tempId = ids[rand.nextInt(ids.length)];
//		for (int id : WAConfig.angels.notAllowedDimensions)
//		{
//			if (id == tempId)
//			{
//				return 0;
//			}
//		}
//
//		if (FMLCommonHandler.instance().getMinecraftServerInstance().getWorld(tempId).provider instanceof ICanTeleport)
//		{
//			ICanTeleport provider = (ICanTeleport) world.provider;
//			if (provider.shouldTeleport())
//			{
//				return tempId;
//			}
//			else
//			{
//				return 0;
//			}
//		}

		return 0;
	}
}

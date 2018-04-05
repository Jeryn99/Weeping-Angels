package com.github.reallysub.angels.common.entities;

import javax.annotation.Nullable;

import com.github.reallysub.angels.common.WAObjects;
import com.github.reallysub.angels.events.EventTeleport;
import com.github.reallysub.angels.main.AngelUtils;
import com.github.reallysub.angels.main.config.Config;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EntityAngel extends EntityMob // implements IEntityAdditionalSpawnData
{
	
	private static DataParameter<Boolean> isAngry = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Boolean> isSeen = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<Integer> timeViewed = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<Integer> type = EntityDataManager.<Integer>createKey(EntityAngel.class, DataSerializers.VARINT);
	private static DataParameter<BlockPos> blockBreakPos = EntityDataManager.<BlockPos>createKey(EntityAngel.class, DataSerializers.BLOCK_POS);
	private static DataParameter<Boolean> isChild = EntityDataManager.<Boolean>createKey(EntityAngel.class, DataSerializers.BOOLEAN);
	private static DataParameter<ItemStack> pickAxe = EntityDataManager.<ItemStack>createKey(EntityAngel.class, DataSerializers.ITEM_STACK);
	// private ItemStack stack;
	
	public EntityAngel(World world) {
		super(world);
		tasks.addTask(3, new EntityAIMoveTowardsTarget(this, 3.0F, 80));
		tasks.addTask(2, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 3.0F, false));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(7, new EntityAIBreakDoor(this));
		tasks.addTask(2, new EntityAITempt(this, 9.5D, Item.getItemFromBlock(Blocks.TORCH), false));
		tasks.addTask(8, new EntityAIMoveThroughVillage(this, 1.0D, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		experienceValue = 25;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_STONE_HIT;
	}
	
	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (isChild() && rand.nextInt(3) == 2) {
			return WAObjects.laughing_child;
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
		
		// if(entity instanceof EntityPlayerMP)
		// {
		// EntityPlayerMP player = (EntityPlayerMP) entity;
		//// ItemStack mainStack = player.getHeldItemMainhand();
		// ItemStack angelStack = getItemStack();
		// if(mainStack.getItem() instanceof ItemPickaxe && angelStack.isEmpty())
		// {
		// angelStack = mainStack;
		// mainStack.setCount(0);
		// setItemStack(angelStack);
		// }
		// System.out.println(getItemStack());
		// }
		
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
		getDataManager().register(isAngry, false);
		getDataManager().register(isChild, randomChild());
		getDataManager().register(timeViewed, 0);
		getDataManager().register(type, randomType());
		getDataManager().register(blockBreakPos, BlockPos.ORIGIN);
		getDataManager().register(pickAxe, new ItemStack(Items.AIR));
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
		
		if (!getItemStack().isEmpty()) {
			entityDropItem(getItemStack(), 0);
		}
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
	
	public ItemStack getItemStack() {
		return getDataManager().get(pickAxe);
	}
	
	public void setItemStack(ItemStack item) {
		getDataManager().set(pickAxe, item);
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
	 * @return Returns whether the angel is angry or not
	 */
	public boolean isAngry() {
		return getDataManager().get(isAngry);
	}
	
	public boolean isChild() {
		return getDataManager().get(isChild);
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
		compound.setBoolean("isAngry", isAngry());
		compound.setInteger("type", getType());
		compound.setBoolean("angelChild", isChild());
		
		if (!getItemStack().isEmpty()) {
			NBTTagCompound item = new NBTTagCompound();
			getItemStack().writeToNBT(compound);
			compound.setTag("itemStack", item);
		}
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
		
		if (compound.hasKey("type")) setType(compound.getInteger("type"));
		
		if (compound.hasKey("angelChild")) setChild(compound.getBoolean("angelChild"));
		
		// if(compound.hasKey("itemStack")) {
		// stack = new ItemStack(compound.getCompoundTag("itemStack"));
		// setItemStack(stack);
		// }
	}
	
	/**
	 * When a entity collides with this entity
	 */
	@Override
	protected void collideWithEntity(Entity entity) {
		entity.applyEntityCollision(this);
		if (Config.teleportEntities && !isChild() && !(entity instanceof EntityAngel) && rand.nextInt(100) == 50 && !(entity instanceof EntityPainting) && !(entity instanceof EntityItemFrame) && !(entity instanceof EntityItem) && !(entity instanceof EntityArrow) && !(entity instanceof EntityThrowable)) {
			int x = entity.getPosition().getX() + rand.nextInt(Config.teleportRange);
			int z = entity.getPosition().getZ() + rand.nextInt(Config.teleportRange);
			int y = world.getHeight(x, z);
			AngelUtils.teleportEntity(world, entity, x, y, z);
			heal(4.0F);
			
			entity.playSound(WAObjects.angel_teleport, 1.0F, 1.0F);
			
			if (entity instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) entity;
				if (rand.nextInt(2) == 2) {
					player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 600, 3));
					player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 600, 1));
					player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 600, 3));
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 600, 3));
				}
				
				MinecraftForge.EVENT_BUS.post(new EventTeleport(player));
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
			setAngry(!isAngry());
		}
		
		if (!world.isRemote) if (isSeen()) {
			setSeenTime(getSeenTime() + 1);
			if (getSeenTime() > 15) setSeen(false);
		} else {
			setSeenTime(0);
		}
		
		if (isSeen()) {
			if (world.getGameRules().getBoolean("mobGriefing")) {
				replaceBlocks(getEntityBoundingBox().grow(25, 25, 25));
			}
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
				playSound(WAObjects.stone_scrap, 0.5F, 1.0F);
			} else {
				if (world.rand.nextInt(50) == 5) {
					playSound(WAObjects.child_run, 1.0F, 1.0F);
				}
			}
		}
	}
	
	private int timer = 0;
	
	private void replaceBlocks(AxisAlignedBB box) {
		for (int x = (int) box.minX; x <= box.maxX; x++) {
			for (int y = (int) box.minY; y <= box.maxY; y++) {
				for (int z = (int) box.minZ; z <= box.maxZ; z++) {
					BlockPos pos = new BlockPos(x, y, z);
					Block block = getEntityWorld().getBlockState(pos).getBlock();
					
					if (block == Blocks.TORCH || block == Blocks.REDSTONE_TORCH || block == Blocks.GLOWSTONE) {
						if (shouldBreak()) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, 1, 0)));
						}
					}
					
					if (block == Blocks.LIT_PUMPKIN) {
						setBreakBlockPos(pos);
						if (shouldBreak()) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							getEntityWorld().setBlockState(pos, Blocks.PUMPKIN.getDefaultState());
						}
						return;
					}
					
					if (block == Blocks.LIT_REDSTONE_LAMP) {
						
						if (shouldBreak()) {
							setBreakBlockPos(pos);
							if (world.isRemote) {
								world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
							}
							getEntityWorld().setBlockToAir(pos);
							playSound(WAObjects.light_break, 1.0F, 1.0F);
							setBreakBlockPos(BlockPos.ORIGIN);
							getEntityWorld().setBlockState(pos, Blocks.REDSTONE_LAMP.getDefaultState());
						}
						return;
					}
					
					if (!WAObjects.IGNORED_BLOCKS.contains(block.getRegistryName()) && block.getLightValue(block.getDefaultState()) >= 7) {
						playSound(WAObjects.light_break, 1.0F, 1.0F);
						world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(block, 1, 0)));
					}
				}
			}
		}
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
}

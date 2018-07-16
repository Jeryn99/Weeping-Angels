package me.sub.angels.common.entities;

import me.sub.angels.api.ICanTeleport;
import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.common.misc.WATeleporter;
import me.sub.angels.config.WAConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
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
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EntityWeepingAngel extends EntityQuantumLockBase {

    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
    private static final DataParameter<String> CURRENT_POSE = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.STRING);
    private SoundEvent[] seenSounds = new SoundEvent[]{WAObjects.Sounds.ANGEL_SEEN_1, WAObjects.Sounds.ANGEL_SEEN_2, WAObjects.Sounds.ANGEL_SEEN_3, WAObjects.Sounds.ANGEL_SEEN_4, WAObjects.Sounds.ANGEL_SEEN_5};
    private long soundTime = 0L;

    public EntityWeepingAngel(World world) {
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

    @Override
    protected void entityInit() {
        super.entityInit();
        getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
        getDataManager().register(TYPE, getRandomType());
        getDataManager().register(CURRENT_POSE, PoseManager.AngelPoses.ANGRY.toString());
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
                heal(2.0F);
            }
        } else {
            if (entity instanceof EntityPlayer) {
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

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setString(WAConstants.POSE, getPose());
        compound.setInteger(WAConstants.TYPE, getType());
        compound.setBoolean(WAConstants.ANGEL_CHILD, isChild());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey(WAConstants.POSE)) setPose(compound.getString(WAConstants.POSE));

        if (compound.hasKey(WAConstants.TYPE)) setType(compound.getInteger(WAConstants.TYPE));

        if (compound.hasKey(WAConstants.ANGEL_CHILD)) setChild(compound.getBoolean(WAConstants.ANGEL_CHILD));
    }

    @Override
    protected void collideWithEntity(Entity entity) {
        entity.applyEntityCollision(this);
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
        // TODO figure out why the hell it's being so weird
        replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
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
                playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
            } else {
                if (world.rand.nextInt(5) == 5) {
                    playSound(WAObjects.Sounds.CHILD_RUN, 1.0F, 1.0F);
                }
            }
        }
    }

    private boolean replaceBlocks(AxisAlignedBB box) {
        // if (!WAConfig.angels.blockBreaking) return false;

        for (int x = (int) box.minX; x <= box.maxX; x++) {
            for (int y = (int) box.minY; y <= box.maxY; y++) {
                for (int z = (int) box.minZ; z <= box.maxZ; z++) {

                    BlockPos pos = new BlockPos(x, y, z);
                    IBlockState blockState = world.getBlockState(pos);

                    if (world.getGameRules().getBoolean("mobGriefing") && getHealth() > 5) {

                        if (world.isRemote) {
                            System.out.println(blockState.getBlock());
                        }

                        if (!canBreak(blockState)) {
                            return false;
                        }

                        if (blockState.getBlock() == Blocks.TORCH || blockState.getBlock() == Blocks.REDSTONE_TORCH || blockState.getBlock() == Blocks.GLOWSTONE || blockState.getLightValue(world, pos) >= 7) {
                            playBreakEvent(pos, Blocks.AIR);
                            return true;
                        }

                        if (blockState.getBlock() == Blocks.LIT_PUMPKIN) {
                            playBreakEvent(pos, Blocks.PUMPKIN);
                            return true;
                        }

                        if (blockState.getBlock() == Blocks.LIT_REDSTONE_LAMP) {
                            playBreakEvent(pos, Blocks.REDSTONE_LAMP);
                            return true;
                        }

                        if (blockState.getBlock() instanceof BlockPortal || blockState.getBlock() instanceof BlockEndPortal) {
                            if (getHealth() < getMaxHealth()) {
                                heal(1.5F);
                                world.setBlockToAir(pos);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
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
        } else {
            world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, pos.getX(), pos.getY(), pos.getZ(), 1.0D, 1.0D, 1.0D);
        }
    }

    public SoundEvent getSeenSound() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - soundTime >= 1000) {
            soundTime = currentTime;
            return seenSounds[rand.nextInt(seenSounds.length)];
        }

        return null;
    }

    private void teleportPlayer(EntityPlayer player) {
        if (world.isRemote) return;
        int range = WAConfig.angels.teleportRange;
        EntityAnomaly anomaly = new EntityAnomaly(world);
        anomaly.setPositionAndUpdate(player.posX, player.posY, player.posZ);
        world.spawnEntity(anomaly);
        int dim = decideDimension();
        WorldServer ws = (WorldServer) world;
        ws.getMinecraftServer().getWorld(dim);
        int x = rand.nextInt(range);
        int z = rand.nextInt(range);
        WATeleporter.teleportDimEntity(player, player.getPosition().add(x, ws.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY(), z), dim, this);
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

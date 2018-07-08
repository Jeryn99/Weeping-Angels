package me.sub.angels.common.entities;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.config.WAConfig;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityWeepingAngel extends EntityMob {

    private static final DataParameter<Boolean> IS_SEEN = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TIME_VIEWED = EntityDataManager.createKey(EntityWeepingAngel.class, DataSerializers.VARINT);
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

        experienceValue = 25;
    }


    @Override
    protected void entityInit() {
        super.entityInit();
        getDataManager().register(IS_SEEN, false);
        getDataManager().register(IS_CHILD, rand.nextInt(10) == 4);
        getDataManager().register(TIME_VIEWED, 0);
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

    public boolean isSeen() {
        return getDataManager().get(IS_SEEN);
    }

    public void setSeen(boolean beingViewed) {
        getDataManager().set(IS_SEEN, beingViewed);
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

    public int getSeenTime() {
        return getDataManager().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getDataManager().set(TIME_VIEWED, time);
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
        compound.setBoolean(WAConstants.IS_SEEN, isSeen());
        compound.setInteger(WAConstants.TIME_SEEN, getSeenTime());
        compound.setString(WAConstants.POSE, getPose());
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

        this.setSeen(isInView());

        super.onUpdate();

        if (isSeen()) {
            replaceBlocks(getEntityBoundingBox().grow(WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange, WAConfig.angels.blockBreakRange));
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
                playSound(WAObjects.Sounds.STONE_SCRAP, 0.2F, 1.0F);
            } else {
                if (world.rand.nextInt(5) == 5) {
                    playSound(WAObjects.Sounds.CHILD_RUN, 1.0F, 1.0F);
                }
            }
        }
    }

    private void replaceBlocks(AxisAlignedBB box) {
        if (world.isRemote || ticksExisted % 100 != 0 || !WAConfig.angels.blockBreaking || !world.getGameRules().getBoolean("mobGreifing"))
            return;
        // TODO Re-write block breaking
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

    private boolean isInView() {
        for (EntityPlayer player : FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayers()) {
            if (AngelUtils.isInSight(player, this) && !player.isSpectator()) {
                if (getAttackTarget() == player && getSeenTime() == 1 && !AngelUtils.isDarkForPlayer(this, player) && !player.isPotionActive(MobEffects.BLINDNESS)) {
                    setPose(PoseManager.getBestPoseForSituation(this, player).toString());
                    SoundEvent sound = getSeenSound();
                    playSound(sound, 1.0F, 1.0F);
                }
                return true;
            }
        }
        setSeenTime(0);
        return false;
    }
}

package me.sub.angels.common.entities;

import me.sub.angels.client.models.poses.PoseManager;
import me.sub.angels.common.misc.WAConstants;
import me.sub.angels.utils.AngelUtils;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public abstract class EntityQuantumLockBase extends MobEntityWithAi
{
    private static final TrackedData<Boolean> IS_SEEN = DataTracker.registerData(EntityWeepingAngel.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> TIME_VIEWED = DataTracker.registerData(EntityWeepingAngel.class, TrackedDataHandlerRegistry.INTEGER);

    protected EntityQuantumLockBase(EntityType<?> entityType_1, World world_1)
    {
        super(entityType_1, world_1);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        getDataTracker().startTracking(IS_SEEN, false);
        getDataTracker().startTracking(TIME_VIEWED, 0);
    }

    @Override public void writeCustomDataToTag(CompoundTag compoundTag_1)
    {
        super.writeCustomDataToTag(compoundTag_1);
        compoundTag_1.putBoolean(WAConstants.IS_SEEN, isSeen());
        compoundTag_1.putInt(WAConstants.TIME_SEEN, getSeenTime());
    }

    @Override public void readCustomDataFromTag(CompoundTag compound)
    {
        super.readCustomDataFromTag(compound);
        if (compound.containsKey(WAConstants.IS_SEEN)) setSeen(compound.getBoolean(WAConstants.IS_SEEN));
        if (compound.containsKey(WAConstants.TIME_SEEN)) setSeenTime(compound.getInt(WAConstants.TIME_SEEN));
    }

    public boolean isSeen() {
        return getDataTracker().get(IS_SEEN);
    }

    public void setSeen(boolean beingViewed) {
        getDataTracker().set(IS_SEEN, beingViewed);
    }

    public int getSeenTime() {
        return getDataTracker().get(TIME_VIEWED);
    }

    public void setSeenTime(int time) {
        getDataTracker().set(TIME_VIEWED, time);
    }

//    private void quantumLocking() {
//        List<EntityQuantumLockBase> entityList = world.getEntitiesWithinAABB(EntityQuantumLockBase.class, getEntityBoundingBox().expand(32.0D, 32.0D, 32.0D));
//        for (EntityQuantumLockBase viewer : entityList) {
//            if (viewer != this) {
//
//                if (viewer instanceof EntityWeepingAngel) {
//                    EntityWeepingAngel angelViewer = (EntityWeepingAngel) viewer;
//                    if (angelViewer.getPose().equals(PoseManager.AngelPoses.HIDING_FACE.toString())) {
//                        return;
//                    }
//                }
//
//                Vec3d vec3 = viewer.getLook(1.0F).normalize();
//                Vec3d vecPoint = new Vec3d(posX - viewer.posX, getEntityBoundingBox().minY + height / 2.0F - (viewer.posY + viewer.getEyeHeight()), posZ - viewer.posZ);
//                double lengthVector = vecPoint.lengthVector();
//                vecPoint = vecPoint.normalize();
//                double dotProduct = vec3.dotProduct(vecPoint);
//                boolean viewed = (dotProduct > 1.0D / lengthVector) && (viewer.canEntityBeSeen(this));
//
//                if (viewed) {
//                    setSeenTime(0);
//                }
//
//                setSeen(viewed);
//            }
//        }
//    }

    @Override
    public void update() {
//        if (WAConfig.angels.angelLocking) {
//            quantumLocking();
//        }
        this.setSeen(isInView());
        super.update();
    }

    private boolean isInView() {
        if(this.world instanceof ServerWorld)
        {
            for (PlayerEntity player : this.world.players)
            {
                if (AngelUtils.isInSight(player, this) && !player.isSpectator() && !AngelUtils.isDarkForPlayer(this, player) && !player.hasPotionEffect(StatusEffects.BLINDNESS))
                {
                    if (getTarget() == player && getSeenTime() == 1)
                    {
                        if (this instanceof EntityWeepingAngel)
                        {
                            EntityWeepingAngel angel = (EntityWeepingAngel) this;
                            angel.setPose(PoseManager.getBestPoseForSituation(angel, player).toString());

//                            SoundEvent sound = angel.getSeenSound();
//                            playSound(sound, 1.0F, 1.0F);
                        }
                    }
                    return true;
                }
            }
            setSeenTime(0);
        }
        return false;
    }
}

package me.sub.angels.client.models.poses;

import me.sub.angels.common.PoseRegistry;
import me.sub.angels.common.entities.EntityAngel;
import me.sub.angels.utils.AngelUtils;
import me.sub.angels.utils.WAUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PoseManager {

    public static PoseRegistry.AngelPoses getBestPoseForSituation(EntityAngel angel, EntityLivingBase target) {

        if (target == null || !(target instanceof EntityPlayerMP)) {
            return PoseRegistry.AngelPoses.IDLE;
        }
        if (angel.world.getEntitiesWithinAABB(EntityAngel.class, angel.getEntityBoundingBox().expand(25.0D, 25.0D, 25.0D)).size() > 1) {
            return PoseRegistry.AngelPoses.HIDING_FACE;
        }
        EntityPlayer player = (EntityPlayer) target;
        boolean flag = WAUtils.handLightCheck(player);
        boolean seenDueToLight = angel.world.getLight(angel.getPosition()) != 0 || flag;
        boolean seenByTarget = AngelUtils.isTargetInSight(target, angel);

            if (angel.getDistance(target) < 1.0F && seenByTarget) {
                return PoseRegistry.AngelPoses.ANGRY;
            }
            if (angel.getDistance(target) < 5.0F && seenDueToLight) {
                return PoseRegistry.AngelPoses.ANGRY_TWO;
            }
            if (angel.getDistance(target) < 10.0F) {
                return PoseRegistry.AngelPoses.OPEN_ARMS;
            }
            if (angel.getDistance(target) < 15.0F) {
                return PoseRegistry.AngelPoses.THINKING;
            }
            if (angel.getDistance(target) < 25.0F) {
                return PoseRegistry.AngelPoses.SHY;
            }

        return PoseRegistry.AngelPoses.DAB;
    }

}

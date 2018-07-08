package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityWeepingAngel;
import net.minecraft.entity.EntityLivingBase;

public class PoseManager {

    public static AngelPoses getBestPoseForSituation(EntityWeepingAngel angel, EntityLivingBase player) {

        if (angel.getDistance(player) < 1.0F) {
            return AngelPoses.ANGRY;
        }
        if (angel.getDistance(player) < 5.0F) {
            return AngelPoses.ANGRY_TWO;
        }
        if (angel.getDistance(player) < 10.0F) {
            return AngelPoses.OPEN_ARMS;
        }
        if (angel.getDistance(player) < 15.0F) {
            return AngelPoses.THINKING;
        }
        if (angel.getDistance(player) < 25.0F) {
            return AngelPoses.HIDING_FACE;
        }

        return AngelPoses.HIDING_FACE;
    }

    public enum AngelPoses {
        IDLE(new PoseIdle()),
        HIDING_FACE(new PoseHidingFace()),
        ANGRY(new PoseAngry()),
        SHY(new PoseShy()),
        ANGRY_TWO(new PoseAngryTwo()),
        THINKING(new PoseThinking()),
        DAB(new PoseDab()),
        OPEN_ARMS(new PoseOpenArms());

        public final PoseBase pose;

        AngelPoses(PoseBase pose) {
            this.pose = pose;
        }

        public PoseBase getPose() {
            return pose;
        }
    }

}

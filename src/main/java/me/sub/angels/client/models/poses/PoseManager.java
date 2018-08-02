package me.sub.angels.client.models.poses;

import java.util.Random;

public class PoseManager {

    private static Random RANDOM = new Random();

    public static <T extends Enum<?>> T randomPose(Class<T> clazz) {
        int x = RANDOM.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

	public enum AngelPoses {
        IDLE(new PoseIdle()), HIDING_FACE(new PoseHidingFace()), ANGRY(new PoseAngry()), SHY(new PoseShy()), ANGRY_TWO(new PoseAngryTwo()), OPEN_ARMS(new PoseOpenArms());
		
		public final PoseBase pose;
		
		AngelPoses(PoseBase pose) {
			this.pose = pose;
		}
		
		public PoseBase getPose() {
			return pose;
		}
	}
	
}

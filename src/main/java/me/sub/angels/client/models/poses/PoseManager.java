package me.sub.angels.client.models.poses;

import java.util.TreeMap;

public class PoseManager {

    private static TreeMap<String, PoseBase> POSES = new TreeMap<>();
	
	public static void addPose(String name, PoseBase pose) {
		POSES.put(name, pose);
	}
	
	public static void init() {
		addPose(AngelPoses.HIDING_FACE.toString(), new PoseHidingFace());
		addPose(AngelPoses.IDLE.toString(), new PoseIdle());
		addPose(AngelPoses.ANGRY.toString(), new PoseAngry());
		addPose(AngelPoses.ANGRY_TWO.toString(), new PoseAngryTwo());
		addPose(AngelPoses.SHY.toString(), new PoseShy());
		addPose(AngelPoses.THINKING.toString(), new PoseThinking());
		addPose(AngelPoses.DAB.toString(), new PoseDab());
        addPose(AngelPoses.OPEN_ARMS.toString(), new PoseOpenArms());
	}
	
	public static PoseBase getPose(String name) {
		return POSES.get(name);
	}

    public static TreeMap<String, PoseBase> getPoses() {
        return POSES;
    }

	public enum AngelPoses {
        IDLE, HIDING_FACE, ANGRY, SHY, ANGRY_TWO, THINKING, DAB, OPEN_ARMS
	}
}

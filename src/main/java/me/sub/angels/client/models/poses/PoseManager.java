package me.sub.angels.client.models.poses;

import java.util.HashMap;

public class PoseManager {
	
	public static HashMap<String, PoseBase> POSES = new HashMap<>();
	
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
	}
	
	public static PoseBase getPose(String name) {
		return POSES.get(name);
	}
	
	public enum AngelPoses {
		IDLE, HIDING_FACE, ANGRY, SHY, ANGRY_TWO, OPEN_ARMS, THINKING, DAB
	}

}

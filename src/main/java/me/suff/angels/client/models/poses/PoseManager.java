package me.suff.angels.client.models.poses;

import java.util.ArrayList;
import java.util.Random;

public class PoseManager {
	
	private static Random RANDOM = new Random();
	
	private static ArrayList<PoseBase> POSES = new ArrayList<>();
	
	public static PoseBase POSE_ANGRY = registerPose(new PoseAngry(), "ANGRY");
	public static PoseBase POSE_ANGRY_TWO = registerPose(new PoseAngryTwo(), "ANGRY_TWO");
	public static PoseBase POSE_SHY = registerPose(new PoseShy(), "SHY");
	public static PoseBase POSE_HIDING_FACE = registerPose(new PoseHidingFace(), "HIDING_FACE");
	public static PoseBase POSE_OPEN_ARMS = registerPose(new PoseOpenArms(), "OPEN_ARMS");
	public static PoseBase POSE_IDLE = registerPose(new PoseOpenArms(), "IDLE");
	// public static PoseBase POSE_TEMP = registerPose(new PoseNew(), "TEMP");
	
	public static PoseBase registerPose(PoseBase poseBase, String name) {
		poseBase.setRegistryName(name);
		POSES.add(poseBase);
		return poseBase;
	}
	
	public static PoseBase getRandomPose() {
		return POSES.get(RANDOM.nextInt(POSES.size()));
	}
	
	public static PoseBase getPoseFromString(String poseName) {
		for (PoseBase pose : POSES) {
			if (pose.getRegistryName().equals(poseName)) {
				return pose;
			}
		}
		return POSE_HIDING_FACE;
	}
}

package com.github.reallysub.angels.common;

import com.github.reallysub.angels.client.models.poses.PoseAngry;
import com.github.reallysub.angels.client.models.poses.PoseHidingFace;
import com.github.reallysub.angels.client.models.poses.PoseIdle;
import com.github.reallysub.angels.common.entities.enums.AngelPoses;
import com.github.reallysub.angels.client.models.poses.PoseBase;

import java.util.HashMap;

public class PoseRegistry {
	
	public static HashMap<String, PoseBase> POSES = new HashMap();
	
	public static void registerPose(String name, PoseBase pose) {
		POSES.put(name, pose);
	}
	
	public static void init() {
		registerPose(AngelPoses.HIDING_FACE.toString(), new PoseHidingFace());
		registerPose(AngelPoses.IDLE.toString(), new PoseIdle());
		registerPose(AngelPoses.ANGERY.toString(), new PoseAngry());
	}
	
	public static PoseBase getPose(String name) {
		return POSES.get(name);
	}
}

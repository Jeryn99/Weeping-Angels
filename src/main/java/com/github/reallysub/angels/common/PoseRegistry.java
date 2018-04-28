package com.github.reallysub.angels.common;

import java.util.HashMap;

import com.github.reallysub.angels.client.models.poses.PoseAngry;
import com.github.reallysub.angels.client.models.poses.PoseAngryTwo;
import com.github.reallysub.angels.client.models.poses.PoseBase;
import com.github.reallysub.angels.client.models.poses.PoseDab;
import com.github.reallysub.angels.client.models.poses.PoseHidingFace;
import com.github.reallysub.angels.client.models.poses.PoseIdle;
import com.github.reallysub.angels.client.models.poses.PoseShy;
import com.github.reallysub.angels.client.models.poses.PoseThinking;
import com.github.reallysub.angels.common.entities.enums.AngelPoses;

public class PoseRegistry {
	
	public static HashMap<String, PoseBase> POSES = new HashMap();
	
	public static void registerPose(String name, PoseBase pose) {
		POSES.put(name, pose);
	}
	
	public static void init() {
		registerPose(AngelPoses.HIDING_FACE.toString(), new PoseHidingFace());
		registerPose(AngelPoses.IDLE.toString(), new PoseIdle());
		registerPose(AngelPoses.ANGRY.toString(), new PoseAngry());
		registerPose(AngelPoses.ANGRY_TWO.toString(), new PoseAngryTwo());
		registerPose(AngelPoses.SHY.toString(), new PoseShy());
		registerPose(AngelPoses.THINKING.toString(), new PoseThinking());
		registerPose(AngelPoses.DAB.toString(), new PoseDab());
	}
	
	public static PoseBase getPose(String name) {
		return POSES.get(name);
	}
}

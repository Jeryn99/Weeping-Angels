package me.sub.angels.client.models.poses;

import me.sub.angels.common.entities.EntityAngel;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;

public class PoseManager {
	
	private static HashMap<String, PoseBase> POSES = new HashMap<>();
	
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
	
	public static AngelPoses getBestPoseForSituation(EntityAngel angel, EntityPlayer player) {
		
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
			return AngelPoses.SHY;
		}
		
		return AngelPoses.DAB;
	}
	
}

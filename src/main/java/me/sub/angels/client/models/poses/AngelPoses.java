package me.sub.angels.client.models.poses;

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

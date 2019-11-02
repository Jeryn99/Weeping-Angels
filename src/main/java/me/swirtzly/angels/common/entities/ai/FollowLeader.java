package me.swirtzly.angels.common.entities.ai;

import me.swirtzly.angels.common.entities.EntityQuantumLockBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;

/**
 * Created by Swirtzly
 * on 15/10/2019 @ 09:23
 */
public class FollowLeader extends EntityAIBase {

    private EntityQuantumLockBase angel;

    public FollowLeader(EntityQuantumLockBase angel) {
        this.angel = angel;
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        return !angel.IsLeader() && angel.getLeader() != null;
    }


    @Override
    public boolean shouldContinueExecuting() {
        return shouldExecute();
    }

    @Override
    public void updateTask() {
        super.updateTask();
        if (angel.getLeader() != null && !angel.getLeader().isDead && angel.getLeader() != angel) {
            AngelFormation form = angel.getLeader().getFormation();
            if (form != null) {
                Vec3d pos = form.getPlace(angel.getLeader(), angel);
                if (pos != null) {
                    //TODO: Make this cleaner
                    double xRot = Math.sin(Math.toRadians(angel.getLeader().rotationYaw)) * pos.x;
                    double zRot = -Math.cos(Math.toRadians(angel.getLeader().rotationYaw) * pos.z);
                    double x = angel.getLeader().posX, y = angel.getLeader().posY, z = angel.getLeader().posZ;

                    angel.getNavigator().tryMoveToXYZ(xRot + x, y, zRot + z, 1.2D);

                    angel.rotationYaw = angel.getLeader().rotationYaw;
                    angel.rotationYawHead = angel.getLeader().rotationYawHead;
                    angel.rotationPitch = angel.getLeader().rotationPitch;
                    System.out.println("Tried to follow");
                }
            }
        }
    }

}

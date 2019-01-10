package me.fril.angels.utils;

import me.fril.angels.common.entities.EntityQuantumLockBase;
import me.fril.angels.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class ViewUtil {

    public static boolean isInFrontOfEntity(Entity entity, Entity target, boolean vr) {
        Vec3d vecTargetsPos = target.getPositionVector();
        Vec3d vecLook;

        if (vr) {
            if (entity instanceof EntityPlayer) {
                vecLook = CommonProxy.reflector.getHMDRot((EntityPlayer) entity);
            } else {
                throw new RuntimeException("Attempted to use a non-player entity with VRSupport: " + entity.getEntityData());
            }
        } else {
            vecLook = entity.getLookVec();
        }

        Vec3d vecFinal = vecTargetsPos.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
        vecFinal = new Vec3d(vecFinal.x, 0.0D, vecFinal.z);
        return vecFinal.dotProduct(vecLook) < 0.0;
    }


    public static boolean viewBlocked(EntityLivingBase viewer, EntityLivingBase angel) {
        AxisAlignedBB viewerBoundBox = viewer.getEntityBoundingBox();
        AxisAlignedBB angelBoundingBox = angel.getEntityBoundingBox();
        Vec3d[] viewerPoints = {new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3d(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};
        Vec3d[] angelPoints = {new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3d(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.minZ),};

        for (int i = 0; i < viewerPoints.length; i++) {
            if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], false, true, false) == null) return false;
        }
        return true;
    }


    /**
     * Method that detects whether a entity is the the view sight of another entity
     *
     * @param viewer      The viewer entity
     * @param beingViewed The entity being watched by viewer
     */
    public static boolean canEntitySee(EntityLivingBase viewer, EntityLivingBase beingViewed) {
        double dx = beingViewed.posX - viewer.posX;
        double dz;
        for (dz = beingViewed.posX - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.rotationYaw > 360) {
            viewer.rotationYaw -= 360;
        }
        while (viewer.rotationYaw < -360) {
            viewer.rotationYaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60 && viewer.canEntityBeSeen(beingViewed);
    }

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param viewer The viewer entity
     * @param tile   The tile being watched by viewer
     */
    public static boolean isInSightTile(EntityLivingBase viewer, TileEntity tile) {
        double dx = tile.getPos().getX() - viewer.posX;
        double dz;
        for (dz = tile.getPos().getX() - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.rotationYaw > 360) {
            viewer.rotationYaw -= 360;
        }
        while (viewer.rotationYaw < -360) {
            viewer.rotationYaw += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60;
    }

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param livingBase The viewer entity
     * @param angel      The entity being watched by viewer
     */
    public static boolean isInSight(EntityLivingBase livingBase, EntityQuantumLockBase angel) {
        if (viewBlocked(livingBase, angel)) return false;

        if (livingBase instanceof EntityPlayer) {
            return isInFrontOfEntity(livingBase, angel, CommonProxy.reflector.isVRPlayer((EntityPlayer) livingBase));
        }
        return isInFrontOfEntity(livingBase, angel, false);
    }

}

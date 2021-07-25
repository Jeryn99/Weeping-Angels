package me.suff.mc.angels.utils;

import me.suff.mc.angels.WeepingAngels;
import me.suff.mc.angels.common.entities.QuantumLockedLifeform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ViewUtil {

    private static final float headSize = 0.15f;

    public static boolean isInFrontOfEntity(LivingEntity entity, Entity target, boolean vr) {
        Vec3 vecTargetsPos = target.position();
        Vec3 vecLook;

        if (vr) {
            if (entity instanceof Player) {
                vecLook = WeepingAngels.VR_REFLECTOR.getHMDRot((Player) entity);
            } else {
                throw new RuntimeException("Attempted to use a non-player entity with VRSupport: " + entity.getPersistentData());
            }
        } else {
            vecLook = entity.getLookAngle();
        }
        Vec3 vecFinal = vecTargetsPos.vectorTo(new Vec3(entity.getX(), entity.getY(), entity.getZ())).normalize();
        vecFinal = new Vec3(vecFinal.x, 0.0D, vecFinal.z);
        return vecFinal.dot(vecLook) < 0.0;
    }

    /**
     * Method that detects whether a entity is the the view sight of another entity
     *
     * @param viewer      The viewer entity
     * @param beingViewed The entity being watched by viewer
     */
    public static boolean canEntitySee(LivingEntity viewer, BlockEntity beingViewed) {
        double dx = beingViewed.getBlockPos().getX() - viewer.getX();
        double dz;
        for (dz = beingViewed.getBlockPos().getX() - viewer.getZ(); dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.yHeadRot > 360) {
            viewer.yHeadRot -= 360;
        }
        while (viewer.yHeadRot < -360) {
            viewer.yHeadRot += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.yHeadRot;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }

        return yaw < 60 && yaw > -60;
    }

    public static boolean isInSightPos(LivingEntity viewer, BlockPos pos) {
        double dx = pos.getX() - viewer.getX();
        double dz;
        for (dz = pos.getX() - viewer.getZ(); dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
            dx = (Math.random() - Math.random()) * 0.01D;
        }
        while (viewer.yHeadRot > 360) {
            viewer.yHeadRot -= 360;
        }
        while (viewer.yHeadRot < -360) {
            viewer.yHeadRot += 360;
        }
        float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.yHeadRot;
        yaw = yaw - 90;
        while (yaw < -180) {
            yaw += 360;
        }
        while (yaw >= 180) {
            yaw -= 360;
        }
        return yaw < 60 && yaw > -60;
    }

    public static void lookAt(LivingEntity looker, LivingEntity target) {
        double dirx = looker.blockPosition().getX() - target.blockPosition().getX();
        double diry = looker.blockPosition().getX() - target.blockPosition().getY();
        double dirz = looker.blockPosition().getX() - target.blockPosition().getZ();

        double len = Math.sqrt(dirx * dirx + diry * diry + dirz * dirz);

        dirx /= len;
        diry /= len;
        dirz /= len;

        double pitch = Math.asin(diry);
        double yaw = Math.atan2(dirz, dirx);

        //to degree
        pitch = pitch * 180.0 / Math.PI;
        yaw = yaw * 180.0 / Math.PI;

        yaw += 90f;
        looker.yHeadRot = (float) pitch;
        looker.yHeadRot = (float) yaw;
    }

    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param livingBase The viewer entity
     * @param angel      The entity being watched by viewer
     */
    public static boolean isInSight(LivingEntity livingBase, QuantumLockedLifeform angel) {
        if (viewBlocked(livingBase, angel)) {
            return false;
        }
        if (livingBase instanceof Player) {
            return isInFrontOfEntity(livingBase, angel, WeepingAngels.VR_REFLECTOR.isVRPlayer((Player) livingBase));
        }
        return isInFrontOfEntity(livingBase, angel, false);
    }

    public static boolean viewBlocked(LivingEntity viewer, LivingEntity angel) {
        AABB viewerBoundBox = viewer.getBoundingBox();
        AABB angelBoundingBox = angel.getBoundingBox();
        Vec3[] viewerPoints = {new Vec3(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};

        if (viewer instanceof Player) {
            Vec3 pos;
            if (WeepingAngels.VR_REFLECTOR.isVRPlayer((Player) viewer))
                pos = WeepingAngels.VR_REFLECTOR.getHMDPos((Player) viewer);
            else
                pos = new Vec3(viewer.getX(), viewer.getY() + 1.62f, viewer.getZ());
            viewerPoints[0] = pos.add(-headSize, -headSize, -headSize);
            viewerPoints[1] = pos.add(-headSize, -headSize, headSize);
            viewerPoints[2] = pos.add(-headSize, headSize, -headSize);
            viewerPoints[3] = pos.add(-headSize, headSize, headSize);
            viewerPoints[4] = pos.add(headSize, headSize, -headSize);
            viewerPoints[5] = pos.add(headSize, headSize, headSize);
            viewerPoints[6] = pos.add(headSize, -headSize, headSize);
            viewerPoints[7] = pos.add(headSize, -headSize, -headSize);
        }


        Vec3[] angelPoints = {new Vec3(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.minZ), new Vec3(angelBoundingBox.minX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3(angelBoundingBox.minX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.minZ), new Vec3(angelBoundingBox.maxX, angelBoundingBox.maxY, angelBoundingBox.maxZ), new Vec3(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.maxZ), new Vec3(angelBoundingBox.maxX, angelBoundingBox.minY, angelBoundingBox.minZ),};

        for (int i = 0; i < viewerPoints.length; i++) {
            if (viewer.level.clip(new ClipContext(viewerPoints[i], angelPoints[i], ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, viewer)).getType() == HitResult.Type.MISS) {
                return false;
            }
            if (rayTraceBlocks(viewer, viewer.level, viewerPoints[i], angelPoints[i], pos -> {
                BlockState state = viewer.level.getBlockState(pos);
                return !canSeeThrough(state, viewer.level, pos);
            }) == null) return false;
        }

        if (angel.tickCount % 1200 == 0) {
            if (angel.distanceTo(viewer) < 15) {
                viewer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15));
            }
        }

        return true;
    }


    @Nullable
    private static HitResult rayTraceBlocks(LivingEntity livingEntity, Level world, Vec3 vec31, Vec3 vec32, Predicate<BlockPos> stopOn) {
        if (!Double.isNaN(vec31.x) && !Double.isNaN(vec31.y) && !Double.isNaN(vec31.z)) {
            if (!Double.isNaN(vec32.x) && !Double.isNaN(vec32.y) && !Double.isNaN(vec32.z)) {
                int i = Mth.floor(vec32.x);
                int j = Mth.floor(vec32.y);
                int k = Mth.floor(vec32.z);
                int l = Mth.floor(vec31.x);
                int i1 = Mth.floor(vec31.y);
                int j1 = Mth.floor(vec31.z);
                BlockPos blockpos = new BlockPos(l, i1, j1);
                if (stopOn.test(blockpos)) {
                    HitResult raytraceresult = world.clip(new ClipContext(vec31, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity));
                    if (raytraceresult != null) {
                        return raytraceresult;
                    }
                }

                int k1 = 200;

                while (k1-- >= 0) {
                    if (Double.isNaN(vec31.x) || Double.isNaN(vec31.y) || Double.isNaN(vec31.z)) {
                        return null;
                    }

                    if (l == i && i1 == j && j1 == k) {
                        return null;
                    }

                    boolean flag2 = true;
                    boolean flag = true;
                    boolean flag1 = true;
                    double d0 = 999.0D;
                    double d1 = 999.0D;
                    double d2 = 999.0D;

                    if (i > l) {
                        d0 = (double) l + 1.0D;
                    } else if (i < l) {
                        d0 = (double) l + 0.0D;
                    } else {
                        flag2 = false;
                    }

                    if (j > i1) {
                        d1 = (double) i1 + 1.0D;
                    } else if (j < i1) {
                        d1 = (double) i1 + 0.0D;
                    } else {
                        flag = false;
                    }

                    if (k > j1) {
                        d2 = (double) j1 + 1.0D;
                    } else if (k < j1) {
                        d2 = (double) j1 + 0.0D;
                    } else {
                        flag1 = false;
                    }

                    double d3 = 999.0D;
                    double d4 = 999.0D;
                    double d5 = 999.0D;
                    double d6 = vec32.x - vec31.x;
                    double d7 = vec32.y - vec31.y;
                    double d8 = vec32.z - vec31.z;

                    if (flag2) {
                        d3 = (d0 - vec31.x) / d6;
                    }

                    if (flag) {
                        d4 = (d1 - vec31.y) / d7;
                    }

                    if (flag1) {
                        d5 = (d2 - vec31.z) / d8;
                    }

                    if (d3 == -0.0D) {
                        d3 = -1.0E-4D;
                    }

                    if (d4 == -0.0D) {
                        d4 = -1.0E-4D;
                    }

                    if (d5 == -0.0D) {
                        d5 = -1.0E-4D;
                    }

                    Direction enumfacing;

                    if (d3 < d4 && d3 < d5) {
                        enumfacing = i > l ? Direction.WEST : Direction.EAST;
                        vec31 = new Vec3(d0, vec31.y + d7 * d3, vec31.z + d8 * d3);
                    } else if (d4 < d5) {
                        enumfacing = j > i1 ? Direction.DOWN : Direction.UP;
                        vec31 = new Vec3(vec31.x + d6 * d4, d1, vec31.z + d8 * d4);
                    } else {
                        enumfacing = k > j1 ? Direction.NORTH : Direction.SOUTH;
                        vec31 = new Vec3(vec31.x + d6 * d5, vec31.y + d7 * d5, d2);
                    }

                    l = Mth.floor(vec31.x) - (enumfacing == Direction.EAST ? 1 : 0);
                    i1 = Mth.floor(vec31.y) - (enumfacing == Direction.UP ? 1 : 0);
                    j1 = Mth.floor(vec31.z) - (enumfacing == Direction.SOUTH ? 1 : 0);
                    blockpos = new BlockPos(l, i1, j1);
                    if (stopOn.test(blockpos)) {
                        HitResult raytraceresult1 = world.clip(new ClipContext(vec31, vec32, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity));

                        if (raytraceresult1 != null) {
                            return raytraceresult1;
                        }
                    }
                }
            }
        }

        return null;
    }

    // This is bloated, I know, but I want to make sure I cover EVERY basis :/
    public static boolean canSeeThrough(BlockState blockState, Level world, BlockPos pos) {

        // Covers all Block, Material and Tag checks :D
        if (!blockState.canOcclude() || !blockState.isSolidRender(world, pos)) {
            return true;
        }

        Block block = blockState.getBlock();

        // Special Snowflakes
        if (block instanceof DoorBlock) {
            return blockState.getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER;
        }

        // Config
        if (blockState.is(AngelUtil.ANGEL_IGNORE)) {
            return true;
        }

        return blockState.getCollisionShape(world, pos) == Shapes.empty();
    }

}

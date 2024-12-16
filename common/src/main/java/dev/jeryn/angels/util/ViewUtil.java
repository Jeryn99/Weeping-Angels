package dev.jeryn.angels.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.jeryn.angels.common.entity.angel.AbstractWeepingAngel;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.function.Predicate;

public class ViewUtil {

    private static final float headSize = 0.15f;

    public static boolean isInFrontOfEntity(LivingEntity entity, Entity target, boolean vr) {
        Vec3 vecTargetsPos = target.position();
        Vec3 vecLook = entity.getLookAngle();
        if (entity instanceof Player player && vr) {
            vecLook = manipulateVrRotation(player, entity.getLookAngle());
        }
        Vec3 vecFinal = vecTargetsPos.vectorTo(new Vec3(entity.getX(), entity.getY(), entity.getZ())).normalize();
        vecFinal = new Vec3(vecFinal.x, 0.0D, vecFinal.z);
        return vecFinal.dot(vecLook) < 0.0;
    }


    @ExpectPlatform
    public static Vec3 manipulateVrRotation(Player player, Vec3 vec3) {
        throw new RuntimeException("VR");
    }

    @ExpectPlatform
    public static Vec3 manipulateVrPosition(Player player, Vec3 vec3) {
        throw new RuntimeException("VR");
    }

    @ExpectPlatform
    public static boolean isVrPlayer(Player player) {
        throw new RuntimeException("VR");
    }


    /**
     * Method that detects whether a tile is the the view sight of viewer
     *
     * @param livingBase The viewer entity
     * @param angel      The entity being watched by viewer
     */
    public static boolean isInSight(LivingEntity livingBase, AbstractWeepingAngel angel) {

        if (isPlayerBlind(livingBase)) return false;

        if (viewBlocked(livingBase, angel)) {
            return false;
        }
        if (livingBase instanceof Player player) {
            return isInFrontOfEntity(livingBase, angel, isVrPlayer(player));
        }
        return isInFrontOfEntity(livingBase, angel, false);
    }

    public static boolean viewBlocked(LivingEntity viewer, LivingEntity angel) {
        AABB viewerBoundBox = viewer.getBoundingBox();
        AABB angelBoundingBox = angel.getBoundingBox();
        Vec3[] viewerPoints = {new Vec3(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.minX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.minZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.maxY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.maxZ), new Vec3(viewerBoundBox.maxX, viewerBoundBox.minY, viewerBoundBox.minZ),};

        if (viewer instanceof Player player) {
            Vec3 pos = new Vec3(viewer.getX(), viewer.getY() + 1.62f, viewer.getZ());

            if (isVrPlayer(player)) {
                pos = ViewUtil.manipulateVrPosition(player, pos);
            }

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
            if (viewer.level().clip(new ClipContext(viewerPoints[i], angelPoints[i], ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, viewer)).getType() == HitResult.Type.MISS) {
                return false;
            }
            if (rayTraceBlocks(viewer, viewer.level(), viewerPoints[i], angelPoints[i], pos -> {
                BlockState state = viewer.level().getBlockState(pos);
                return !canSeeThrough(state, viewer.level(), pos);
            }) == null) return false;
        }

        if (angel.tickCount % 1200 == 0) {
            if (angel.distanceTo(viewer) < 15) {
                viewer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 15));
            }
        }

        return true;
    }

    public static boolean isDarkForPlayer(AbstractWeepingAngel angel, LivingEntity living) {
        return !living.hasEffect(MobEffects.NIGHT_VISION) && angel.level().getLightEmission(angel.blockPosition()) <= 0 && !angel.level().dimensionType().hasCeiling();
    }

    public static boolean isPlayerBlind(LivingEntity living) {
        return living.hasEffect(MobEffects.BLINDNESS);
    }

    private static HitResult rayTraceBlocks(LivingEntity livingEntity, Level world, Vec3 startVec, Vec3 endVec, Predicate<BlockPos> stopOn) {
        // Check for NaN values in the input vectors
        if (hasNaN(startVec) || hasNaN(endVec)) {
            return null;
        }

        // Convert start and end vectors to integer coordinates
        int startX = Mth.floor(startVec.x);
        int startY = Mth.floor(startVec.y);
        int startZ = Mth.floor(startVec.z);
        int endX = Mth.floor(endVec.x);
        int endY = Mth.floor(endVec.y);
        int endZ = Mth.floor(endVec.z);

        // Create a block position from the start coordinates
        BlockPos currentPos = new BlockPos(startX, startY, startZ);

        // Check if the block at the start position satisfies the stop condition
        if (stopOn.test(currentPos)) {
            HitResult result = world.clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity));
            if (result != null) {
                return result;
            }
        }

        int maxIterations = 200;

        while (maxIterations-- >= 0) {
            // Check if the current position is equal to the end position
            if (startX == endX && startY == endY && startZ == endZ) {
                return null;
            }

            // Variables to determine the next position to check
            boolean stepX = true;
            boolean stepY = true;
            boolean stepZ = true;
            double nextX = Double.POSITIVE_INFINITY;
            double nextY = Double.POSITIVE_INFINITY;
            double nextZ = Double.POSITIVE_INFINITY;

            // Calculate the distances to the next positions in each axis
            if (endX > startX) {
                nextX = (double) startX + 1.0D;
            } else if (endX < startX) {
                nextX = (double) startX + 0.0D;
            } else {
                stepX = false;
            }

            if (endY > startY) {
                nextY = (double) startY + 1.0D;
            } else if (endY < startY) {
                nextY = (double) startY + 0.0D;
            } else {
                stepY = false;
            }

            if (endZ > startZ) {
                nextZ = (double) startZ + 1.0D;
            } else if (endZ < startZ) {
                nextZ = (double) startZ + 0.0D;
            } else {
                stepZ = false;
            }

            // Calculate the ratios between the distances and the differences in positions
            double deltaX = endVec.x - startVec.x;
            double deltaY = endVec.y - startVec.y;
            double deltaZ = endVec.z - startVec.z;
            double ratioX = stepX ? (nextX - startVec.x) / deltaX : Double.POSITIVE_INFINITY;
            double ratioY = stepY ? (nextY - startVec.y) / deltaY : Double.POSITIVE_INFINITY;
            double ratioZ = stepZ ? (nextZ - startVec.z) / deltaZ : Double.POSITIVE_INFINITY;

            // Handle negative zero values
            if (ratioX == -0.0D) {
                ratioX = -1.0E-4D;
            }

            if (ratioY == -0.0D) {
                ratioY = -1.0E-4D;
            }

            if (ratioZ == -0.0D) {
                ratioZ = -1.0E-4D;
            }

            Direction direction;

            // Determine the direction and update the next position accordingly
            if (ratioX < ratioY && ratioX < ratioZ) {
                direction = endX > startX ? Direction.WEST : Direction.EAST;
                startVec = new Vec3(nextX, startVec.y + deltaY * ratioX, startVec.z + deltaZ * ratioX);
            } else if (ratioY < ratioZ) {
                direction = endY > startY ? Direction.DOWN : Direction.UP;
                startVec = new Vec3(startVec.x + deltaX * ratioY, nextY, startVec.z + deltaZ * ratioY);
            } else {
                direction = endZ > startZ ? Direction.NORTH : Direction.SOUTH;
                startVec = new Vec3(startVec.x + deltaX * ratioZ, startVec.y + deltaY * ratioZ, nextZ);
            }

            // Update the current position based on the direction
            startX = Mth.floor(startVec.x) - (direction == Direction.EAST ? 1 : 0);
            startY = Mth.floor(startVec.y) - (direction == Direction.UP ? 1 : 0);
            startZ = Mth.floor(startVec.z) - (direction == Direction.SOUTH ? 1 : 0);

            // Check if the new current position satisfies the stop condition
            currentPos = new BlockPos(startX, startY, startZ);
            if (stopOn.test(currentPos)) {
                HitResult result = world.clip(new ClipContext(startVec, endVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, livingEntity));
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    private static boolean hasNaN(Vec3 vec) {
        return Double.isNaN(vec.x) || Double.isNaN(vec.y) || Double.isNaN(vec.z);
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

        if(!block.defaultBlockState().canOcclude()){
            return true;
        }

        return blockState.getCollisionShape(world, pos) == Shapes.empty();
    }

}
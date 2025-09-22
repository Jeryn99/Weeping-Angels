package dev.jeryn.angels.util;

import com.google.common.collect.Lists;
import dev.jeryn.angels.WAConfiguration;
import dev.jeryn.angels.WeepingAngels;
import dev.jeryn.angels.common.WASounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;

import java.util.ArrayList;
import java.util.List;

public class Teleporter {


    public static ServerLevel getRandomDimension(RandomSource rand, ServerLevel serverLevel) {
        MinecraftServer server = serverLevel.getServer();
        Iterable<ServerLevel> dimensions = server.getAllLevels();
        ArrayList<ServerLevel> allowedDimensions = Lists.newArrayList(dimensions);

        for (ServerLevel dimension : dimensions) {
            for (String dimName : WAConfiguration.CONFIG.bannedDimensions.get()) {
                if (dimension.dimension().location().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }
        allowedDimensions.remove(server.getLevel(Level.NETHER));
        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }


    public static BlockPos findClosestValidPosition(ServerLevel level, BlockPos position) {
        
        ChunkPos chunkPos = level.getChunk(position).getPos();

        var maxBuildHeight = level.getMaxBuildHeight();
        var minHeight = level.getMinBuildHeight();

        List<BlockPos> solutionsInRow = new ArrayList<>();

        //Force load chunk to search positions
        level.setChunkForced(chunkPos.x, chunkPos.z, true);

        BlockPos closest = BlockPos.ZERO;

        if (canPlaceTardis(level, position) && isExitPositionSafe(level, position)) {
            solutionsInRow.add(position);
        }

        if (!solutionsInRow.isEmpty()) {
            closest = position;
        } else {

            //If the exact target location isn't valid, check blocks in the vertical column
            List<BlockPos> nextValidLocations = findValidLocationInColumn(level, position, minHeight, maxBuildHeight);
            if (!nextValidLocations.isEmpty()) {
                solutionsInRow.addAll(nextValidLocations);
            } else {
                //If the vertical column is not valid, let's check the surrounding area at the same y level.
                List<BlockPos> surroundingPositionsSameYLevel = getBlockPosInRadius(position, 1, true, false);
                for (BlockPos directionOffset : surroundingPositionsSameYLevel) {
                    BlockPos nextLocation = directionOffset;
                    if (canPlaceTardis(level, nextLocation) && isExitPositionSafe(level, nextLocation)) {
                        solutionsInRow.add(nextLocation);
                    }
                }

                //If the surrounding areas also aren't suitable, search vertically in the original location as well as surrounding areas
                //This is a much more expensive search so ideally we don't want to do this.
                if (solutionsInRow.isEmpty()) {

                    List<BlockPos> surroundingPositionsForColumn = getBlockPosInRadius(position, 6, true, true);

                    for (BlockPos pos : surroundingPositionsForColumn) {
                        List<BlockPos> surroundingColumn = findValidLocationInColumn(level, pos, minHeight, maxBuildHeight);
                        if (!surroundingColumn.isEmpty()) {
                            solutionsInRow.addAll(surroundingColumn);
                        }
                    }
                }
            }

            //Now after we have searched all possible solutions, find the closest solution.
            closest = findClosestValidPositionFromTarget(solutionsInRow, position);

        }

        //Unforce chunk after we are done searching
        level.setChunkForced(chunkPos.x, chunkPos.z, false);

        return closest;
    }

    public static List<BlockPos> getBlockPosInRadius(BlockPos referencePoint, int radius, boolean interCardinal, boolean includeReferencePoint) {
        List<BlockPos> posList = new ArrayList<>();

        //Add all horizontal directions, with the option of adding any intercardinal directions (North-East, South-East etc.)
        List<Direction> horizontalDirections = new ArrayList<>();
        horizontalDirections.addAll(Direction.Plane.HORIZONTAL.stream().toList());

        for (Direction dir : horizontalDirections) {
            BlockPos offsettedPos = referencePoint.relative(dir, radius);
            posList.add(offsettedPos);
            if (interCardinal) {
                BlockPos interCardinalPos = offsettedPos.offset(dir.getClockWise().getNormal());
                posList.add(interCardinalPos);
            }
        }

        //If we want to include the original reference point, add it as well.
        if (includeReferencePoint)
            posList.add(referencePoint);

        return posList;
    }

    public static List<BlockPos> findValidLocationInColumn(ServerLevel level, BlockPos position, int minHeight, int maxBuildHeight) {

        List<BlockPos> solutionsInRow = new ArrayList<>();

        List<BlockPos> blockColumn = getBlockPosColumn(position, minHeight, maxBuildHeight);
        List<BlockPos> filteredForAir = blockColumn.stream().filter(x -> isLegalLandingBlock(level, x)).toList();
        List<BlockPos> filteredForNonAir = blockColumn.stream().filter(x -> !isLegalLandingBlock(level, x)).toList();

        for (BlockPos airPos : filteredForAir) {

            if (level.dimension() == Level.NETHER && airPos.getY() > 125) {
                continue;
            }

            BlockPos below = airPos.below();
            BlockPos above = airPos.above();

            if (filteredForNonAir.contains(below) && filteredForAir.contains(above)) {
                if (canPlaceTardis(level, airPos) && isExitPositionSafe(level, airPos)) {
                    solutionsInRow.add(airPos);
                }
            }
        }
        return solutionsInRow;
    }


    public static  boolean isExitPositionSafe(ServerLevel level, BlockPos pos) {
        return isLegalLandingBlock(level, pos.above())
                && isLegalLandingBlock(level, pos)
                && !isLegalLandingBlock(level, pos.below());
    }

    private static boolean canPlaceTardis(ServerLevel targetLevel, BlockPos pos) {
        return (targetLevel.dimension() == Level.NETHER && pos.getY() <= 125);
    }

    public static boolean isLegalLandingBlock(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        // Can land in air or override any block that can be marked as "replaceable" such as snow, tall grass etc.
        return state.isAir() || (state.canBeReplaced() && state.getFluidState().isEmpty() && !state.isCollisionShapeFullBlock(level, pos));
    }


    /**
     * Finds the closest valid position out of a list of possible solutions, from the original intended landing location
     */
    private static BlockPos findClosestValidPositionFromTarget(List<BlockPos> validPositions, BlockPos targetLocation) {
        int distance = Integer.MAX_VALUE;
        BlockPos intendedLocation = targetLocation;
        BlockPos closestSolution = BlockPos.ZERO;
        for (BlockPos potentialLocation : validPositions) {
            int distanceBetween = Math.abs(potentialLocation.distManhattan(intendedLocation));
            if (distanceBetween < distance) {
                distance = distanceBetween;
                closestSolution = potentialLocation;
            }
        }
        return closestSolution;
    }

    private static List<BlockPos> getBlockPosColumn(BlockPos referencePoint, int min, int max) {

        List<BlockPos> positions = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            positions.add(new BlockPos(referencePoint.getX(), i, referencePoint.getZ()));
        }

        return positions;
    }


    private static boolean canTeleportTo(BlockPos pPos, Level level, Entity entity) {
        BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(level, pPos.mutable());
        if (blockpathtypes != BlockPathTypes.WALKABLE) {
            return false;
        } else {
            BlockPos blockpos = pPos.subtract(entity.blockPosition());
            return level.noCollision(entity, entity.getBoundingBox().move(blockpos));
        }
    }
    public static boolean performTeleport(Entity pEntity, ServerLevel pLevel, int pX, int pY, int pZ, float pYaw, float pPitch, boolean playSound) {
        WeepingAngels.LOGGER.debug("Teleported {} to {} {} {}", pEntity.getDisplayName().getString(), pX, pY, pZ);
        BlockPos blockpos = new BlockPos(pX, pY, pZ);

        if (!canTeleportTo(blockpos, pLevel, pEntity)) {
            return false;
        }

        if (!Level.isInSpawnableBounds(blockpos)) {
            return false;
        } else {
            float f = Mth.wrapDegrees(pYaw);
            float f1 = Mth.wrapDegrees(pPitch);
            if (pEntity instanceof ServerPlayer serverPlayer) {
                if (playSound) {
                    serverPlayer.connection.send(new ClientboundSoundPacket(Holder.direct(WASounds.TELEPORT.get()), SoundSource.MASTER, pX, pY, pZ, 0.25F, 1F, serverPlayer.level().random.nextLong()));
                }
                ChunkPos chunkpos = new ChunkPos(new BlockPos(pX, pY, pZ));
                pLevel.getChunkSource().addRegionTicket(TicketType.POST_TELEPORT, chunkpos, 1, pEntity.getId());
                pEntity.stopRiding();
                if (serverPlayer.isSleeping()) {
                    serverPlayer.stopSleepInBed(true, true);
                }

                if (pLevel == pEntity.level()) {
                    serverPlayer.connection.teleport(pX, pY, pZ, f, f1);
                } else {
                    serverPlayer.teleportTo(pLevel, pX, pY, pZ, f, f1);
                }
                pEntity.setYHeadRot(f);
            } else {
                float f2 = Mth.clamp(f1, -90.0F, 90.0F);
                if (pLevel == pEntity.level()) {
                    pEntity.moveTo(pX, pY, pZ, f, f2);
                    pEntity.setYHeadRot(f);
                } else {
                    pEntity.unRide();
                    Entity entity = pEntity;
                    pEntity = pEntity.getType().create(pLevel);
                    if (pEntity == null) {
                        return false;
                    }

                    pEntity.restoreFrom(entity);
                    pEntity.moveTo(pX, pY, pZ, f, f2);
                    pEntity.setYHeadRot(f);
                    entity.setRemoved(Entity.RemovalReason.CHANGED_DIMENSION);
                    pLevel.addDuringTeleport(pEntity);
                }
            }

            if (!(pEntity instanceof LivingEntity) || !((LivingEntity) pEntity).isFallFlying()) {
                pEntity.setDeltaMovement(pEntity.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D));
                pEntity.setOnGround(true);
            }

            if (pEntity instanceof PathfinderMob) {
                ((PathfinderMob) pEntity).getNavigation().stop();
            }

            return true;
        }
    }

}

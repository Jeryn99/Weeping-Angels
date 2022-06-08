package me.suff.mc.angels.utils;

import com.google.common.collect.Lists;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageSFX;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static BlockPos findSafePlace(Player playerEntity, Level world, BlockPos pos) {

        if (world.dimension().equals(Level.END)) {
            return ServerLevel.END_SPAWN_POINT;
        }

        for (int i = 5; i > 0; i--) {
            for (int y = 0; y < world.getMaxBuildHeight(); y++) {
                BlockPos newPos = new BlockPos(pos.getX() + i * 20, y, pos.getZ() + i * 20);
                if (isTeleportFriendlyBlock(world, pos, playerEntity) && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                    System.out.println("Teleporting player to " + newPos + " || " + world.getBlockState(newPos));
                    return newPos;
                }
            }
        }

        return world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos);
    }

    public static ServerLevel getRandomDimension(Random rand) {
        Iterable<ServerLevel> dimensions = ServerLifecycleHooks.getCurrentServer().getAllLevels();
        ArrayList<ServerLevel> allowedDimensions = Lists.newArrayList(dimensions);

        for (ServerLevel dimension : dimensions) {
            for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
                if (dimension.dimension().location().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }
        allowedDimensions.remove(ServerLifecycleHooks.getCurrentServer().getLevel(Level.NETHER));
        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

    public static void teleportPlayerTo(ServerPlayer player, BlockPos destinationPos, ServerLevel targetDimension) {
        Network.sendTo(new MessageSFX(WAObjects.Sounds.TELEPORT.get().getRegistryName()), player);
        player.teleportTo(targetDimension, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.yHeadRot, player.xRotO);
    }

    public static boolean handleStructures(ServerPlayer player) {
        TagKey<ConfiguredStructureFeature<?, ?>> targetStructure = AngelUtil.TELEPORT_STRUCTURES;
        if (targetStructure != null) {
            ServerLevel serverWorld = (ServerLevel) player.level;
            BlockPos bPos = serverWorld.findNearestMapFeature(targetStructure, player.blockPosition(), Integer.MAX_VALUE, false);
            if (bPos != null) {
                teleportPlayerTo(player, bPos, player.getLevel());
                return true;
            }
        }
        return false;
    }

    public static boolean isPosBelowOrAboveWorld(Level dim, int y) {
        if (dim.dimension() == Level.NETHER) {
            return y <= 0 || y >= 126;
        }
        return y <= 0 || y >= 256;
    }

    private static boolean isTeleportFriendlyBlock(Level world, BlockPos pos, Player playerEntity) {
        BlockState state = world.getBlockState(pos);
        BlockPos blockpos = pos.subtract(playerEntity.blockPosition());
        return state.getMaterial().blocksMotion() && state.isCollisionShapeFullBlock(world, pos) && world.noCollision(playerEntity, playerEntity.getBoundingBox().move(blockpos));
    }

}

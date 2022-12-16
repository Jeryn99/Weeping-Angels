package mc.craig.software.angels.utils;

import com.google.common.collect.Lists;
import mc.craig.software.angels.WeepingAngels;
import mc.craig.software.angels.common.WAObjects;
import mc.craig.software.angels.compat.tardis.TardisMod;
import mc.craig.software.angels.config.WAConfig;
import mc.craig.software.angels.network.Network;
import mc.craig.software.angels.network.messages.MessageSFX;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static BlockPos findSafePlace(PlayerEntity playerEntity, World world, BlockPos pos) {

        if (world.dimension().equals(World.END)) {
            return ServerWorld.END_SPAWN_POINT;
        }

        for (int i = 5; i > 0; i--) {
            for (int y = 0; y < world.getMaxBuildHeight(); y++) {
                BlockPos newPos = new BlockPos(pos.getX() + i * 20, y, pos.getZ() + i * 20);
                if (isTeleportFriendlyBlock(world, pos, playerEntity) && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                    WeepingAngels.LOGGER.info("Teleporting player to " + newPos + " || " + world.getBlockState(newPos));
                    return newPos;
                }
            }
        }

        return world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
    }

    public static ServerWorld getRandomDimension(Random rand) {
        Iterable<ServerWorld> dimensions = ServerLifecycleHooks.getCurrentServer().getAllLevels();
        ArrayList<ServerWorld> allowedDimensions = Lists.newArrayList(dimensions);

        for (ServerWorld dimension : dimensions) {
            for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
                if (dimension.dimension().location().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }

        if (ModList.get().isLoaded("tardis")) {
            allowedDimensions = TardisMod.cleanseDimensions(allowedDimensions);
        }

        allowedDimensions.remove(ServerLifecycleHooks.getCurrentServer().getLevel(World.NETHER));

        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

    public static boolean handleStructures(ServerPlayerEntity player) {

        Structure[] targetStructure = null;

        switch (player.level.dimension().location().toString()) {
            case "minecraft:overworld":
                targetStructure = AngelUtil.OVERWORLD_STRUCTURES;
                break;

            case "minecraft:end":
                targetStructure = AngelUtil.END_STRUCTURES;
                break;

            case "minecraft:nether":
                targetStructure = AngelUtil.NETHER_STRUCTURES;
                break;
        }

        if (targetStructure != null) {
            ServerWorld serverWorld = (ServerWorld) player.level;
            BlockPos bPos = serverWorld.findNearestMapFeature(targetStructure[player.level.random.nextInt(targetStructure.length)], player.blockPosition(), Integer.MAX_VALUE, false);
            if (bPos != null) {
                teleportPlayerTo(player, bPos, player.getLevel());
                return true;
            }
        }
        return false;
    }

    public static void teleportPlayerTo(ServerPlayerEntity player, BlockPos destinationPos, ServerWorld targetDimension) {
        Network.sendTo(new MessageSFX(WAObjects.Sounds.TELEPORT.get().getRegistryName()), player);
        player.teleportTo(targetDimension, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.yRot, player.xRot);
    }


    public static boolean isPosBelowOrAboveWorld(World dim, int y) {
        if (dim.dimension() == World.NETHER) {
            return y <= 0 || y >= 126;
        }
        return y <= 0 || y >= 256;
    }

    private static boolean isTeleportFriendlyBlock(World world, BlockPos pos, PlayerEntity playerEntity) {
        BlockState state = world.getBlockState(pos);
        BlockPos blockpos = pos.subtract(playerEntity.blockPosition());
        return state.getMaterial().blocksMotion() && state.isCollisionShapeFullBlock(world, pos) && world.noCollision(playerEntity, playerEntity.getBoundingBox().move(blockpos));
    }

}

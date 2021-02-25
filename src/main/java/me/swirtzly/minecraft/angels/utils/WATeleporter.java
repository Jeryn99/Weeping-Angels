package me.swirtzly.minecraft.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.compat.tardis.TardisMod;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.network.Network;
import me.swirtzly.minecraft.angels.network.messages.MessageSFX;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.util.Direction;
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

        for (int i = 5; i > 0; i--) {
            System.out.println("Attempt: " + i);
            System.out.println("Offsetting by: " + i * 100);

            for (int y = 0; y < world.getHeight(); y++) {
                System.out.println("Height: " + y);
                BlockPos newPos = new BlockPos(pos.getX() + i * 20, y, pos.getZ() + i * 20);
                if (isTeleportFriendlyBlock(world, pos,playerEntity) && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                    System.out.println("Teleporting player to " + newPos + " || " + world.getBlockState(newPos));
                    return newPos;
                }
            }
        }

        return world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
    }

    public static ServerWorld getRandomDimension(Random rand) {
        Iterable< ServerWorld > dimensions = ServerLifecycleHooks.getCurrentServer().getWorlds();
        ArrayList< ServerWorld > allowedDimensions = Lists.newArrayList(dimensions);

        for (ServerWorld dimension : dimensions) {
            for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
                if (dimension.getDimensionKey().getLocation().toString().equalsIgnoreCase(dimName)) {
                    allowedDimensions.remove(dimension);
                }
            }
        }

        if (ModList.get().isLoaded("tardis")) {
            allowedDimensions = TardisMod.cleanseDimensions(allowedDimensions);
        }

        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

    public static boolean handleStructures(ServerPlayerEntity player) {

        Structure[] targetStructure = null;

        switch (player.world.getDimensionKey().getLocation().toString()) {
            case "minecraft:overworld":
                targetStructure = AngelUtils.OVERWORLD_STRUCTURES;
                break;

            case "minecraft:end":
                targetStructure = AngelUtils.END_STRUCTURES;
                break;

            case "minecraft:nether":
                targetStructure = AngelUtils.NETHER_STRUCTURES;
                break;
        }

        if (targetStructure != null) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            BlockPos bPos = serverWorld.func_241117_a_(targetStructure[player.world.rand.nextInt(targetStructure.length)], player.getPosition(), Integer.MAX_VALUE, false);
            if (bPos != null) {
                teleportPlayerTo(player, bPos, player.getServerWorld());
                return true;
            }
        }
        return false;
    }

    public static void teleportPlayerTo(ServerPlayerEntity player, BlockPos destinationPos, ServerWorld targetDimension) {
        Network.sendTo(new MessageSFX(WAObjects.Sounds.TELEPORT.get().getRegistryName()), player);
        player.teleport(targetDimension, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.rotationYaw, player.rotationPitch);
    }


    public static boolean isPosBelowOrAboveWorld(World dim, int y) {
        if (dim.getDimensionKey() == World.THE_NETHER) {
            return y <= 0 || y >= 126;
        }
        return y <= 0 || y >= 256;
    }

    private static boolean isTeleportFriendlyBlock(World world, BlockPos pos, PlayerEntity playerEntity) {
        BlockState state = world.getBlockState(pos);
        BlockPos blockpos = pos.subtract(playerEntity.getPosition());
        return state.getMaterial().blocksMovement() && state.hasOpaqueCollisionShape(world, pos) && world.hasNoCollisions(playerEntity, playerEntity.getBoundingBox().offset(blockpos));
    }

}

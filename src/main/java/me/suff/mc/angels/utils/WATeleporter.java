package me.suff.mc.angels.utils;

import com.google.common.collect.Lists;
import me.suff.mc.angels.common.WAObjects;
import me.suff.mc.angels.compat.tardis.TardisMod;
import me.suff.mc.angels.config.WAConfig;
import me.suff.mc.angels.network.Network;
import me.suff.mc.angels.network.messages.MessageSFX;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.TeleportationRepositioner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static BlockPos findSafePlace(PlayerEntity playerEntity, World world, BlockPos pos) {

        if(world.dimension().equals(World.NETHER)){
            WorldBorder worldborder = world.getWorldBorder();
            double d0 = Math.max(-2.9999872E7D, worldborder.getMinX() + 16.0D);
            double d1 = Math.max(-2.9999872E7D, worldborder.getMinZ() + 16.0D);
            double d2 = Math.min(2.9999872E7D, worldborder.getMaxX() - 16.0D);
            double d3 = Math.min(2.9999872E7D, worldborder.getMaxZ() - 16.0D);
            double d4 = DimensionType.getTeleportationScale(world.dimensionType(), ServerLifecycleHooks.getCurrentServer().getLevel(World.NETHER).dimensionType());
            BlockPos blockpos1 = new BlockPos(MathHelper.clamp(pos.getZ() * d4, d0, d2), pos.getY(), MathHelper.clamp(pos.getZ() * d4, d1, d3));

            BlockState blockstate = world.getBlockState(blockpos1);
            TeleportationRepositioner.Result tt = TeleportationRepositioner.getLargestRectangleAround(blockpos1, Direction.Axis.X, 21, Direction.Axis.Y, 21, (posIn) -> {
                return world.getBlockState(posIn) == blockstate;
            });

            return tt.minCorner;
        }

        if(world.dimension().equals(World.END)){
            return ServerWorld.END_SPAWN_POINT;
        }

        for (int i = 5; i > 0; i--) {
            for (int y = 0; y < world.getMaxBuildHeight(); y++) {
                BlockPos newPos = new BlockPos(pos.getX() + i * 20, y, pos.getZ() + i * 20);
                if (isTeleportFriendlyBlock(world, pos,playerEntity) && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                    System.out.println("Teleporting player to " + newPos + " || " + world.getBlockState(newPos));
                    return newPos;
                }
            }
        }

        return world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos);
    }

    public static ServerWorld getRandomDimension(Random rand) {
        Iterable< ServerWorld > dimensions = ServerLifecycleHooks.getCurrentServer().getAllLevels();
        ArrayList< ServerWorld > allowedDimensions = Lists.newArrayList(dimensions);

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

        if(rand.nextInt(100) > 20){
            allowedDimensions.remove(ServerLifecycleHooks.getCurrentServer().getLevel(World.NETHER));
        }

        return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
    }

    public static boolean handleStructures(ServerPlayerEntity player) {

        Structure[] targetStructure = null;

        switch (player.level.dimension().location().toString()) {
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

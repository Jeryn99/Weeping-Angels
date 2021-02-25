package me.swirtzly.minecraft.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.compat.tardis.TardisMod;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.network.Network;
import me.swirtzly.minecraft.angels.network.messages.MessageSFX;
import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.state.properties.BlockStateProperties;
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
import net.minecraft.world.storage.IWorldInfo;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static BlockPos findSafePlace(PlayerEntity playerEntity, World world, BlockPos pos) {

        if(world.getDimensionKey().equals(World.THE_NETHER)){
            WorldBorder worldborder = world.getWorldBorder();
            double d0 = Math.max(-2.9999872E7D, worldborder.minX() + 16.0D);
            double d1 = Math.max(-2.9999872E7D, worldborder.minZ() + 16.0D);
            double d2 = Math.min(2.9999872E7D, worldborder.maxX() - 16.0D);
            double d3 = Math.min(2.9999872E7D, worldborder.maxZ() - 16.0D);
            double d4 = DimensionType.getCoordinateDifference(world.getDimensionType(), ServerLifecycleHooks.getCurrentServer().getWorld(World.THE_NETHER).getDimensionType());
            BlockPos blockpos1 = new BlockPos(MathHelper.clamp(pos.getZ() * d4, d0, d2), pos.getY(), MathHelper.clamp(pos.getZ() * d4, d1, d3));

            BlockState blockstate = world.getBlockState(blockpos1);
            TeleportationRepositioner.Result tt = TeleportationRepositioner.findLargestRectangle(blockpos1, Direction.Axis.X, 21, Direction.Axis.Y, 21, (posIn) -> {
                return world.getBlockState(posIn) == blockstate;
            });

            return tt.startPos;
        }

        if(world.getDimensionKey().equals(World.THE_END)){
            return ServerWorld.field_241108_a_;
        }

        for (int i = 5; i > 0; i--) {
            for (int y = 0; y < world.getHeight(); y++) {
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

        if(rand.nextInt(100) > 20){
            allowedDimensions.remove(ServerLifecycleHooks.getCurrentServer().getWorld(World.THE_NETHER));
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

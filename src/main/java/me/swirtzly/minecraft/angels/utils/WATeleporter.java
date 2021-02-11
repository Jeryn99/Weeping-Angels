package me.swirtzly.minecraft.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.minecraft.angels.WeepingAngels;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.compat.tardis.TardisMod;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.network.Network;
import me.swirtzly.minecraft.angels.network.messages.MessageSFX;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.server.command.ForgeCommand;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {

    public static int yCoordSanity(World world, BlockPos pos) {
        for (int y = world.getHeight(); y > 0; --y) {
            BlockPos newPos = new BlockPos(pos.getX(), y, pos.getZ());
            BlockState underState = world.getBlockState(newPos.down());

            if (!willBlockStateCauseSuffocation(world, newPos) && underState.isSolid() && !isPosBelowOrAboveWorld(world, newPos.getY())) {
                return newPos.getY();
            }
        }
        return world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX(), pos.getZ());
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

        if(ModList.get().isLoaded("tardis")){
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

    /**
     * Checks if the blockstate will cause entity to suffocate in it.
     * <br> This attempts to reproduce behaviour shown in the private AbstractBlock suffocates predicate
     * <br> Do not use AbstractBlock#causesSuffocation because that does not actually relate to suffocation at all, it is a rendering related method misnamed in 1.16 mappings
     *
     * @param world
     * @param pos
     * @return true if causes suffocating, false if it doesn't
     */
    public static boolean willBlockStateCauseSuffocation(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.getMaterial().blocksMovement() && state.hasOpaqueCollisionShape(world, pos);
    }

}

package me.swirtzly.minecraft.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.minecraft.angels.common.WAObjects;
import me.swirtzly.minecraft.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.minecraft.angels.config.WAConfig;
import me.swirtzly.minecraft.angels.network.Network;
import me.swirtzly.minecraft.angels.network.messages.MessageSFX;
import net.minecraft.command.impl.LocateCommand;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {
	
	public static int yCoordSanity(World world, BlockPos spawn) {
        IChunk chunk = world.getChunk(spawn);
        return chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE, spawn.getX(), spawn.getZ());
    }
	
	public static ServerWorld getRandomDimension(Random rand) {
		Iterable<ServerWorld> dimensions = ServerLifecycleHooks.getCurrentServer().getWorlds();
		ArrayList<ServerWorld> allowedDimensions = Lists.newArrayList(dimensions);
		
		for (ServerWorld dimension : dimensions) {
			for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
				if (dimension.getDimensionKey().getRegistryName().toString().equalsIgnoreCase(dimName) || dimension.getDimensionKey().getRegistryName().toString().contains("tardis")) {
					allowedDimensions.remove(dimension);
				}
			}
		}
		
		return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
	}
	
	public static void handleStructures(ServerPlayerEntity player) {

		Structure[] targetStructure = null;

		switch (player.world.getDimensionKey().getRegistryName().toString()) {
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
			}
		}
	}
	
	public static void teleportPlayerTo(ServerPlayerEntity player, BlockPos destinationPos, ServerWorld targetDimension) {
			Network.sendTo(new MessageSFX(WAObjects.Sounds.TELEPORT.get().getRegistryName()), player);
			player.teleport(targetDimension, destinationPos.getX(), destinationPos.getY(), destinationPos.getZ(), player.rotationYaw, player.rotationPitch);
		}
}

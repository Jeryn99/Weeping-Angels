package me.swirtzly.angels.utils;

import com.google.common.collect.Lists;
import me.swirtzly.angels.common.WAObjects;
import me.swirtzly.angels.common.entities.WeepingAngelEntity;
import me.swirtzly.angels.compat.events.EventAngelTeleport;
import me.swirtzly.angels.config.WAConfig;
import me.swirtzly.angels.network.Network;
import me.swirtzly.angels.network.messages.MessageSFX;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;

import java.util.ArrayList;
import java.util.Random;

public class WATeleporter {
	
	public static int yCoordSanity(World world, BlockPos spawn) {
		for (int i = 0; i < world.getActualHeight(); i++) {
			BlockPos pos = spawn.up(i);
			if (world.getBlockState(pos).getMaterial() != Material.LAVA && !world.getBlockState(pos).causesSuffocation(world, pos) && !world.getBlockState(pos.up()).causesSuffocation(world, pos.up())) {
				return pos.getY();
			}
		}
		return 65;
	}
	
	public static DimensionType getRandomDimension(Random rand) {
		Iterable<DimensionType> dimensions = DimensionType.getAll();
		ArrayList<DimensionType> allowedDimensions = Lists.newArrayList(DimensionType.getAll());
		
		for (DimensionType dimension : dimensions) {
			for (String dimName : WAConfig.CONFIG.notAllowedDimensions.get()) {
				if (dimension.getRegistryName().toString().equalsIgnoreCase(dimName) || dimension.getRegistryName().toString().contains("tardis")) {
					allowedDimensions.remove(dimension);
				}
			}
		}
		
		return allowedDimensions.get(rand.nextInt(allowedDimensions.size()));
	}
	
	public static void handleStructures(ServerPlayerEntity player, WeepingAngelEntity weepingAngelEntity) {
		
		String[] targetStructure = null;
		
		switch (player.world.dimension.getType().getId()) {
			case 0:
				targetStructure = AngelUtils.OVERWORLD_STRUCTURES;
				break;
			
			case 1:
				targetStructure = AngelUtils.END_STRUCTURES;
				break;
			
			case -1:
				targetStructure = AngelUtils.NETHER_STRUCTURES;
				break;
		}
		
		if (targetStructure != null) {
			BlockPos bPos = player.getEntityWorld().findNearestStructure(targetStructure[player.world.rand.nextInt(targetStructure.length)], player.getPosition(), Integer.MAX_VALUE, false);
			if (bPos != null) {
				teleportPlayerTo(player, weepingAngelEntity, bPos, player.getServerWorld());
			}
		}
	}
	
	public static void teleportPlayerTo(ServerPlayerEntity player, WeepingAngelEntity angel, BlockPos destinationPos, ServerWorld targetDimension) {
		EventAngelTeleport event = new EventAngelTeleport(player, angel, destinationPos, targetDimension);
		if (MinecraftForge.EVENT_BUS.post(event)) {
			Network.sendTo(new MessageSFX(WAObjects.Sounds.TELEPORT.get().getRegistryName()), player);
			player.teleport(event.getTargetDimension(), event.getDestination().getX(), event.getDestination().getY(), event.getDestination().getZ(), player.rotationYaw, player.rotationPitch);
		}
	}
	
}

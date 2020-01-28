package me.swirtzly.angels.utils;

import me.swirtzly.angels.common.entities.EntityAnomaly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.command.ForgeCommand;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;

public final class Teleporter {


	public static DimensionType getRandomDimension(DimensionType current, Random rand) {
		Iterable<DimensionType> dimensions = DimensionType.getAll();
		


		//WAConfig.CONFIG.notAllowedDimensions.get()
		
		return current;
	}
	
	public static void handleStructures(PlayerEntity player) {
		
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
				TeleporterNew.teleportEntity(player, player.dimension, bPos.getX(), bPos.getY(), bPos.getZ());
			}
		}
	}
}

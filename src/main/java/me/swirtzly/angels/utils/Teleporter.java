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

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Random;

public final class Teleporter {
	
	@Nullable
	public static Entity move(Entity entity, DimensionType dimension, BlockPos pos) {
		return move(entity, dimension, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
	}
	
	@Nullable
	public static Entity move(Entity entity, DimensionType dimension, double x, double y, double z) {
		if (entity.world.isRemote || !entity.isNonBoss()) {
			return null;
		}
		
		if (entity.dimension == dimension) {
			if (entity instanceof ServerPlayerEntity) {
				((ServerPlayerEntity) entity).connection.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
			} else {
				entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
			}
			return entity;
		}
		return entity.changeDimension(dimension, new WATeleport(x, y, z));
	}
	
	public static Entity moveSafeAcrossDim(Entity entity, BlockPos pos) {
		
		EntityAnomaly anomaly = new EntityAnomaly(entity.world);
		BlockPos entityOldPos = entity.getPosition();
		anomaly.setLocationAndAngles(entityOldPos.getX(), entityOldPos.getY(), entityOldPos.getZ(), entity.rotationYaw, entity.rotationPitch);
		entity.world.addEntity(anomaly);
		
		if (entity instanceof ServerPlayerEntity) {
			DimensionType newDimension = getRandomDimension(entity.world.dimension.getType(), new Random());
			entity.changeDimension(newDimension, (world, en, yaw) -> entity.setLocationAndAngles(0, 0, 0, en.rotationYaw, en.rotationPitch));
			World world = entity.getEntityWorld();
			boolean beSafeFlag = newDimension == DimensionType.field_223229_c_ || newDimension == DimensionType.field_223228_b_;
			BlockPos spawn = beSafeFlag ? pos : world.getSpawnPoint();
			
			while (!(world.isAirBlock(spawn) && world.isAirBlock(spawn.up())) && spawn.getY() < world.dimension.getHeight() - 5)
				spawn = spawn.up();
			
			entity.setPositionAndUpdate(spawn.getX(), spawn.getY(), spawn.getZ());
			return entity;
		}
		return entity;
	}
	
	public static DimensionType getRandomDimension(DimensionType current, Random rand) {
		Collection<ModDimension> dimensions = ForgeRegistries.MOD_DIMENSIONS.getValues();
		
		ModDimension[] dimArray = dimensions.toArray(new ModDimension[0]);
		
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
				Teleporter.move(player, player.dimension, bPos);
			}
		}
	}
	
	public static final class WATeleport extends net.minecraft.world.Teleporter {
		private final double x, y, z;
		
		public WATeleport(double x, double y, double z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		@Override
		public void placeEntity(World world, Entity entity, float yaw) {
			entity.setLocationAndAngles(x, y, z, yaw, entity.rotationPitch);
		}
	}
	
}

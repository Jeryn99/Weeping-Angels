package me.sub.angels.utils;

import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.common.events.mods.EventAngelTeleport;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;

public final class Teleporter {

    @Nullable
	public static Entity move(Entity entity, int dimension, BlockPos pos) {
		return move(entity, dimension, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
	}
	
	@Nullable
	public static Entity move(Entity entity, int dimension, double x, double y, double z) {
        if (entity.world.isRemote || !entity.isNonBoss()) {
			return null;
		}
		if (entity.dimension == dimension) {
			if (entity instanceof EntityPlayerMP) {
				((EntityPlayerMP) entity).connection.setPlayerLocation(x, y, z, entity.rotationYaw, entity.rotationPitch);
			} else {
				entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
			}
			return entity;
		}
		return entity.changeDimension(dimension, new WATeleport(x, y, z));
	}
	
	public static Entity move(EntityPlayer player, BlockPos pos, int dim, EntityWeepingAngel entityWeepingAngel) {
		MinecraftForge.EVENT_BUS.post(new EventAngelTeleport(player, entityWeepingAngel, pos, dim));
		return move(player, dim, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
	}
	
	private static final class WATeleport implements ITeleporter {
		private final double x, y, z;
		
		private WATeleport(double x, double y, double z) {
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

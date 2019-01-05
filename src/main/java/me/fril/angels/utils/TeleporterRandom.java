package me.fril.angels.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import javax.annotation.Nonnull;
import java.util.Random;

//This class was pinched and adapted from EnderIO, Credit to those guys for being smarter than me
//https://raw.githubusercontent.com/SleepyTrousers/EnderIO/master/enderio-base/src/main/java/crazypants/enderio/base/teleport/RandomTeleportUtil.java
public class TeleporterRandom {
	
	private static final Random rand = new Random();

	public static void teleportEntity(@Nonnull World world, @Nonnull EntityLivingBase entity, float range) {
		if (entity instanceof FakePlayer) {
			// don't even bother...
			return;
		}
		double origX = entity.posX, origY = world.provider.getAverageGroundLevel(), origZ = entity.posZ;
		for (int i = 0; i < 15; i++) {
			double targetX = origX + rand.nextGaussian() * range;
			double targetY = -1;
			while (targetY < 1.1) {
				targetY = origY;
			}
			double targetZ = origZ + rand.nextGaussian() * range;
			if (targetY >= 2f && isClear(world, entity, targetX, targetY, targetZ) && doTeleport(world, entity, new BlockPos(targetX, targetY, targetZ))) {
				entity.timeUntilPortal = 5;
				return;
			}
		}
	}
	
	private static boolean isClear(@Nonnull World world, @Nonnull EntityLivingBase entity, double targetX, double targetY, double targetZ) {
		double origX = entity.posX, origY = entity.posY, origZ = entity.posZ;
		try {
			entity.setPosition(targetX, targetY, targetZ);
			boolean result = world.checkNoEntityCollision(entity.getEntityBoundingBox(), entity)
					&& world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(entity.getEntityBoundingBox());
			return result;
		} finally {
			entity.setPosition(origX, origY, origZ);
		}
	}
	
	private static boolean hasGround(@Nonnull World world, double targetX, double targetY, double targetZ) {
		int xInt = MathHelper.floor(targetX);
		int yInt = MathHelper.floor(targetY);
		int zInt = MathHelper.floor(targetZ);
		return yInt > 1 && world.getBlockState(new BlockPos(xInt, yInt - 1, zInt)).getMaterial().blocksMovement();
	}
	
	private static boolean doTeleport(@Nonnull World world, @Nonnull EntityLivingBase entity, BlockPos pos) {
		Teleporter.move(entity, world.provider.getDimension(), pos);
		entity.fallDistance = 0.0F;
		return true;
	}


	public static void handleStructures(EntityPlayer player) {

		String[] targetStructure = null;

		switch (player.world.provider.getDimension()) {
			case 0:
				targetStructure = AngelUtils.overworldStructures;
				break;

			case 1:
				targetStructure = AngelUtils.endStructures;
				break;

			case -1:
				targetStructure = AngelUtils.netherStructures;
				break;
		}

		if (targetStructure != null) {
			BlockPos bPos = player.getEntityWorld().findNearestStructure(targetStructure[player.world.rand.nextInt(targetStructure.length)], player.getPosition(), false);
			if (bPos != null) {
				Teleporter.move(player, player.dimension, bPos);
			}
		}
	}

}

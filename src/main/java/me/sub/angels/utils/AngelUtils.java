package me.sub.angels.utils;

import com.google.common.collect.Lists;
import me.sub.angels.combat.vivecraft.ServerReflector;
import me.sub.angels.common.WAObjects;
import me.sub.angels.common.entities.EntityQuantumLockBase;
import me.sub.angels.common.entities.EntityWeepingAngel;
import me.sub.angels.config.WAConfig;
import me.sub.angels.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.ArrayList;
import java.util.Collection;

public class AngelUtils {
	
	public static ArrayList<Item> lightItems = new ArrayList<Item>();
	
	/*
	 * Method that detects whether a tile is the the view sight of viewer
	 * @param viewer The viewer entity
	 * @param tile The tile being watched by viewer
	 */
	public static boolean isInSightTile(EntityLivingBase viewer, TileEntity tile) {
		double dx = tile.getPos().getX() - viewer.posX;
		double dz;
		for (dz = tile.getPos().getX() - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		
		return yaw < 60 && yaw > -60;
	}


	public static boolean isInSight(EntityLivingBase livingBase, EntityQuantumLockBase angel) {
		return isInFrontOfEntity(livingBase, angel, CommonProxy.reflector.isVRPlayer((EntityPlayer) livingBase)) && !viewBlocked(livingBase, angel) && !AngelUtils.isDarkForPlayer(angel, livingBase);
	}
	
	public static boolean isDarkForPlayer(EntityQuantumLockBase angel, EntityLivingBase living) {
		return !living.isPotionActive(MobEffects.NIGHT_VISION) && angel.world.getLight(angel.getPosition()) == 0 && !AngelUtils.handLightCheck(living);
	}
	
	public static void setupLightItems() {
		for (Block block : ForgeRegistries.BLOCKS.getValuesCollection()) {
			
			if (AngelUtils.getLightValue(block) > 7) {
				lightItems.add(Item.getItemFromBlock(block));
			}
			
			lightItems.add(Item.getItemFromBlock(Blocks.REDSTONE_TORCH));
		}
	}


	public static boolean canSee(EntityLivingBase viewer, EntityLivingBase angel) {
		double dx = angel.posX - viewer.posX;
		double dz;
		for (dz = angel.posX - viewer.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) {
			dx = (Math.random() - Math.random()) * 0.01D;
		}
		while (viewer.rotationYaw > 360) {
			viewer.rotationYaw -= 360;
		}
		while (viewer.rotationYaw < -360) {
			viewer.rotationYaw += 360;
		}
		float yaw = (float) (Math.atan2(dz, dx) * 180.0D / Math.PI) - viewer.rotationYaw;
		yaw = yaw - 90;
		while (yaw < -180) {
			yaw += 360;
		}
		while (yaw >= 180) {
			yaw -= 360;
		}
		
		return yaw < 60 && yaw > -60 && viewer.canEntityBeSeen(angel);
	}
	
	public static boolean handLightCheck(EntityLivingBase player) {
		for (Item item : lightItems) {
			if (PlayerUtils.isInEitherHand(player, item)) {
				return true;
			}
		}
		return false;
	}
	
	// Spawn Set up
	public static void setUpSpawns() {
		Collection<Biome> biomes = ForgeRegistries.BIOMES.getValuesCollection();
		ArrayList<Biome> spawn = Lists.newArrayList();
		spawn.addAll(biomes);
		
		for (String rs : WAConfig.spawn.notAllowedBiomes) {
			if (Biome.REGISTRY.containsKey(new ResourceLocation(rs))) {
				Biome removedBiome = Biome.REGISTRY.getObject(new ResourceLocation(rs));
				spawn.remove(removedBiome);
			}
		}
		
		for (Biome biome : spawn) {
			if (biome != null) {
				EntityRegistry.addSpawn(EntityWeepingAngel.class, WAConfig.spawn.spawnProbability, WAConfig.spawn.minimumSpawn, WAConfig.spawn.maximumSpawn, WAConfig.spawn.spawnType, biome);
			}
		}
	}
	
	public static int secondsToTicks(int seconds) {
		return 20 * seconds;
	}
	
	public static void removeLightFromHand(EntityPlayerMP playerMP, EntityWeepingAngel angel) {
		if (playerMP.getDistanceSq(angel) < 1) {
			
			ItemStack stack = playerMP.getHeldItem(EnumHand.MAIN_HAND);
			if (lightCheck(playerMP, stack, angel)) {
				return;
			}
			
			stack = playerMP.getHeldItem(EnumHand.OFF_HAND);
			lightCheck(playerMP, stack, angel);
		}
	}
	
	public static int getLightValue(Block block) {
		return ReflectionHelper.getPrivateValue(Block.class, block, 9);
	}
	
	private static boolean lightCheck(EntityPlayerMP player, ItemStack stack, EntityWeepingAngel angel) {
		
		if (stack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) {
			stack.shrink(1);
			player.addItemStackToInventory(new ItemStack(WAObjects.Items.UNLIT_TORCH));
			angel.playSound(WAObjects.Sounds.BLOW, 1.0F, 1.0F);
			return true;
		}
		
		if (lightItems.contains(stack.getItem())) {
			if (stack.getItem() == Item.getItemFromBlock(Blocks.TORCH)) return false;
			stack.shrink(1);
			angel.playSound(WAObjects.Sounds.BLOW, 1.0F, 1.0F);
			return true;
		}
		
		return false;
	}
	
	public static boolean isInFrontOfEntity(Entity entity, Entity target, boolean vr) {
		Vec3d vecTargetsPos = target.getPositionVector();
		Vec3d vecLook;

		if(vr){
			if(entity instanceof EntityPlayer) {
				vecLook = CommonProxy.reflector.getHMDRot((EntityPlayer) entity);
			} else {
				throw new RuntimeException("yo dawg, only players can use vr");
			}
		} else {
			vecLook = entity.getLookVec();
		}

		Vec3d vecFinal = vecTargetsPos.subtractReverse(new Vec3d(entity.posX, entity.posY, entity.posZ)).normalize();
		vecFinal = new Vec3d(vecFinal.x, 0.0D, vecFinal.z);
		return vecFinal.dotProduct(vecLook) < 0.0;
	}

	
	public static boolean viewBlocked(EntityLivingBase viewer, EntityLivingBase angel) {
		AxisAlignedBB viewerEntityBoundingBox = viewer.getEntityBoundingBox();
		AxisAlignedBB angelEntityBoundingBox = angel.getEntityBoundingBox();
		Vec3d[] viewerPoints = { new Vec3d(viewerEntityBoundingBox.minX, viewerEntityBoundingBox.minY, viewerEntityBoundingBox.minZ), new Vec3d(viewerEntityBoundingBox.minX, viewerEntityBoundingBox.minY, viewerEntityBoundingBox.maxZ), new Vec3d(viewerEntityBoundingBox.minX, viewerEntityBoundingBox.maxY, viewerEntityBoundingBox.minZ), new Vec3d(viewerEntityBoundingBox.minX, viewerEntityBoundingBox.maxY, viewerEntityBoundingBox.maxZ), new Vec3d(viewerEntityBoundingBox.maxX, viewerEntityBoundingBox.maxY, viewerEntityBoundingBox.minZ), new Vec3d(viewerEntityBoundingBox.maxX, viewerEntityBoundingBox.maxY, viewerEntityBoundingBox.maxZ), new Vec3d(viewerEntityBoundingBox.maxX, viewerEntityBoundingBox.minY, viewerEntityBoundingBox.maxZ), new Vec3d(viewerEntityBoundingBox.maxX, viewerEntityBoundingBox.minY, viewerEntityBoundingBox.minZ), };
		Vec3d[] angelPoints = { new Vec3d(angelEntityBoundingBox.minX, angelEntityBoundingBox.minY, angelEntityBoundingBox.minZ), new Vec3d(angelEntityBoundingBox.minX, angelEntityBoundingBox.minY, angelEntityBoundingBox.maxZ), new Vec3d(angelEntityBoundingBox.minX, angelEntityBoundingBox.maxY, angelEntityBoundingBox.minZ), new Vec3d(angelEntityBoundingBox.minX, angelEntityBoundingBox.maxY, angelEntityBoundingBox.maxZ), new Vec3d(angelEntityBoundingBox.maxX, angelEntityBoundingBox.maxY, angelEntityBoundingBox.minZ), new Vec3d(angelEntityBoundingBox.maxX, angelEntityBoundingBox.maxY, angelEntityBoundingBox.maxZ), new Vec3d(angelEntityBoundingBox.maxX, angelEntityBoundingBox.minY, angelEntityBoundingBox.maxZ), new Vec3d(angelEntityBoundingBox.maxX, angelEntityBoundingBox.minY, angelEntityBoundingBox.minZ), };
		
		for (int i = 0; i < viewerPoints.length; i++) {
			if (viewer.world.rayTraceBlocks(viewerPoints[i], angelPoints[i], false, true, false) == null) return false;
		}
		return true;
	}

	public static void playBreakEvent(Entity entity, BlockPos pos, Block block) {
		if (!entity.world.isRemote) {
			entity.playSound(WAObjects.Sounds.LIGHT_BREAK, 1.0F, 1.0F);
			InventoryHelper.spawnItemStack(entity.world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(entity.world.getBlockState(pos).getBlock()));
			entity.world.setBlockState(pos, block.getDefaultState());

			for (EntityPlayer player : entity.world.playerEntities) {
				if (player instanceof EntityPlayerMP) {
					EntityPlayerMP playerMP = (EntityPlayerMP) player;
					if (playerMP.getDistanceSq(pos) < 45) {
						playerMP.connection.sendPacket(new SPacketParticles(EnumParticleTypes.CRIT_MAGIC, false, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0, 1.0F, 11));
					}
				}
			}
		}
	}
	
}
